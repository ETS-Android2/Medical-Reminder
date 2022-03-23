package com.example.androidproject.login.loginPresenter;

import androidx.annotation.NonNull;

import com.example.androidproject.login.loginView.LoginViewInterface;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginPresenterInterface{

    public static LoginViewInterface viewInterface ;

    public LoginPresenter( LoginViewInterface viewInterface1){
        viewInterface = viewInterface1;
    }

    @Override
    public void login(String email, String password) {
        FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                    viewInterface.loggedIn();
                } else {
                    viewInterface.sendError("please Verify your email ");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                viewInterface.sendError(e.getMessage());
            }
        });

    }

}
