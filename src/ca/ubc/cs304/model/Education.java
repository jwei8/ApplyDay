package ca.ubc.cs304.model;

import java.sql.Date;

public class Education {
    public int applicantID;
    public String schoolName;
    public String degree;
    public String major;
    public Date startDate;
    public Date endDate;

    public int gpa;

    public Education(int applicantID, String schoolName, String degree, String major, Date startDate, Date endDate, int gpa) {
        this.applicantID = applicantID;
        this.schoolName = schoolName;
        this.degree = degree;
        this.major = major;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gpa = gpa;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getGpa() {
        return gpa;
    }
}
