package ca.ubc.cs304.model;

import java.sql.Date;

public class Application {
    public int applicationID;
    public String status;
    public String positionName;
    public String company;
    public Date dateSubmitted;
    public int applicantID;
    public int referralID;
    public int jobID;
    public int coverLetterID;

    public Application(int applicationID, String status, String positionName, String company, Date dateSubmitted, int applicantID, int referralID, int jobID, int coverLetterID) {
        this.applicationID = applicationID;
        this.status = status;
        this.positionName = positionName;
        this.company = company;
        this.dateSubmitted = dateSubmitted;
        this.applicantID = applicantID;
        this.referralID = referralID;
        this.jobID = jobID;
        this.coverLetterID = coverLetterID;
    }

    public int getApplicationID() {return applicationID;}

    public String getStatus() {return status;}

    public Date getDateSubmitted() {return dateSubmitted;}

    public int getApplicantID() {return applicantID;}

    public int getReferralID() {return referralID;}

    public int getJobID() {return jobID;}

    public int getCoverLetterID() {return coverLetterID;}
}