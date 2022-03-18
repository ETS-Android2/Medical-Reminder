package com.example.androidproject.remote_data;

import com.example.androidproject.model.Medicine;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineDAO implements RemoteSource {
    private DatabaseReference databaseReference;
    public MedicineDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference= db.getReference(Medicine.class.getSimpleName());
    }
    public Task<Void> add(Medicine med){
            return databaseReference.push().setValue(med);
    }
}
