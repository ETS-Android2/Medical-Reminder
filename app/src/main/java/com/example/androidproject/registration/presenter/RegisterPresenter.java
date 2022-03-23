package com.example.androidproject.registration.presenter;

import android.util.Log;

import com.example.androidproject.login.loginPresenter.LoginPresenter;
import com.example.androidproject.login.loginView.LoginViewInterface;
import com.example.androidproject.login.login_repository.LoginRepository;
import com.example.androidproject.login.login_repository.LoginRepositoryInterface;
import com.example.androidproject.registration.RegisterRepository.RegisterRepository;
import com.example.androidproject.registration.RegisterRepository.RegisterRepositoryInterface;
import com.example.androidproject.registration.view.RegisterViewInterface;

public class RegisterPresenter implements RegisterPresenterInterface{

    public static RegisterRepositoryInterface repositoryInterface;
    public static RegisterViewInterface viewInterface ;

    private static RegisterPresenter presenter = null;

    private RegisterPresenter(){

    }

    public static RegisterPresenter getPresenter(RegisterRepositoryInterface registerRepositoryInterface){
        repositoryInterface=registerRepositoryInterface;
        if(presenter == null)
            presenter = new RegisterPresenter();

        return presenter;
    }
    public static RegisterPresenter getPresenter( RegisterViewInterface viewInterface1){
        viewInterface = viewInterface1;
        if(presenter == null)
            presenter = new RegisterPresenter();

        return presenter;
    }

    @Override
    public void register(String emailAddress, String password) {

        Log.i("TAG","register presenter");

        repositoryInterface.register(emailAddress,password);
    }

    @Override
    public void sendError(String error) {
        viewInterface.sendError(error);
    }

    @Override
    public void registeredSuccessfully(){
        viewInterface.registeredSuccessfully();
        Log.i("TAG","view registered successfully");
    }
}
