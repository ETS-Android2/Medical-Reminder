package com.example.androidproject.remote_data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.RequestModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class AddTracker implements AddTrackerInterface {
    public FirebaseFirestore fireStore;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String TAG = "";
    ArrayList<RequestModel> trackers = new ArrayList<>();
    ArrayList<RequestModel> friends = new ArrayList<>();
    ArrayList<Medicine> friendMedicines = new ArrayList<>();


    public AddTracker() {
        fireStore = FirebaseFirestore.getInstance();

    }

    @Override
    public void sendRequest(String id, RequestModel req) {
        Log.i("TAG", "sendRequest: ");
        fireStore.collection("Requests").document("FriendRequests").collection(req.getReceiverEmail()).document().set(req)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("TAG", "sendRequest: doneeee ");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", "onFailure: ");
                    }
                });
    }

    @Override
    public void reqList(String id) {
if(user!=null) {
    fireStore.collection("Requests").document("FriendRequests").collection(id).whereEqualTo("states", "Pending")
            .

                    addSnapshotListener(new EventListener<QuerySnapshot>() {

                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.d(TAG, "Error: " + error.getMessage());
                            }
                            for (DocumentChange doc : value.getDocumentChanges()) {
                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    Log.i(TAG, "onEvent: " + doc.getDocument().getId());
                                    RequestModel req = doc.getDocument().toObject(RequestModel.class);
                                    req.setDocId(doc.getDocument().getId());
                                    trackers.add(req);
                                }
                            }
                        }
                    });
}
    }

    @Override
    public ArrayList<RequestModel> returnRequestArr() {
        return trackers;
    }

    @Override
    public void updateStatus(String id) {
        DocumentReference washingtonRef =
                fireStore.collection("Requests").document("FriendRequests").collection(user.getEmail()).document(id);
        washingtonRef.
                update("states", "accepted")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    @Override
    public ArrayList<Medicine> friendList(String id) {
        if(user!=null) {
            fireStore.collection("Requests").document("FriendRequests").collection(user.getEmail())
                    .whereEqualTo("states", "accepted")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (document.getData() != null) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        RequestModel friend = document.toObject(RequestModel.class);
                                        friends.add(friend);

                                    }
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
        return friendMedicines;
    }
    @Override
    public ArrayList<RequestModel> returnFriendsArr() {
        return friends;
    }

    @Override
    public void getMyFriendMedicines(String id) {
        ArrayList<Medicine> list = new ArrayList<>();
        fireStore.collection(id).document("Medicines").collection("myMedicinesList").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                               getAllMedicinesName(id,document.getId());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private   void getAllMedicinesName(String friendID,String id) {
        fireStore.collection(friendID).document("Medicines").collection("myMedicinesList").document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();
                                String returned = String.valueOf(document.getData());
                                Log.i(TAG, "My Friend Medications"+returned);
                                Medicine med = gson.fromJson(returned, Medicine.class);
                                friendMedicines.add(med);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }
}