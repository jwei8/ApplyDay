package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkDayDelegate {
    void login(String username, String password);

    boolean checkApplicantLogin(int userId, String password);

    boolean checkRecruiterLogin(int userId, String password);

    boolean checkCompanyLogin(int userId, String password);

    ArrayList<Application> selectApplicationsByApplicantID(int applicantID);

    ArrayList<JobPosting> selectAllJobPostings();

    ArrayList<JobPosting> selectJobPostingsByCompanyID(int companyID);

    JobPosting selectJobPostingByJobID(int jobID);

    void insertJobPosting(JobPosting jobPosting) throws SQLException;

    void updateJobPosting(JobPosting jobPosting, JobPosting currentJobPosting);

    void deleteJobPosting(int jobID) throws SQLException;

    ArrayList<JobPosting> selectJobPostingsByType(String jobType);

    int findRecruiterCompanyID(int recruiterID);

    JobTypeAverages findAveragePayByJobType();

    ArrayList<Application> selectApplicationsByCompanyID(int companyID);

    ArrayList<String> findApplicantsAppliedToAllPostingsForACompany(int companyID);

    ArrayList<String> findApplicantWithMultiplePrevInternships(int companyID);

    ArrayList<JobPayAboveAverageData> countAboveAverageSalaryJobPostingForEachCompany();

    Applicant selectApplicantByApplicantID(int applicantID);

    Company selectCompanyByCompanyID(int parseInt);

    Application selectApplicationByApplicationID(int applicationID);

    ArrayList<WorkExperience> selectWorkExperienceByApplicantID(int applicantID);

    ArrayList<Education> selectEducationByApplicantID(int applicantID);
}
