DROP TABLE JobLocation;
DROP TABLE ApplicationHasWork;
DROP TABLE WorkExperience;
DROP TABLE ApplicationHasEducation;
DROP TABLE Education;
DROP TABLE JobInfo;
DROP TABLE JobRecruiter;
DROP TABLE Recruiter;
DROP TABLE ReferralBonus;
DROP TABLE Application;
DROP TABLE CoverLetter;
DROP TABLE Applicant;
DROP TABLE JobPosting;
DROP TABLE Referral;
DROP TABLE Company;

CREATE TABLE Applicant(
                          userID INTEGER PRIMARY KEY,
                          name varchar2(200) NOT NULL,
                          address varchar2(200),
                          password varchar2(200) NOT NULL,
                          phoneNumber varchar2(200),
                          email varchar2(200) UNIQUE NOT NULL,
                          requireVisaSponsor INTEGER
);

CREATE TABLE JobPosting(
                           jobID INTEGER PRIMARY KEY,
                           datePosted DATE,
                           deadline DATE NOT NULL,
                           positionName varchar2(100),
                           companyID INTEGER NOT NULL,
                           companyName varchar2(100),
                           recruiterID INTEGER NOT NULL,
                           jobType varchar2(100) NOT NULL,
                           skills varchar2(500),
                           location varchar2(100),
                           education varchar2(100),
                           startDate DATE,
                           endDate DATE
);


CREATE TABLE JobLocation(
                            location varchar2(80) PRIMARY KEY,
                            visaRequirement INTEGER NOT NULL
);

CREATE TABLE Company(
                        companyID INTEGER PRIMARY KEY,
                        name varchar2(80) NOT NULL,
                        address varchar2(80) NOT NULL,
                        password varchar2(80) NOT NULL
);

CREATE TABLE ReferralBonus(
                              companyID INTEGER PRIMARY KEY,
                              signOnBonus INTEGER NOT NULL,
                              FOREIGN KEY (companyID) REFERENCES Company(companyID) ON DELETE CASCADE
);

CREATE TABLE JobInfo(
                        positionName varchar2(100),
                        companyID INTEGER NOT NULL,
                        salary INTEGER,
                        PRIMARY KEY (positionName, companyID),
                        FOREIGN KEY (companyID) REFERENCES Company(companyID) ON DELETE CASCADE
);

CREATE TABLE CoverLetter(
                            coverLetterID INTEGER PRIMARY KEY,
                            title varchar2(80) NOT NULL,
                            content CLOB,
                            applicantID INTEGER NOT NULL,
                            FOREIGN KEY (applicantID) REFERENCES Applicant(userID) on DELETE CASCADE
);

CREATE TABLE Recruiter(
                          recruiterID INTEGER PRIMARY KEY,
                          name varchar2(80) NOT NULL,
                          password varchar2(80) NOT NULL,
                          companyID INTEGER NOT NULL,
                          FOREIGN KEY (companyID) REFERENCES Company(companyID) ON DELETE CASCADE
);

CREATE TABLE JobRecruiter(
                             recruiterID INTEGER PRIMARY KEY,
                             location varchar2(50) NOT NULL,
                             FOREIGN KEY (recruiterID) REFERENCES Recruiter(recruiterID) ON DELETE CASCADE
);

CREATE TABLE Referral(
                         referralID INTEGER PRIMARY KEY,
                         nameOfEmployee varchar2(50) NOT NULL,
                         applicationID INTEGER NOT NULL,
                         companyID INTEGER NOT NULL,
                         FOREIGN KEY (companyID) REFERENCES Company(companyID) ON DELETE CASCADE
);

