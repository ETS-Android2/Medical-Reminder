package com.example.androidproject.remote_data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidproject.model.MedicineDose;
import com.example.androidproject.model.MedicineList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MedicineDAO implements RemoteSource {
    private DatabaseReference databaseReference;
    String TAG;
    public FirebaseFirestore fireStore;
    MedicineList medicineList ;
    MedicineDose medicineDose =  new MedicineDose();
    String objectAsString;
    ArrayList<MedicineDose> medicineDoseArrayList  = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public MedicineDAO() {
        fireStore = FirebaseFirestore.getInstance();

    }

    public void add(String id, Object doc) {
        Log.i(TAG, "add: tessssst");
        fireStore.collection(user.getUid()).document("Medicines").collection("myMedicinesList").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

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

    @Override
    public void addMedicineDose(String id, Object med) {
        if(user.getUid()!=null) {

            DocumentReference docIdRef = fireStore.collection(user.getUid()).document("MedicineList").collection("DosesPerDay").document(id);
            docIdRef.set(med);
            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            docIdRef.set(med, SetOptions.merge());
                            Log.i(TAG, "onComplete: " + "doneeeee");
                        } else {
                            docIdRef.set(med);
                        }
                    } else {
                        Log.d(TAG, "onComplete: ", task.getException());

                    }
                }
            });
        }
    }

    @Override
    public MedicineList retrieveData(String date) {

        if(user.getUid()!=null) {
            DocumentReference docRef = fireStore.collection(user.getUid()).document("MedicineList").collection("DosesPerDay").document(date);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot document = task.getResult();
                    if (document.getData() != null) {
                        String returnedString = String.valueOf(document.getData().values());
                        convertString(returnedString);
                        medicineList = new MedicineList(date, medicineDoseArrayList);
                        Log.i(TAG, "retrieveData: "+returnedString);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
        }
        return medicineList;
    }

private void convertString(String returnedString){
    String delimiter = "},";
    String delimiter2 = " {";
    String[] s = returnedString.split(Pattern.quote(delimiter+delimiter2));
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    objectAsString = s[0].substring(2)+"}";
    medicineDose = gson.fromJson(objectAsString, MedicineDose.class);
    medicineDoseArrayList.add(medicineDose);

    for(int i=1;i<s.length-1;i++){
        objectAsString = "{"+s[i]+"}";
        medicineDose = gson.fromJson(objectAsString, MedicineDose.class);
        medicineDoseArrayList.add(medicineDose);
    }
    objectAsString = "{"+s[s.length-1].substring(0,s[s.length-1].length()-2);
    medicineDose = gson.fromJson(objectAsString, MedicineDose.class);
    medicineDoseArrayList.add(medicineDose);
}
}