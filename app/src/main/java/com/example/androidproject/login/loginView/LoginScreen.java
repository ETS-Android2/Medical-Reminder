package com.example.androidproject.login.loginView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.home.view.Home;
import com.example.androidproject.login.loginPresenter.LoginPresenter;
import com.example.androidproject.login.loginPresenter.LoginPresenterInterface;
import com.example.androidproject.registration.view.RegisterScreen;

public class LoginScreen extends AppCompatActivity implements LoginViewInterface {

    TextView loginEmail;
    TextView loginPassword;
    Button loginButton;
    Button createAccountButton;
    LoginPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccountButton = findViewById(R.id.SignUpButton);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginbtn);
        presenterInterface = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailLogin = loginEmail.getText().toString();
                String passwordLogin = loginPassword.getText().toString();

                if (emailLogin.isEmpty()) {
                    loginEmail.setError("Email Address is required");
                    return;
                }
                if (passwordLogin.isEmpty()) {
                    loginPassword.setError("Password is required");
                    return;
                }

                presenterInterface.login(emailLogin, passwordLogin);


            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), RegisterScreen.class);
                startActivity(outIntent);
            }
        });
    }

    @Override
    public void loggedIn() {
        Toast.makeText(this, "Welcome :)", Toast.LENGTH_SHORT).show();
        SharedPreferences data = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        data.edit().putBoolean("LoggedIn", true).commit();
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    @Override
    public void sendError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}