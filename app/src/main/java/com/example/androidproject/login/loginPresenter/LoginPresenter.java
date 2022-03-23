package com.example.androidproject.login.loginPresenter;

import com.example.androidproject.login.loginView.LoginViewInterface;
import com.example.androidproject.login.login_repository.LoginRepository;
import com.example.androidproject.login.login_repository.LoginRepositoryInterface;

public class LoginPresenter implements LoginPresenterInterface{

   //LoginRepositoryInterface repositoryInterface= new LoginRepository();
   public static LoginRepositoryInterface repositoryInterface;
    public static LoginViewInterface viewInterface ;

    private static LoginPresenter presenter = null;

    private LoginPresenter(){


    }

    public static LoginPresenter getPresenter(LoginRepositoryInterface loginRepositoryInterface){
        repositoryInterface =loginRepositoryInterface;
        if(presenter == null)
            presenter = new LoginPresenter();

        return presenter;
    }
    public static LoginPresenter getPresenter( LoginViewInterface viewInterface1){
        viewInterface = viewInterface1;
        if(presenter == null)
            presenter = new LoginPresenter();

        return presenter;
    }

    @Override
    public void login(String email, String password) {
        repositoryInterface.login(email,password);
    }

    @Override
    public void loggedIn() {
        viewInterface.loggedIn();
    }

    @Override
    public void sendError(String error) {
        viewInterface.sendError(error);
    }
}
