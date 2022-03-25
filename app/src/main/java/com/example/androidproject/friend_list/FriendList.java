package com.example.androidproject.friend_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;
import com.example.androidproject.model.RequestModel;
import com.example.androidproject.remote_data.AddTracker;
import com.example.androidproject.remote_data.AddTrackerInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
public class FriendList extends AppCompatActivity {
    // ArrayList<RequestModel> persons  ;
    AddTrackerInterface addTracker = new AddTracker();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ArrayAdapter<String> adapter;
    ListView lstPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        Intent intent = getIntent();
        addTracker.friendList(user.getUid());
        ArrayList<RequestModel> persons = (ArrayList<RequestModel>)getIntent().getSerializableExtra("ARRAYLISTFRIENDS");
        lstPersons = findViewById(R.id.listFriendView);
        for(int i=0;i<persons.size();i++) {
            FriendListAdapter adapter = new FriendListAdapter(this, R.layout.friend_row, R.id.senderEmail, persons);
            lstPersons.setAdapter(adapter);
            lstPersons.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i).toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }
}