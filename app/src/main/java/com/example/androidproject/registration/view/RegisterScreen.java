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


import com.example.androidproject.R;
import com.example.androidproject.registration.presenter.RegisterPresenter;
import com.example.androidproject.registration.presenter.RegisterPresenterInterface;

public class RegisterScreen extends AppCompatActivity implements RegisterViewInterface{
    TextView registerFullName, registerEmail, registerPassword, registerConfirmPassword;
    Button registerUserButton, goToLoginButton;

    RegisterPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmailAddress);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.confirmRegisterPassword);
        registerUserButton = findViewById(R.id.registerbtn);
        goToLoginButton = findViewById(R.id.goToLoginButton);

        presenterInterface = new RegisterPresenter(this);

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

                //data is validated
                Toast.makeText(getApplicationContext(), "Data is Validated", Toast.LENGTH_SHORT).show();
              presenterInterface.register(emailAddress,password);


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
    public void registered() {
        Toast.makeText(this, "Registered ,, Check your Mail for Verification", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}