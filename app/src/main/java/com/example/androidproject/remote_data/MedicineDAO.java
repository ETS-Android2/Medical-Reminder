package com.example.androidproject.remote_data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidproject.model.MedicineDose;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MedicineDAO implements RemoteSource {
    private DatabaseReference databaseReference;
    String TAG;
    public FirebaseFirestore fireStore;



    public MedicineDAO() {
        fireStore = FirebaseFirestore.getInstance();

    }

    public void add(String id, Object doc) {

        fireStore.collection("Medicines").document(id).set(doc)
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

        DocumentReference docIdRef = fireStore.collection("MedicineList").document(id);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        docIdRef.set(med,SetOptions.merge());
                        Log.i(TAG, "onComplete: "+"doneeeee");
                    } else {
                        docIdRef.set(med);
                    }
                } else {
                    Log.d(TAG, "onComplete: ", task.getException());

                }
            }
        });
    }

    @Override
    public void retrieveData(String date) {
        Log.i(TAG, "retrieveData: ");
        DocumentReference docRef = fireStore.collection("MedicineList").document(date);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Object object =  documentSnapshot.getData();


                        Gson gson = new Gson();
                        String json = gson.toJson(object);
                        try {
                            JSONObject infoObj = new JSONObject(json).getJSONObject("medicinesDose");
                            MedicineDose m = gson.fromJson(json, MedicineDose.class);
                            //     medicineListAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Map<String , Object> object = (Map<String, Object>) documentSnapshot.getData();
//                        MedicineList medicineList1 = new MedicineList();
//                        medicineList1.setDate(date);
//                        medicineList1.setMedicineDoseArrayList((ArrayList<MedicineDose>) object.get("medicineDose"));
//                        Log.i(TAG, "run: "+object.get("medicineDose"));

//                        String json = new Gson().toJson(object);
//                        Log.v("json", json);
//                        try {
//                            JSONObject infoObj = new JSONObject(json).getJSONObject("medicinesDose");
//                            JSONArray jsonarray = new JSONArray(infoObj);
//                            Iterator<String> iterator = infoObj.keys();
//                            while (iterator.hasNext()) {
//                                for (int i = 0; i < jsonarray.length(); i++) {
//                                    Log.i(TAG, "run: "+i);
//                                    String key = iterator.next();
//                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
//                                   // JSONObject objArray = infoObj.getJSONObject(key);
//                                    MedicineDose med = new MedicineDose();
//                                    med.setName(jsonobject.getString("name"));
//                                    med.setHour(jsonobject.getInt("hour"));
//                                    med.setMinute(jsonobject.getInt("minute"));
//                                    medicineList.add(med);
//                                }
//
//                            }
//
//                        } catch (
//                                JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                };
                Thread th = new Thread(r);
                th.start();
            }

        });


    }}