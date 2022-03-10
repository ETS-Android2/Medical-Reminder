package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    TextView loginEmail;
    TextView loginPassword;
    Button loginButton;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccountButton=findViewById(R.id.CreateNewAccountButton);
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton=findViewById(R.id.loginbtn);
        FirebaseAuth firebaseAuth;

        firebaseAuth=FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailLogin=loginEmail.getText().toString();
                String passwordLogin=loginPassword.getText().toString();

                if(emailLogin.isEmpty()){
                    loginEmail.setError("Email Address is required");
                    return;

                }
                if(passwordLogin.isEmpty()){

                    loginPassword.setError("Password is required");
                    return;
                }
                Toast.makeText(getApplicationContext(),"Login is Successful",Toast.LENGTH_SHORT).show();

                firebaseAuth.signInWithEmailAndPassword(emailLogin,passwordLogin).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent=new Intent(getApplicationContext(),RegisterScreen.class);
                startActivity(outIntent);


            }
        });


    }

}