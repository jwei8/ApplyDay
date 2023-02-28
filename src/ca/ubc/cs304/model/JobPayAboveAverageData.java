package ca.ubc.cs304.model;

public class JobPayAboveAverageData {
    public String companyName;
    public int count;

    public JobPayAboveAverageData(String companyName, int count) {
        this.companyName = companyName;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Company Name: " + this.companyName + " and Count: " + this.count + "\n";
    }
}