CREATE TABLE Application(
                            applicationID INTEGER PRIMARY KEY,
                            status varchar2(20) NOT NULL,
                            dateSubmitted DATE NOT NULL,
                            applicantID INTEGER NOT NULL,
                            referralID INTEGER,
                            jobID INTEGER NOT NULL,
                            coverLetterID INTEGER,
                            FOREIGN KEY (applicantID) REFERENCES Applicant(userID) ON DELETE CASCADE,
                            FOREIGN KEY (referralID) REFERENCES Referral(referralID) ON DELETE SET NULL,
                            FOREIGN KEY (jobID) REFERENCES JobPosting(jobID) ON DELETE CASCADE,
                            FOREIGN KEY (coverLetterID) REFERENCES CoverLetter(coverLetterID) ON DELETE SET NULL
);

CREATE TABLE WorkExperience(
                               applicantID INTEGER,
                               companyName varchar2(80),
                               jobTitle varchar2(80),
                               startDate DATE NOT NULL,
                               endDate DATE,
                               description varchar2(200),
                               PRIMARY KEY (applicantID, companyName, jobTitle),
                                   FOREIGN KEY (applicantID) REFERENCES Applicant(userID) ON DELETE CASCADE
);

CREATE TABLE Education(
                          applicantID INTEGER,
                          schoolName varchar2(50),
                          degree varchar2(50),
                          major varchar2(50) NOT NULL,
                          gpa INTEGER NOT NULL,
                          endDate DATE,
                          startDate DATE NOT NULL,
                          PRIMARY KEY (applicantID, schoolName, degree),
                            FOREIGN KEY (applicantID) REFERENCES Applicant(userID) ON DELETE CASCADE
);

CREATE TABLE ApplicationHasWork(
                                   applicantID INTEGER,
                                   applicationID INTEGER,
                                   companyName varchar2(80),
                                   jobTitle varchar2(80),
                                   PRIMARY KEY (applicantID, applicationID, companyName, jobTitle),
                                       FOREIGN KEY (applicantID) REFERENCES Applicant(userID) ON DELETE CASCADE,
                                       FOREIGN KEY (applicationID) REFERENCES Application(applicationID) ON DELETE CASCADE,
                                       FOREIGN KEY (applicantID, companyName, jobTitle) REFERENCES WorkExperience(applicantID, companyName, jobTitle) ON DELETE CASCADE
);

CREATE TABLE applicationHasEducation(
                                        applicantID INTEGER,
                                        applicationID INTEGER,
                                        schoolName varchar2(80),
                                        degree varchar2(80),
                                        PRIMARY KEY (applicantID, applicationID, schoolName, degree),
                                            FOREIGN KEY (applicantID) REFERENCES Applicant(userID) ON DELETE CASCADE,
                                            FOREIGN KEY (applicationID) REFERENCES Application(applicationID) ON DELETE CASCADE,
                                            FOREIGN KEY (applicantID, schoolName, degree) REFERENCES Education(applicantID, schoolName, degree) ON DELETE CASCADE
);

--insert applicant
INSERT INTO Applicant VALUES(2201, 'Danny Hu', '101 Bay Street Toronto Ontario Canada', 'Ca123456', '6050402238', 'dannyhuo06@ubc.ca', 1);
INSERT INTO Applicant VALUES (2202, 'Mike Simpson', '5621 West Street, Kingston, Ontario, Canada', 'Canada223', '6040402238', 'ms56226@ubc.ca', 1);
INSERT INTO Applicant VALUES (2203, 'Jason Doo', '101 1st Ave, Vancouver, British Columbia, Canada', 'Password0011', '6043321123', 'jason5542@ubc.ca', 1);
INSERT INTO Applicant VALUES (2204, 'Chirs Mullen', '5621 West Street, Kingston, Ontario, Canada', 'Password2333', '7780402238', 'mullen56226@ubc.ca', 0);
INSERT INTO Applicant VALUES (2205, 'David Thompson', '5621 West Street, Kingston, Ontario, Canada', 'Password2003', '6050455238', 'dt503226@ubc.ca', 0);

