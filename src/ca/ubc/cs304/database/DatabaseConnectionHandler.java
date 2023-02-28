package ca.ubc.cs304.database;

import ca.ubc.cs304.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class references code from the Sample Project given to us in class/tutorial
 * <p>
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    //check login credential
    public boolean checkApplicantLogin(int userId, String password) {
        try {
            String query = "SELECT COUNT( * ) AS count from APPLICANT WHERE userID = ? AND password = ?";
            PreparedStatement result = connection.prepareStatement(query);
            result.setInt(1, userId);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                return rs.getInt("count") == 1;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkRecruiterLogin(int userId, String password) {
        try {
            String query = "SELECT COUNT( * ) AS count from Recruiter WHERE recruiterID = ? AND password = ?";
            PreparedStatement result = connection.prepareStatement(query);
            result.setInt(1, userId);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                return rs.getInt("count") == 1;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkCompanyLogin(int userId, String password) {
        try {
            String query = "SELECT COUNT( * ) AS count from Company WHERE companyID = ? AND password = ?";
            PreparedStatement result = connection.prepareStatement(query);
            result.setInt(1, userId);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                return rs.getInt("count") == 1;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


    /**
     * Insert Operation – Provide an interface for the user to specify some input for
     * the insert operation. You can choose which table the user should insert to but
     * you cannot hardcode any values.
     * For example, you may choose to have a GUI that allows a fictional user to enter
     * in details for a new employee. The table to insert to has been predetermined
     * (the employee table) but the various details that are to be inserted should be
     * dependent on the user of the GUI.
     */
    public void insertJobPosting(JobPosting jobPosting) throws SQLException {
        try {
            String insertJobPostingQuery = "INSERT INTO JobPosting VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String insertJobInfoQuery = "INSERT INTO JobInfo VALUES (?, ?, ?)";
            String jobLocationQuery = "INSERT INTO JobLocation VALUES (?, ?)";
            String selectJobLocationQuery = "SELECT * FROM JobLocation WHERE location = ? ";

            PreparedStatement info = connection.prepareStatement(insertJobInfoQuery);
            info.setString(1, jobPosting.positionName);
            info.setInt(2, jobPosting.companyID);
            info.setInt(3, jobPosting.salary);
            info.executeUpdate();

            // visaRequirement = 1 means must be citizen
            PreparedStatement selectLocation = connection.prepareStatement(selectJobLocationQuery);
            ResultSet rs = selectLocation.executeQuery();
            if (!rs.next()) {
                PreparedStatement location = connection.prepareStatement(jobLocationQuery);
                location.setString(1, jobPosting.location);
                location.setInt(2, jobPosting.visaRequirement);
                location.executeUpdate();
            }

            PreparedStatement posting = connection.prepareStatement(insertJobPostingQuery);
            posting.setInt(1, jobPosting.jobID);
            posting.setDate(2, jobPosting.datePosted);
            posting.setDate(3, jobPosting.deadline);
            posting.setString(4, jobPosting.positionName);
            posting.setInt(5, jobPosting.companyID);
            posting.setString(6, jobPosting.companyName);
            posting.setInt(7, jobPosting.recruiterID);
            posting.setString(8, jobPosting.jobType);
            posting.setString(9, jobPosting.skills);
            posting.setString(10, jobPosting.location);
            posting.setString(11, jobPosting.education);
            posting.setDate(12, jobPosting.startDate);
            posting.setDate(13, jobPosting.endDate);

            posting.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.printf(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

//    public void insertApplication(Application Application) throws SQLException {
//        try {
//            String insertQuery = "INSERT INTO Application VALUES (?,?,?,?,?,?,?)";
//            PreparedStatement posting = connection.prepareStatement(insertQuery);
//            posting.setInt(1, Application.getApplicationID());
//            posting.setString(2, Application.getStatus());
//            posting.setDate(3, Application.getDateSubmitted());
//            posting.setInt(4, Application.getApplicantID());
//            posting.setInt(5, Application.getReferralID());
//            posting.setInt(6, Application.getJobID());
//            posting.setInt(7, Application.getCoverLetterID());
//        } catch (SQLException e) {
//            connection.rollback();
//            System.out.printf(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }

//    public void insertWorkExperience(WorkExperience workExperience) throws SQLException {
//        try {
//            String insertQuery = "INSERT INTO WorkExperience VALUES (?,?,?,?,?,?)";
//            PreparedStatement experience = connection.prepareStatement(insertQuery);
//            experience.setInt(1, workExperience.applicantID);
//            experience.setString(2, workExperience.companyName);
//            experience.setString(3, workExperience.jobTitle);
//            experience.setDate(4, workExperience.startDate);
//            experience.setDate(5, workExperience.endDate);
//        } catch (SQLException e) {
//            connection.rollback();
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }

    /**
     * Delete Operation – Implement a cascade-on-delete situation.  Provide an
     * interface for the user to specify some input for the deletion operation. You can
     * choose which table to delete from but you cannot specify which tuple to delete.
     * <p>
     * For example, you can allow your fictional user to delete an employee (so you
     * predetermine the delete operation is for the Employee table) but you cannot
     * predetermine which employee is to be deleted.
     * <p>
     * delete job posting
     **/
    public void deleteJobPosting(int jobID) throws SQLException {
        try {
            String deleteQuery = "DELETE FROM JobPosting WHERE jobID = ?";
            PreparedStatement posting = connection.prepareStatement(deleteQuery);
            posting.setInt(1, jobID);
            int countOfRow = posting.executeUpdate();
            if (countOfRow == 0) {
                System.out.printf(WARNING_TAG + " Job Posting " + jobID + "is not valid/does not exist");
            }
            connection.commit();
            posting.close();

        } catch (SQLException e) {
            connection.rollback();
            System.out.printf(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    /**
     * Selection
     * create one query of this category and provide an interface for the user
     * to specific the values of selection conditions to be returned
     */
    public ArrayList<JobPosting> selectAllJobPostings() {
        ArrayList<JobPosting> result = new ArrayList<>();
        try {
            String selectQuery = "select posting.*, info.salary, jl.visaRequirement " +
                    "from JobPosting posting " +
                    "JOIN JobInfo info on info.companyID = posting.companyID  " +
                    "AND info.positionName = posting.positionName " +
                    "JOIN JobLocation jl on posting.location = jl.location ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            formatJobPostingResult(result, posting);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

    public int findRecruiterCompanyID(int recruiterID) {
        try {
            String selectQuery = "SELECT companyID FROM RECRUITER WHERE RECRUITERID = ? ";

            PreparedStatement companyID = connection.prepareStatement(selectQuery);
            companyID.setInt(1, recruiterID);

            ResultSet rs = companyID.executeQuery();

            while (rs.next()) {
                return rs.getInt("companyID");
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return 0;
    }

    public ArrayList<JobPosting> selectJobPostingsByCompanyID(int companyID) {
        ArrayList<JobPosting> result = new ArrayList<>();
        try {
            String selectQuery = "select posting.*, info.salary, jl.visaRequirement " +
                    "from JobPosting posting " +
                    "JOIN JobInfo info on info.companyID = posting.companyID " +
                    "AND info.positionName = posting.positionName " +
                    "JOIN JobLocation jl on posting.location = jl.location " +
                    "WHERE posting.companyID = ? ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            posting.setInt(1, companyID);
            formatJobPostingResult(result, posting);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

    public ArrayList<JobPosting> selectJobPostingsByType(String jobType) {
        ArrayList<JobPosting> result = new ArrayList<>();
        try {
            String selectQuery = "SELECT posting.*, info.salary, jl.visaRequirement " +
                    "from JobPosting posting " +
                    "JOIN JobInfo info on info.companyID = posting.companyID " +
                    "AND info.positionName = posting.positionName " +
                    "JOIN JobLocation jl on posting.location = jl.location " +
                    "WHERE posting.jobType = ? ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            posting.setString(1, jobType);
            formatJobPostingResult(result, posting);

        } catch (SQLException e) {
            System.out.println("error in selecting by job type");
        }
        return result;
    }

    public JobPosting selectJobPostingByJobID(int jobID) {
        ArrayList<JobPosting> result = new ArrayList<>();
        try {
            String selectQuery = "select posting.*, info.salary, jl.visaRequirement " +
                    "from JobPosting posting " +
                    "JOIN JobInfo info on info.companyID = posting.companyID " +
                    "AND info.positionName = posting.positionName " +
                    "JOIN JobLocation jl on posting.location = jl.location " +
                    "WHERE posting.jobID = ? ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            posting.setInt(1, jobID);
            formatJobPostingResult(result, posting);

        } catch (SQLException e) {
            System.out.println("error in selecting by job type");
        }
        return result.get(0);
    }

    public ArrayList<Application> selectApplicationsByApplicantID(int applicantID) {
        ArrayList<Application> result = new ArrayList<>();
        try {
            String selectQuery = "SELECT application.applicationID as applicationID, application.applicantID as applicantID, application.jobID, posting.positionName as positionName, c.name as companyName, " +
                    "application.status AS status, application.dateSubmitted as dateSubmitted, application.referralID, application.coverLetterID " +
                    "FROM Application application, JobPosting posting, Company c " +
                    "WHERE applicantID = ? AND application.jobID = posting.jobID AND posting.companyID = c.companyID ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            posting.setInt(1, applicantID);
            ResultSet rs = posting.executeQuery();

            while (rs.next()) {
                Application application = new Application(
                        rs.getInt("applicationID"),
                        rs.getString("status"),
                        rs.getString("positionName"),
                        rs.getString("companyName"),
                        rs.getDate("dateSubmitted"),
                        applicantID,
                        rs.getInt("referralID"),
                        rs.getInt("jobID"),
                        rs.getInt("coverLetterID")
                );
                result.add(application);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Applicant selectApplicantByApplicantID(int applicantID) {
        try {
            String selectQuery = "SELECT * FROM Applicant WHERE userID = ? ";
            PreparedStatement applicant = connection.prepareStatement(selectQuery);
            applicant.setInt(1, applicantID);
            ResultSet rs = applicant.executeQuery();

            while (rs.next()) {
                return new Applicant(rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("address"),
                        "",
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getInt("requireVisaSponsor"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Company selectCompanyByCompanyID(int companyID) {
        try {
            String selectQuery = "SELECT * FROM Company WHERE companyID = ?";

            PreparedStatement company = connection.prepareStatement(selectQuery);
            company.setInt(1, companyID);
            ResultSet rs = company.executeQuery();

            while (rs.next()) {
                return new Company(rs.getInt("companyID"),
                        rs.getString("name"),
                        rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ArrayList<WorkExperience> selectWorkExperienceByApplicantID(int applicantID) {
        ArrayList<WorkExperience> experiences = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM WorkExperience WHERE applicantID = ? ";
            PreparedStatement applicant = connection.prepareStatement(selectQuery);
            applicant.setInt(1, applicantID);
            ResultSet rs = applicant.executeQuery();

            while (rs.next()) {
                WorkExperience experience =  new WorkExperience(rs.getInt("applicantID"),
                        rs.getString("companyName"),
                        rs.getString("jobTitle"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("description"));
                experiences.add(experience);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return experiences;
    }

    public ArrayList<Education> selectEducationByApplicantID(int applicantID) {
        ArrayList<Education> edus = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM Education WHERE applicantID = ? ";
            PreparedStatement applicant = connection.prepareStatement(selectQuery);
            applicant.setInt(1, applicantID);
            ResultSet rs = applicant.executeQuery();

            while (rs.next()) {
                Education education =  new Education(rs.getInt("applicantID"),
                        rs.getString("schoolName"),
                        rs.getString("degree"),
                        rs.getString("major"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getInt("gpa"));
                edus.add(education);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return edus;
    }

    public Application selectApplicationByApplicationID(int applicationID) {
        ArrayList<Application> result = new ArrayList<>();
        try {
            String selectQuery = "SELECT application.applicationID as applicationID, application.applicantID as applicantID, application.jobID, posting.positionName as positionName, c.name as companyName, " +
                    "application.status AS status, application.dateSubmitted as dateSubmitted, application.referralID, application.coverLetterID " +
                    "FROM Application application, JobPosting posting, Company c " +
                    "WHERE application.applicationID = ? AND application.jobID = posting.jobID AND posting.companyID = c.companyID ";
            PreparedStatement posting = connection.prepareStatement(selectQuery);
            posting.setInt(1, applicationID);
            ResultSet rs = posting.executeQuery();

            while (rs.next()) {
                Application application = new Application(
                        rs.getInt("applicationID"),
                        rs.getString("status"),
                        rs.getString("positionName"),
                        rs.getString("companyName"),
                        rs.getDate("dateSubmitted"),
                        rs.getInt("applicantID"),
                        rs.getInt("referralID"),
                        rs.getInt("jobID"),
                        rs.getInt("coverLetterID")
                );
                result.add(application);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result.get(0);
    }

    public ArrayList<Application> selectApplicationsByCompanyID(int companyID) {
        ArrayList<Application> result = new ArrayList<>();
        try {
            String selectQuery = "SELECT application.applicationID as applicationID, application.applicantID as applicantID, application.jobID, posting.positionName as positionName, posting.companyName, " +
                    "application.status AS status, application.dateSubmitted as dateSubmitted, application.referralID, application.coverLetterID " +
                    "FROM Application application, JobPosting posting " +
                    "WHERE application.jobID = posting.jobID AND posting.companyID = ? ";
            PreparedStatement applications = connection.prepareStatement(selectQuery);
            applications.setInt(1, companyID);
            ResultSet rs = applications.executeQuery();

            while (rs.next()) {
                Application application = new Application(
                        rs.getInt("applicationID"),
                        rs.getString("status"),
                        rs.getString("positionName"),
                        rs.getString("companyName"),
                        rs.getDate("dateSubmitted"),
                        rs.getInt("applicantID"),
                        rs.getInt("referralID"),
                        rs.getInt("jobID"),
                        rs.getInt("coverLetterID")
                );
                result.add(application);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void formatJobPostingResult(ArrayList<JobPosting> result, PreparedStatement posting) throws SQLException {
        ResultSet rs = posting.executeQuery();
        while (rs.next()) {
            JobPosting post = new JobPosting(
                    rs.getInt("jobID"),
                    rs.getDate("datePosted"),
                    rs.getDate("deadline"),
                    rs.getString("positionName"),
                    rs.getInt("companyID"),
                    rs.getString("companyName"),
                    rs.getString("jobType"),
                    rs.getInt("recruiterID"),
                    rs.getString("skills"),
                    rs.getString("location"),
                    rs.getString("education"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getInt("salary"),
                    rs.getInt("visaRequirement")

            );
            result.add(post);
        }
    }

    /**
     * Aggregation with Group By – Create one query that requires the use of distinct
     * aggregation (min, max, average, or count are all fine), and provide an interface
     * (e.g., HTML button/dropdown, etc.) for the user to execute this query.
     * You can hardcode this query (i.e., you do not have to allow for user input) but
     * you must display the tuples in the result within the GUI. The resulting tuples
     * should be presented in a clear manner (e.g., if you use a table to display the
     * results, there should be column headings that give context to the displayed
     * tuples).
     * <p>
     * aggregation with groupby and AVG to find the average pay in each jobType
     */
    public JobTypeAverages findAveragePayByJobType() {
        JobTypeAverages averages;
        try {
            String query = "SELECT ROUND(AVG(info.salary),0), posting.jobType " +
                    "FROM JobInfo info, JobPosting posting " +
                    "WHERE posting.positionName = info.positionName " +
                    "AND posting.companyID = info.companyID " +
                    "GROUP BY posting.jobType ";
            PreparedStatement posting = connection.prepareStatement(query);
            ResultSet result = posting.executeQuery();
            result.next();
            int averageInternship = result.getInt(1);
            result.next();
            int averageFullTime = result.getInt(1);
            averages = new JobTypeAverages(
                    averageInternship,
                    averageFullTime
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return averages;
    }

    /**
     * Aggregation with Having – Create one meaningful query that requires the use
     * of a HAVING clause, and provide an interface (e.g., HTML button/dropdown,
     * etc.) for the user to execute this query.
     * You can hardcode this query (i.e., you do not have to allow for user input) but
     * you must display the tuples in the result within the GUI. The resulting tuples
     * should be presented in a clear manner (e.g., if you use a table to display the
     * results, there should be column headings that give context to the displayed
     * tuples).
     * <p>
     * aggregation with having to find applicant name with more than 2 previous internships
     **/
    public ArrayList<String> findApplicantWithMultiplePrevInternships(int companyID) {
        ArrayList<String> applicantNames = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT applicant.name " +
                    "FROM Applicant applicant, " +
                    "     Application application, " +
                    "     JobPosting posting " +
                    "WHERE posting.companyID = ? " +
                    "  AND application.APPLICANTID = applicant.USERID " +
                    "  AND application.JOBID = posting.JOBID " +
                    "  AND applicant.userID IN (SELECT exp.applicantID " +
                    "                           FROM WorkExperience exp " +
                    "                           GROUP BY exp.applicantID " +
                    "                           HAVING COUNT(*) > 2) ";
            PreparedStatement name = connection.prepareStatement(query);
            name.setInt(1, companyID);
            ResultSet result = name.executeQuery();
            while (result.next()) {
                String applicantName = result.getString("name");
                applicantNames.add(applicantName);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }

        return applicantNames;
    }

    // DIVISION: find applicants who have applied to all of a company's job postings
    public ArrayList<String> findApplicantsAppliedToAllPostingsForACompany(int companyID) {
        ArrayList<String> applicantNames = new ArrayList<>();
        try {
            String query = "SELECT company.companyID, applicant.userID, applicant.name " +
                    "FROM Applicant applicant, " +
                    "     Company company " +
                    "WHERE company.COMPANYID = ? AND NOT EXISTS((SELECT * " +
                    "                  FROM JobPosting posting" +
                    "                  WHERE posting.companyID = company.companyID " +
                    "                    AND NOT EXISTS(SELECT * " +
                    "                                   FROM Application application " +
                    "                                   WHERE posting.jobID = application.jobID " +
                    "                                     AND applicant.userID = application.applicantID)))";
            PreparedStatement applicant = connection.prepareStatement(query);
            applicant.setInt(1, companyID);
            ResultSet result = applicant.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                applicantNames.add(name);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return applicantNames;
    }

    public ArrayList<JobPayAboveAverageData> countAboveAverageSalaryJobPostingForEachCompany() {
        ArrayList<JobPayAboveAverageData> results = new ArrayList<>();
        try {
            String query = "SELECT companyName, COUNT(*) as count " +
                    "    FROM JobPosting p " +
                    "    NATURAL JOIN JobInfo info " +
                    "    WHERE info.salary > (SELECT AVG(salary) " +
                    "    FROM JobPosting " +
                    "    NATURAL JOIN JobInfo) " +
                    "    GROUP BY companyName ";
            PreparedStatement posting = connection.prepareStatement(query);
            ResultSet result = posting.executeQuery();
            while (result.next()) {
                JobPayAboveAverageData data = new JobPayAboveAverageData(
                        result.getString("companyName"),
                        result.getInt("count")
                );
                results.add(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }


    /**
     * Update Operation – Provide an interface for the user to specify some input for
     * the update operation. You can choose which table to update but the user
     * should be able to specify which attribute(s) in that table to update the value(s)
     * for.
     * <p>
     * For example, you can allow your fictional user to update information for an
     * employee (so you predetermine the update operation is for the Employee table)
     * but you cannot predetermine which employee is to be updated and what the
     * new information is.
     * <p>
     * change name and address in applicant
     * change job posting details
     **/

    public void updateJobPosting(JobPosting jobPosting, JobPosting currentJobPosting) {
        try {
            String insertJobInfoQuery = "UPDATE JobInfo info SET salary = ?, info.positionName = ? WHERE info.positionName = ? AND info.COMPANYID = ?";
            PreparedStatement info = connection.prepareStatement(insertJobInfoQuery);
            info.setInt(1, jobPosting.salary);
            info.setString(2, jobPosting.positionName);
            info.setString(3, currentJobPosting.positionName);
            info.setInt(4, currentJobPosting.companyID);
            info.executeUpdate();

            String query = "UPDATE JobPosting SET datePosted = ?, deadline = ?, positionName = ?, companyID = ?, companyName = ?, jobType = ?, recruiterID = ?, skills = ?, location = ?, education = ?, startDate = ?, endDate =  ?" +
                    "WHERE jobID = ?";
            PreparedStatement posting = connection.prepareStatement(query);
            posting.setDate(1, jobPosting.datePosted);
            posting.setDate(2, jobPosting.deadline);
            posting.setString(3, jobPosting.positionName);
            posting.setInt(4, jobPosting.companyID);
            posting.setString(5, jobPosting.companyName);
            posting.setString(6, jobPosting.jobType);
            posting.setInt(7, jobPosting.recruiterID);
            posting.setString(8, jobPosting.skills);
            posting.setString(9, jobPosting.location);
            posting.setString(10, jobPosting.education);
            posting.setDate(11, jobPosting.startDate);
            posting.setDate(12, jobPosting.endDate);
            posting.setInt(13, jobPosting.jobID);

            int rowCount = posting.executeUpdate();

            if (rowCount == 0)
                System.out.println(WARNING_TAG + "Job Posting for " + jobPosting.jobID + " does not exist");
            connection.commit();
            posting.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    public void updateProfile(Applicant applicant) {
        try {
            String query = "UPDATE Applicant SET name = ?, address = ?, phoneNumber = ?, email = ?, requireVisaSponsor = ?" +
                    "WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, applicant.getName());
            stmt.setString(2, applicant.getAddress());
            stmt.setString(3, applicant.getPhoneNumber());
            stmt.setString(4, applicant.getEmail());
            stmt.setInt(5, applicant.getRequireVisaSponsor());
            stmt.setInt(6, applicant.getUserID());

            int rowCount = stmt.executeUpdate();

            if (rowCount == 0) System.out.println(WARNING_TAG + "Applicant" + applicant.userID + "does not exist");
            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateWorkExperience(WorkExperience experience) {
        try {
            String query = "UPDATE WorkExperience SET startDate = ?, endDate = ?, description = ?" +
                    "WHERE applicantID = ? AND companyName = ? AND jobTitle = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, experience.getStartDate());
            stmt.setDate(2, experience.getEndDate());
            stmt.setString(3, experience.getDescription());
            stmt.setInt(4, experience.getApplicantID());
            stmt.setString(5, experience.getCompanyName());
            stmt.setString(6, experience.getJobTitle());

            int rowCount = stmt.executeUpdate();

            if (rowCount == 0)
                System.out.println(WARNING_TAG + "Work Experience for" + experience.applicantID + "does not exist");
            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
