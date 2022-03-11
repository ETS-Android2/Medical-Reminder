package com.example.androidproject.add_medicine.add_medicine_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.R;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.AddMedicineNameFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.DosageFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.DurationFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.MedicineFormFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.MedicineReasonRecurrencyFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.MedicineStrengthFragment;
import com.example.androidproject.add_medicine.add_medicine_view.fragments.RefillFragment;

public class AddMedicine extends AppCompatActivity implements AddMedicineFragmentsCommunicator {

    Medicine medicine = new Medicine();
    Fragment[] medicineFragment = new Fragment[7];
    FragmentManager fragmentManager ;
    int currentFragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);

        initFragments();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.FragmentContainerView, medicineFragment[currentFragment]).commit();

    }

    void initFragments(){
        medicineFragment[0] = new AddMedicineNameFragment(this);
        medicineFragment[1] = new MedicineFormFragment(this);
        medicineFragment[2] = new MedicineStrengthFragment(this);
        medicineFragment[4] = new DosageFragment(this);
        medicineFragment[3] = new DurationFragment(this);
        medicineFragment[6] = new RefillFragment(this);
        medicineFragment[5] = new MedicineReasonRecurrencyFragment(this);


    }



    @Override
    public void onBackPressed() {
        if (currentFragment>0) {
            currentFragment--;
            fragmentManager.beginTransaction().replace(R.id.FragmentContainerView, medicineFragment[currentFragment]).commit();
        }else {
            finish();
        }
    }

    @Override
    public void nextFragment() {
        if (currentFragment<6) {
            currentFragment++;
            fragmentManager.beginTransaction().replace(R.id.FragmentContainerView, medicineFragment[currentFragment]).commit();
        }

    }

    @Override
    public void setName(String name) {
        medicine.setName(name);
    }

    @Override
    public void setMedicineForm(String medicineForm) {
        medicine.setMedicineForm(medicineForm);
    }

    @Override
    public void setReasonOfTakingDrug(String reasonOfTakingDrug) {
        medicine.setReasonOfTakingDrug(reasonOfTakingDrug);
    }

    @Override
    public void setRecurrenceOfTakingDrug(String recurrenceOfTakingDrug) {
        medicine.setRecurrenceOfTakingDrug(recurrenceOfTakingDrug);
    }

    @Override
    public void setDosagesPerTime(int dosagesPerTime) {
        medicine.setDosagesPerTime(dosagesPerTime);
    }

    @Override
    public void setMedicineStrength(int medicineStrength) {
        medicine.setMedicineStrength(medicineStrength);
    }

    @Override
    public void setTreatmentDuration(int treatmentDuration) {
        medicine.setTreatmentDuration(treatmentDuration);
    }

    @Override
    public void setRecurrence(int recurrence) {
        medicine.setRecurrence(recurrence);
    }

    @Override
    public void setRefillReminder(int refillReminder) {
        medicine.setRefillReminder(refillReminder);
    }
}