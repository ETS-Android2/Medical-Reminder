package com.example.androidproject.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.registration.EmailVerificatinScreen;
import com.example.androidproject.R;
import com.example.androidproject.home.view.Home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {
    TextView registerFullName, registerEmail, registerPassword, registerConfirmPassword;
    Button registerUserButton, goToLoginButton;
    FirebaseAuth fAuth;


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
        fAuth = FirebaseAuth.getInstance();

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
                //register the user using firebase
                fAuth.createUserWithEmailAndPassword(emailAddress, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //send user to Next Page
                        Log.i("TAG", "onSuccess:register done ");
                       // startActivity(new Intent(getApplicationContext(), Home.class));
                        Intent outIntent=new Intent(getApplicationContext(), EmailVerificatinScreen.class);
                        startActivity(outIntent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}