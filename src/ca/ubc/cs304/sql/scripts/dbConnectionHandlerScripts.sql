-- DELETE JobPosting
DELETE
FROM JobPosting posting
WHERE posting.jobID = 20221001;

-- check login credentials SELECT
SELECT COUNT(*) AS count
FROM APPLICANT
WHERE userID = 2201
  AND password = 'Ca123456';

-- checkRecruiterLogin SELECT
SELECT COUNT(*) AS count
from Recruiter
WHERE recruiterID = ?
  AND password = ?

-- checkCompanyLogin SELECT
SELECT COUNT(*) AS count
from Company
WHERE companyID = ?
  AND password = ?

-- selectAllJobPosting using JOIN
select posting.*, info.salary, jl.visaRequirement
from JobPosting posting
         JOIN JobInfo info on info.companyID = posting.companyID
    AND info.positionName = posting.positionName
         JOIN JobLocation jl on posting.location = jl.location;

-- selectJobPostingsByCompanyID using JOIN
select posting.*, info.salary, jl.visaRequirement
from JobPosting posting
         JOIN JobInfo info on info.companyID = posting.companyID
    AND info.positionName = posting.positionName
         JOIN JobLocation jl on posting.location = jl.location
WHERE posting.companyID = 325431;

-- selectJobPostingsByType using JOIN
SELECT posting.*, info.salary, jl.visaRequirement
from JobPosting posting
         JOIN JobInfo info on info.companyID = posting.companyID
    AND info.positionName = posting.positionName
         JOIN JobLocation jl on posting.location = jl.location
WHERE posting.jobType = 'Internship';

-- selectJobPostingByJobID using JOIN
select posting.*, info.salary, jl.visaRequirement
from JobPosting posting
         JOIN JobInfo info on info.companyID = posting.companyID
    AND info.positionName = posting.positionName
         JOIN JobLocation jl on posting.location = jl.location
WHERE posting.jobID = 487382;

-- selectApplicationsByApplicantID using PROJECTION
SELECT application.applicationID as applicationID,
       application.applicantID   as applicantID,
       application.jobID,
       posting.positionName      as positionName,
       c.name                    as companyName,
       application.status        AS status,
       application.dateSubmitted as dateSubmitted,
       application.referralID,
       application.coverLetterID
FROM Application application,
     JobPosting posting,
     Company c
WHERE applicantID = 2201
  AND application.jobID = posting.jobID
  AND posting.companyID = c.companyID;

-- selectApplicationsByApplicationID using PROJECTION
SELECT application.applicationID as applicationID,
       application.applicantID   as applicantID,
       application.jobID,
       posting.positionName      as positionName,
       c.name                    as companyName,
       application.status        AS status,
       application.dateSubmitted as dateSubmitted,
       application.referralID,
       application.coverLetterID
FROM Application application,
     JobPosting posting,
     Company c
WHERE APPLICATIONID = 012201
  AND application.jobID = posting.jobID
  AND posting.companyID = c.companyID;

-- selectApplicantByApplicantID
SELECT *
FROM APPLICANT
WHERE USERID = 2201

-- selectWorkExperienceByApplicantID
SELECT *
FROM WORKEXPERIENCE
WHERE APPLICANTID = 2201

-- selectEducationByApplicantID
SELECT *
FROM EDUCATION
where APPLICANTID = 2201

-- selectApplicationsByCompanyID using PROJECTION
SELECT application.applicationID as applicationID,
       application.applicantID   as applicantID,
       application.jobID,
       posting.positionName      as positionName,
       posting.companyName,
       application.status        AS status,
       application.dateSubmitted as dateSubmitted,
       application.referralID,
       application.coverLetterID
FROM Application application,
     JobPosting posting
WHERE application.jobID = posting.jobID
  AND posting.companyID = 325431;

-- UPDATE applicant query
UPDATE Applicant
SET name               = 'Danny Wu',
    address            = '101 Bay Street Toronto Ontario Canada',
    phoneNumber        = '7789525626',
    email              = 'danny22321@gmail.com',
    requireVisaSponsor = 1
WHERE userId = 2201;

-- UPDATE work experience
SELECT *
FROM WORKEXPERIENCE;
UPDATE WorkExperience
SET startDate   = '20220101',
    endDate     = '20220530',
    description = 'worked on squid game launch'
WHERE applicantID = 2205
  AND companyName = 'Netflix'
  AND jobTitle = 'Product Management Intern';

-- UPDATE job posting
UPDATE JobPosting
SET datePosted   = ?,
    deadline     = ?,
    positionName = ?,
    companyID    = ?,
    companyName  = ?,
    jobType      = ?,
    recruiterID  = ?,
    skills       = ?,
    location     = ?,
    education    = ?,
    startDate    = ?,
    endDate      = ?
WHERE jobID = ?;

-- AGGREGATION WITH GROUP BY: find the average pay in each jobType
SELECT ROUND(AVG(info.salary), 0) AS average, posting.jobType
FROM JobInfo info,
     JobPosting posting
WHERE posting.positionName = info.positionName
  AND posting.companyID = info.companyID
GROUP BY posting.jobType;

-- AGGREGATION WITH HAVING: find applicant with more than 2 previous internships
SELECT DISTINCT applicant.name, posting.RECRUITERID
FROM Applicant applicant,
     Application application,
     JobPosting posting
WHERE posting.RECRUITERID = 100004
  AND application.APPLICANTID = applicant.USERID
  AND application.JOBID = posting.JOBID
  AND applicant.userID IN (SELECT exp.applicantID
                           FROM WorkExperience exp
                           GROUP BY exp.applicantID
                           HAVING COUNT(*) > 2);


-- NESTED AGGREGATION WITH GROUP BY: find the count of job postings by each company with a salary higher than the average of all postings
SELECT companyName, COUNT(*)
FROM JobPosting p
         NATURAL JOIN JobInfo info
WHERE info.salary > (SELECT AVG(salary)
                     FROM JobPosting
                              NATURAL JOIN JobInfo)
GROUP BY companyName;

-- DIVISION: find applicants who have applied to all of a company's job postings
SELECT company.companyID, applicant.userID, applicant.NAME
FROM Applicant applicant,
     Company company
WHERE company.COMPANYID = 325431
  AND NOT EXISTS((SELECT *
                  FROM JobPosting posting
                  WHERE posting.companyID = company.companyID
                    AND NOT EXISTS(SELECT *
                                   FROM Application application
                                   WHERE posting.jobID = application.jobID
                                     AND applicant.userID = application.applicantID)));