--insert jobPosting
INSERT INTO JobPosting VALUES (487382, '20221001', '20221031', 'Software Engineer Intern', 871270, 'Netflix', 100000, 'Internship', 'Full-stack development using Python and C++', 'Mountain View, CA, USA', 'Bachelor''s in Computer Science', '20221105', '20231105');
INSERT INTO JobPosting VALUES (479134, '20220930', '20221007', 'Software Engineer', 280953, 'Meta', 100001, 'Internship', 'Capable of developing both SQL and NoSQL databases', 'Seattle, WA, USA', 'Master''s in Computer Science', '20221015', '20231105');
INSERT INTO JobPosting VALUES (390839, '20221007', '20221107', 'Warehouse Assistant', 423880, 'Amazon', 100002, 'FullTime', 'Can carry heavy boxes up to 50 lbs', 'Menlo Park, CA, USA', 'No requirement', '20201130', NULL);
INSERT INTO JobPosting VALUES (666666, '20221007', '20221107', 'Warehouse Assistant', 423880, 'Amazon', 100002, 'FullTime', 'Can carry heavy boxes up to 50 lbs', 'Menlo Park, CA, USA', 'No requirement', '20201130', NULL);
INSERT INTO JobPosting VALUES (583093, '20221020', '20221101', 'Machine Learning Researcher', 325431, 'Google', 100004, 'Internship', 'Strong experience in machine learning', 'Calgary, AB, Canada', 'PhD in Computer Science', '20221114', '20231105');
INSERT INTO JobPosting VALUES (129683, '20221020', '20221101', 'Supply Chain Program Manager', 325431, 'Google', 100004, 'FullTime', 'Experience with Supply/Demand Planning and Accounting/Finance', 'Calgary, AB, Canada', 'Any Bachelor''s', '20221114', NULL);
INSERT INTO JobPosting VALUES (478762, '20221020', '20221101', 'Account Executive', 325431, 'Google', 100004, 'FullTime', 'Experience in media, marketing or advertising, with a focus on programmatic media sales', 'Calgary, AB, Canada', 'Any Bachelor''s', '20221114', NULL);
INSERT INTO JobPosting VALUES (888643, '20221020', '20221101', 'AI Engineer Intern', 325431, 'Google', 100004, 'Internship', 'Experience in coding languages, data structures, algorithms, and software design', 'Calgary, AB, Canada', 'Bachelor''s in Computer Science', '20221114', '20231105');
INSERT INTO JobPosting VALUES (111998, '20221020', '20221101', 'Partner Support Specialist', 325431, 'Google', 100004, 'FullTime', 'Experience with the Darwin benefits system', 'Calgary, AB, Canada', 'No requirement', '20221114', NULL);

--insert jobLocation
INSERT INTO JobLocation VALUES ('Mountain View, CA, USA', 1);
INSERT INTO JobLocation VALUES ('Calgary, AB, Canada', 0);
INSERT INTO JobLocation VALUES ('Menlo Park, CA, USA', 1);
INSERT INTO JobLocation VALUES ('Seattle, WA, USA', 1);
INSERT INTO JobLocation VALUES ('Vancouver, BC, Canada', 0);

--insert company
INSERT INTO Company VALUES (325431, 'Google', '1600 Amphitheatre Parkway, Mountain View, CA 94043, USA', 'password123');
INSERT INTO Company VALUES (423880, 'Amazon', '410 Terry Ave. N, Seattle, WA 98109, USA', 'password123');
INSERT INTO Company VALUES (280953, 'Meta', '1 Hacker Way, Menlo Park, CA 94025, USA', 'sdjkfhsjf8jnfd');
INSERT INTO Company VALUES (871270, 'Netflix', '100 Winchester Circle, Los Gatos, CA 95032, USA', 'f2j59gdj3i9');
INSERT INTO Company VALUES (016498, 'Apple', 'One Apple Park Way, Cupertino, CA 95014, USA', '9kmjdikc8982j');

--insert referralBonus
INSERT INTO ReferralBonus VALUES (325431, 1000);
INSERT INTO ReferralBonus VALUES (423880, 5000);
INSERT INTO ReferralBonus VALUES (280953, 2000);
INSERT INTO ReferralBonus VALUES (871270, 4000);
INSERT INTO ReferralBonus VALUES (016498, 500);

