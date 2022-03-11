package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EmailVerificatinScreen extends AppCompatActivity {

    TextView verifyMsg;
    Button verifyEmailButton;
    Button logout;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verificatin_screen);
        logout=findViewById(R.id.logoutButton);
        verifyEmailButton=findViewById(R.id.VerifyEmailButton);
        verifyMsg=findViewById(R.id.verifyEmaimMsg);

        firebaseAuth=FirebaseAuth.getInstance();

        if(!firebaseAuth.getCurrentUser().isEmailVerified()){
            //in this case we will display the emailMsg and EmailButton

            verifyEmailButton.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);
        }
///Handle Verify button to send the verification email to the user//
        verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EmailVerificatinScreen.this, "Verification Email is sent", Toast.LENGTH_SHORT).show();
                        //once verification is done we will hide the verifyMsg and Button
                        verifyEmailButton.setVisibility(View.GONE);
                        verifyMsg.setVisibility(View.GONE);
                    }
                });
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),long.class);
                startActivity(intent);
                finish();

            }
        });
    }
}