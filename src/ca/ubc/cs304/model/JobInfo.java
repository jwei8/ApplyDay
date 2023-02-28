package ca.ubc.cs304.model;

public class JobInfo {
    public String positionName;
    public int companyName;
    public int salary;

    public JobInfo(String positionName, int companyName, int salary) {
        this.positionName = positionName;
        this.companyName = companyName;
        this.salary = salary;
    }

    public String getPositionName() {
        return positionName;
    }

    public int getCompanyName() {
        return companyName;
    }

    public int getSalary() {
        return salary;
    }
}