--insert jobInfo
INSERT INTO JobInfo VALUES ('Software Engineer Intern', 871270, 50000);
INSERT INTO JobInfo VALUES ('Software Engineer', 280953, 170000);
INSERT INTO JobInfo VALUES ('Warehouse Assistant', 423880, 140000);
INSERT INTO JobInfo VALUES ('Machine Learning Researcher', 325431, 60000);
INSERT INTO JobInfo VALUES ('Supply Chain Program Manager', 325431, 80000);
INSERT INTO JobInfo VALUES ('Account Executive', 325431, 100000);
INSERT INTO JobInfo VALUES ('AI Engineer Intern', 325431, 60000);
INSERT INTO JobInfo VALUES ('Partner Support Specialist', 325431, 110000);

--insert coverLetter
INSERT INTO CoverLetter VALUES (382720, 'coverletter for SDE intern', 'Dear Hiring Manager… <omitted>', 2201);
INSERT INTO CoverLetter VALUES (592781, 'coverletter for QA intern', 'I believe I have accumulated… <omitted>', 2202);
INSERT INTO CoverLetter VALUES (672091, 'coverletter for my dream job', 'I''m confident that I''m a great fit… <omitted>', 2203);
INSERT INTO CoverLetter VALUES (187930, 'coverletter for job I like', 'Please consider me as the candidate… <omitted>', 2204);
INSERT INTO CoverLetter VALUES (593819, 'coverletter general', 'I assert that I am the best applicant… <omitted>', 2205);

--insert recruiter
INSERT INTO Recruiter VALUES (100000, 'John Smith', 'johnsmith123', 871270);
INSERT INTO Recruiter VALUES (100001, 'Alice Lee', 'topsecret', 280953);
INSERT INTO Recruiter VALUES (100002, 'Hannah Jackson', 'hannahjackson2345', 423880);
INSERT INTO Recruiter VALUES (100003, 'Jay Hua', '9939494ffadfs', 016498);
INSERT INTO Recruiter VALUES (100004, 'Bob Wilkinson', 'password', 325431);

--insert jobRecruiter
INSERT INTO JobRecruiter VALUES (100000, 'Mountain View, CA, USA');
INSERT INTO JobRecruiter VALUES (100001, 'Seattle, WA, USA');
INSERT INTO JobRecruiter VALUES (100002, 'Menlo Park, CA, USA');
INSERT INTO JobRecruiter VALUES (100003, 'Los Gatos, CA, USA');
INSERT INTO JobRecruiter VALUES (100004, 'Calgary, AB, Canada');

--insert referral
INSERT INTO Referral VALUES (200001, 'Mike Ross', 012201, 871270);
INSERT INTO Referral VALUES (200002, 'Josh Armstrong', 012202, 280953);
INSERT INTO Referral VALUES (200003, 'Jeff Bezos', 012203, 423880);
INSERT INTO Referral VALUES (200004, 'Dan Green', 012204, 325431);
INSERT INTO Referral VALUES (200005, 'Chi Apachi', 012205, 325431);

--insert application
INSERT INTO Application VALUES (012201, 'submitted', '20221001', 2201, 200001, 487382, 382720);
INSERT INTO Application VALUES (012202, 'rejected', '20221101', 2202, 200002, 479134, 592781);
INSERT INTO Application VALUES (012206, 'rejected', '20221101', 2201, NULL, 479134, 592781);
INSERT INTO Application VALUES (012203, 'in progress', '20220901', 2203, 200003, 390839, 672091);
INSERT INTO Application VALUES (012207, 'in progress', '20220901', 2201, NULL, 390839, 672091);
INSERT INTO Application VALUES (012204, 'hired', '20220824', 2204, 200004, 478762, 187930);
INSERT INTO Application VALUES (012205, 'withdrawn', '20220916', 2205, 200005, 583093, 593819);
INSERT INTO Application VALUES (555555, 'submitted', '20220916', 2201, 200005, 583093, 593819);
INSERT INTO Application VALUES (111111, 'submitted', '20220916', 2205, NULL, 129683, 593819);
INSERT INTO Application VALUES (222222, 'submitted', '20220916', 2205, NULL, 478762, 593819);
INSERT INTO Application VALUES (333333, 'submitted', '20220916', 2205, NULL, 888643, 593819);
INSERT INTO Application VALUES (444444, 'submitted', '20220916', 2205, NULL, 111998, 593819);


