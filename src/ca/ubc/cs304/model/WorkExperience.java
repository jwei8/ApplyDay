package ca.ubc.cs304.model;

import java.sql.Date;

public class WorkExperience {
    public int applicantID;
    public String companyName;
    public String jobTitle;
    public Date startDate;
    public Date endDate;
    public String description;

    public WorkExperience(int applicantID, String companyName, String jobTitle, Date startDate, Date endDate, String description) {
        this.applicantID = applicantID;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getApplicantID() { return applicantID; }

    public String getCompanyName() { return companyName; }

    public String getJobTitle() { return jobTitle; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

    public String getDescription() { return description; }
}
