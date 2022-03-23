package com.example.androidproject.registration.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.registration.view.RegisterViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenter implements RegisterPresenterInterface{

    RegisterViewInterface viewInterface;
    FirebaseAuth fAuth;

    public RegisterPresenter(RegisterViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }

    @Override
    public void register(String emailAddress, String password) {


        fAuth = FirebaseAuth.getInstance();

        fAuth.createUserWithEmailAndPassword(emailAddress, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //send user to Next Page
                Log.i("TAG", "onSuccess:register done ");
                fAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        viewInterface.registered();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        viewInterface.error(e.getMessage());
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                viewInterface.error(e.getMessage());
            }
        });


    }
}
