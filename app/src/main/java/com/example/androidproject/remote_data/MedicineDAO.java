package com.example.androidproject.remote_data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.add_medicine.add_medicine_view.AddMedicine;
import com.example.androidproject.model.Medicine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MedicineDAO implements RemoteSource {
    private DatabaseReference databaseReference;
    String TAG;
   public FirebaseFirestore firestore;
    public MedicineDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference= db.getReference(Medicine.class.getSimpleName());
        firestore = FirebaseFirestore.getInstance();

    }
    public void add(String id,Object doc){
        firestore.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new  OnCompleteListener<Void>(){

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i(TAG, "onComplete: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: ");
                    }
                });

    }
}
