package com.example.androidproject.model;

public class UserPojo {
    String UserEmail;
    String UserPassword;
    String UserConfirmPassword;
    String UserName;

    public UserPojo(String userEmail, String userPassword, String userConfirmPassword, String userName) {
        UserEmail = userEmail;
        UserPassword = userPassword;
        UserConfirmPassword = userConfirmPassword;
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return UserConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        UserConfirmPassword = userConfirmPassword;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


}
