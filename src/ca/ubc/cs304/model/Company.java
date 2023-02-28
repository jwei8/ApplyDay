package ca.ubc.cs304.model;

public class Company {
    private int companyID;
    private String name;
    private String address;

    public Company(int companyID, String name, String address) {
        this.companyID = companyID;
        this.name = name;
        this.address = address;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
