package com.example.androidproject.login.login_repository;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.login.loginPresenter.LoginPresenter;
import com.example.androidproject.login.loginPresenter.LoginPresenterInterface;
import com.example.androidproject.login.loginView.LoginScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepository implements LoginRepositoryInterface {

    LoginPresenterInterface presenterInterface = LoginPresenter.getPresenter(this);

    @Override
    public void login(String email,String password){

        String emailLogin = email;
        String passwordLogin= password;
        FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                //    sendError("Login is Successful");
                    loggedIn();

                } else {
                    sendError("please Verify your email ");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                sendError(e.getMessage());
            }
        });

    }

    void sendError(String error){
        presenterInterface.sendError(error);
    }
    void loggedIn(){
        presenterInterface.loggedIn();
    }


}
