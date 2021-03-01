package com.example.medhat_ahmed;
//Make sure that the class name same as the table name
public class CurrentUser {

    private String UserName;


    public CurrentUser(String userName)
    {
        UserName = userName;
    }


    public CurrentUser()
    {
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


}
