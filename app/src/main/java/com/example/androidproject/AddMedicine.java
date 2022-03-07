package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AddMedicine extends AppCompatActivity {

    MedicineFormActivity addMedicineFragment;
    FragmentManager fragmentManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);

        fragmentManager = getSupportFragmentManager();

            addMedicineFragment = new MedicineFormActivity();
            fragmentManager.beginTransaction().add(R.id.FragmentContainerView,addMedicineFragment).commit();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}