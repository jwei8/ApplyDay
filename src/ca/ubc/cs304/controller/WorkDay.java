package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.Home;
import ca.ubc.cs304.ui.OracleLogin;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class references code from the Sample Project given to us in class/tutorial
 * <p>
 * Main Controller that controls everything/runs the program
 */

public class WorkDay implements WorkDayDelegate {
    private DatabaseConnectionHandler databaseHandler;
    private OracleLogin loginWindow = null;

    public WorkDay() {
        databaseHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new OracleLogin();
        loginWindow.showFrame(this);
    }

    /**
     * Main method called at launch time
     */
    public static void main(String[] args) {
        WorkDay workday = new WorkDay();
        workday.start();
    }

    @Override
    public void login(String username, String password) {
        boolean didConnect = databaseHandler.login(username, password);

        if (didConnect) {
            loginWindow.dispose();
            new Home(this);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have reached the maximum allowed number of attempts");
                System.exit(-1);
            }
        }
    }

    @Override
    public boolean checkApplicantLogin(int userId, String password) {
        return databaseHandler.checkApplicantLogin(userId, password);
    }

    @Override
    public boolean checkRecruiterLogin(int userId, String password) {
        return databaseHandler.checkRecruiterLogin(userId, password);

    }

    @Override
    public boolean checkCompanyLogin(int userId, String password) {
        return databaseHandler.checkCompanyLogin(userId, password);
    }

    @Override
    public ArrayList<Application> selectApplicationsByApplicantID(int applicantID) {
        return databaseHandler.selectApplicationsByApplicantID(applicantID);
    }

    @Override
    public ArrayList<JobPosting> selectAllJobPostings() {
        return databaseHandler.selectAllJobPostings();
    }

    @Override
    public ArrayList<JobPosting> selectJobPostingsByCompanyID(int companyID) {
        return databaseHandler.selectJobPostingsByCompanyID(companyID);
    }

    @Override
    public JobPosting selectJobPostingByJobID(int jobID) {
        return databaseHandler.selectJobPostingByJobID(jobID);
    }

    @Override
    public void insertJobPosting(JobPosting jobPosting) throws SQLException {
        databaseHandler.insertJobPosting(jobPosting);
    }

    @Override
    public void updateJobPosting(JobPosting jobPosting, JobPosting currentJobPosting) {
        databaseHandler.updateJobPosting(jobPosting, currentJobPosting);

    }

    @Override
    public void deleteJobPosting(int jobID) throws SQLException {
        databaseHandler.deleteJobPosting(jobID);
    }

    @Override
    public JobTypeAverages findAveragePayByJobType() {
        return databaseHandler.findAveragePayByJobType();
    }

    @Override
    public ArrayList<JobPosting> selectJobPostingsByType(String jobType) {
        return databaseHandler.selectJobPostingsByType(jobType);
    }

    @Override
    public int findRecruiterCompanyID(int recruiterID) {
        return databaseHandler.findRecruiterCompanyID(recruiterID);
    }

    @Override
    public ArrayList<Application> selectApplicationsByCompanyID(int companyID) {
        return databaseHandler.selectApplicationsByCompanyID(companyID);
    }

    @Override
    public ArrayList<String> findApplicantsAppliedToAllPostingsForACompany(int companyID) {
        return databaseHandler.findApplicantsAppliedToAllPostingsForACompany(companyID);
    }

    @Override
    public ArrayList<String> findApplicantWithMultiplePrevInternships(int companyID) {
        return databaseHandler.findApplicantWithMultiplePrevInternships(companyID);
    }

    @Override
    public ArrayList<JobPayAboveAverageData> countAboveAverageSalaryJobPostingForEachCompany() {
        return databaseHandler.countAboveAverageSalaryJobPostingForEachCompany();
    }

    @Override
    public Applicant selectApplicantByApplicantID(int applicantID) {
        return databaseHandler.selectApplicantByApplicantID(applicantID);
    }

    @Override
    public Company selectCompanyByCompanyID(int companyID) {
        return databaseHandler.selectCompanyByCompanyID(companyID);
    }

    @Override
    public Application selectApplicationByApplicationID(int applicationID) {
        return databaseHandler.selectApplicationByApplicationID(applicationID);
    }

    @Override
    public ArrayList<WorkExperience> selectWorkExperienceByApplicantID(int applicantID) {
        return databaseHandler.selectWorkExperienceByApplicantID(applicantID);
    }

    @Override
    public ArrayList<Education> selectEducationByApplicantID(int applicantID) {
        return databaseHandler.selectEducationByApplicantID(applicantID);
    }
}