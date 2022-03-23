package com.example.androidproject.registration.presenter;

public interface RegisterPresenterInterface {
    public void register(String emailAddress,String Password);//same as firebase
    public void sendError(String error);
    public void registeredSuccessfully();
}
