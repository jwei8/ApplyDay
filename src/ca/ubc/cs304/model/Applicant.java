package ca.ubc.cs304.model;

public class Applicant {
    public int userID;
    public String name;
    public String address;
    public String password;
    public String phoneNumber;
    public String email;
    public int requireVisaSponsor;

    public Applicant(int userID, String name, String address, String password, String phoneNumber, String email, int requireVisaSponsor) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.requireVisaSponsor = requireVisaSponsor;
    }

    public int getUserID() { return userID; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getPassword() { return password; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getEmail() { return email; }

    public int getRequireVisaSponsor() { return requireVisaSponsor; }
}
