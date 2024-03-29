package com.example.medicly;

public class User {
    //creation of variables relating to medication information, this class uses getters and setters so variables can be safely stored within this class,
    //and can only be retrieved using the relevant getter and setter methods()

    // creation of variables
    private int userID;
    private static String  firstname;
    private String lastname;
    private String username;
    private String userbirthday;
    private String password;

    public User(int userID, String firstname, String lastname, String username, String userbirthday, String password){
    this.userID = userID;
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.userbirthday = userbirthday;
    this.password = password;

    }

    public User() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
