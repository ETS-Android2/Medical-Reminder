package com.example.androidproject.registration.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.email_verification.emailVerification_Viewer.EmailVerificatinScreen;
import com.example.androidproject.R;
import com.example.androidproject.home.view.Home;
import com.example.androidproject.login.loginView.LoginScreen;
import com.example.androidproject.registration.presenter.RegisterPresenter;
import com.example.androidproject.registration.presenter.RegisterPresenterInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity implements RegisterViewInterface {
    TextView registerFullName, registerEmail, registerPassword, registerConfirmPassword;
    Button registerUserButton, goToLoginButton;
    RegisterPresenterInterface registerPresenterInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(savedInstanceState!=null){finish();} //register opened twice

        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmailAddress);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.confirmRegisterPassword);
        registerUserButton = findViewById(R.id.registerbtn);
        goToLoginButton = findViewById(R.id.goToLoginButton);

        registerPresenterInterface= RegisterPresenter.getPresenter(this);
        //fAuth = FirebaseAuth.getInstance();

        registerUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extract the data from the Form
                String fullName = registerFullName.getText().toString();
                String emailAddress = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                //validate the data

                if (fullName.isEmpty()) {
                    registerFullName.setError("Full Name is required");
                    return;
                }
                if (emailAddress.isEmpty()) {
                    registerEmail.setError("Email Address is required");
                    return;

                }
                if (password.isEmpty()) {

                    registerPassword.setError("Password is required");
                    return;
                }
                if (confirmPassword.isEmpty()) {
                    registerConfirmPassword.setError(" Confirm Password is required");
                    return;

                }
                if (!password.equals(confirmPassword)) {
                    registerConfirmPassword.setError(" Password Do not match");
                    return;
                }
                registerPresenterInterface.register(emailAddress,password);

                //data is validated
               // Toast.makeText(getApplicationContext(), "Data is Validated", Toast.LENGTH_SHORT).show();





            }
        });
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    public void registeredSuccessfully(){
        Toast.makeText(getApplicationContext(),"Data is validated Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
    }

    @Override
    public void sendError(String error){
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

}