package com.example.androidproject.login.loginPresenter;

public interface LoginPresenterInterface {
    public void login(String email,String password);
    public void sendError(String error);
    public void loggedIn();

}
