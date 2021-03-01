package com.example.medhat_ahmed;
//Make sure that the class name same as the table name
public class User {
    private String Firstname;
    private String SecondName;
    private String UserName;
    private String Email;
    private String password;
    private int profileImage;

    public User(String firstname, String secondName, String userName, String email, String password, int profileImage)
    {
        Firstname = firstname;
        SecondName = secondName;
        UserName = userName;
        Email = email;
        this.password = password;
        this.profileImage = profileImage;
    }


    public User()
    {
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
}