--insert WorkExperience
INSERT INTO WorkExperience VALUES (2201, 'Google', 'Software Engineering Intern', '20220502', '20220831', 'worked on different google services');
INSERT INTO WorkExperience VALUES (2201, 'Nuro', 'Software Engineering Intern', '20210502', '20210831', 'worked self driving technologies');
INSERT INTO WorkExperience VALUES (2201, 'RobinHood', 'Site Reliability Engineering Intern', '20200502', '20200831', 'worked on web services');
INSERT INTO WorkExperience VALUES (2202, 'SAP', 'Software Engineering Intern', '20220102', '20220831', 'created full stack application');
INSERT INTO WorkExperience VALUES (2202, 'RobinHood', 'Site Reliability Engineering Intern', '20200502', '20200831', 'worked on web services');
INSERT INTO WorkExperience VALUES (2202, 'Netflix', 'Software Engineering Intern', '20210502', '20210831', 'worked self driving technologies');
INSERT INTO WorkExperience VALUES (2203, 'Meta', 'QA Intern', '20220502', '20220821', 'created auto test suits');
INSERT INTO WorkExperience VALUES (2204, 'Amazon', 'Software Engineering Intern', '20220602', '20220831', 'worked on Alexa services');
INSERT INTO WorkExperience VALUES (2205, 'Netflix', 'Product Management Intern', '20220502', '20220831', 'worked with PM to carryout daily scrums');

--insert education
INSERT INTO Education VALUES (2201, 'The University of British Columbia', 'Bachelor of Science', 'Computer Science', 80, '20240530', '20170906');
INSERT INTO Education VALUES (2202, 'Simon Fraser University', 'Bachelor of Science', 'Computer Science', 85, '20230530', '20190906');
INSERT INTO Education VALUES (2203, 'Queen''s University', 'Bachelor of Arts', 'Psychology', 90, '20230531', '20170906');
INSERT INTO Education VALUES (2204, 'The University of Toronto', 'Bachelor of Applied Science', 'Electrical Engineering', 70, '20230530', '20170906');
INSERT INTO Education VALUES (2205, 'The University of British Columbia', 'Bachelor of Science', 'Biology', 82, '20240530', '20210909');

--insert applicationHasWork
INSERT INTO ApplicationHasWork VALUES (2201, 12201, 'Google', 'Software Engineering Intern');
INSERT INTO ApplicationHasWork VALUES (2201, 12201, 'Nuro', 'Software Engineering Intern');
INSERT INTO ApplicationHasWork VALUES (2202, 12202, 'SAP', 'Software Engineering Intern');
INSERT INTO ApplicationHasWork VALUES (2203, 12203, 'Meta', 'QA Intern');
INSERT INTO ApplicationHasWork VALUES (2204, 12204, 'Amazon', 'Software Engineering Intern');
INSERT INTO ApplicationHasWork VALUES (2205, 12205, 'Netflix', 'Product Management Intern');

--insert applicationHasEducation
INSERT INTO ApplicationHasEducation VALUES (2201, 12201, 'The University of British Columbia', 'Bachelor of Science');
INSERT INTO ApplicationHasEducation VALUES (2202, 12202, 'Simon Fraser University', 'Bachelor of Science');
INSERT INTO ApplicationHasEducation VALUES (2203, 12203, 'Queen''s University', 'Bachelor of Arts');
INSERT INTO ApplicationHasEducation VALUES (2204, 12204, 'The University of Toronto', 'Bachelor of Applied Science');
INSERT INTO ApplicationHasEducation VALUES (2205, 12205, 'The University of British Columbia', 'Bachelor of Science');