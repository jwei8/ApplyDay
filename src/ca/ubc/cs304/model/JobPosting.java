package ca.ubc.cs304.model;

import java.sql.Date;

public class JobPosting {
    public int jobID;
    public Date datePosted;
    public Date deadline;
    public String positionName;
    public int companyID;
    public String companyName;
    public String jobType;
    public int recruiterID;
    public String skills;
    public String location;
    public String education;
    public Date startDate;
    public Date endDate;
    public int salary;
    public int visaRequirement;

    public JobPosting(int jobID, Date datePosted, Date deadline, String positionName, int companyID, String companyName,
                      String jobType, int recruiterID, String skills, String location, String education, Date startDate,
                      Date endDate, int salary, int visaRequirement) {
        this.jobID = jobID;
        this.datePosted = datePosted;
        this.deadline = deadline;
        this.positionName = positionName;
        this.companyID = companyID;
        this.companyName = companyName;
        this.jobType = jobType;
        this.recruiterID = recruiterID;
        this.skills = skills;
        this.location = location;
        this.education = education;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
        this.visaRequirement = visaRequirement;
    }
}


