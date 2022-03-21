package com.example.androidproject.registration.RegisterRepository;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.email_verification.emailVerification_Viewer.EmailVerificatinScreen;
import com.example.androidproject.registration.presenter.RegisterPresenter;
import com.example.androidproject.registration.presenter.RegisterPresenterInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterRepository implements RegisterRepositoryInterface{

    RegisterPresenterInterface presenterInterface= RegisterPresenter.getPresenter(this);


    @Override
    public void register(String emailAddress, String Password) {
        String registeredEmail = emailAddress;
        String registeredPassword= Password;
        FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(registeredEmail, registeredPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                presenterInterface.registeredSuccessfully();
                Log.i("TAG","register presenter");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                presenterInterface.sendError(e.getMessage());
            }
        });

    }
}
