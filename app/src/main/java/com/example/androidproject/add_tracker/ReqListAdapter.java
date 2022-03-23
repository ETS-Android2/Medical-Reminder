package com.example.androidproject.add_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidproject.R;
import com.example.androidproject.model.RequestModel;
import com.example.androidproject.remote_data.AddTracker;
import com.example.androidproject.remote_data.AddTrackerInterface;

import java.util.ArrayList;

public class ReqListAdapter extends ArrayAdapter<RequestModel> {
    Context context;
    ArrayList<RequestModel> personArr;
    int customRowId;
    Button accpet;
    Button delete;
    AddTrackerInterface addTracker = new AddTracker();


    public ReqListAdapter(@NonNull Context context, int customRowId, int textViewResourceId, @NonNull ArrayList<RequestModel> personData) {
        super(context, customRowId, textViewResourceId, personData);
        this.context = context;
        personArr = personData;
        this.customRowId = customRowId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup listView) {
        View rowView = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.reqlist_item,listView,false);
        ImageView img = rowView.findViewById(R.id.imageView);
        TextView senderName = rowView.findViewById(R.id.senderEmail);
        TextView states = rowView.findViewById(R.id.relationStates);
        accpet = rowView.findViewById(R.id.accept_button2);
        delete = rowView.findViewById(R.id.delete_button);
        img.setImageResource(R.drawable.nurse);
        senderName.setText(personArr.get(position).getSenderEmail());
        states.setText(personArr.get(position).getStates());
        accpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTracker.updateStatus(personArr.get(position).getDocId());
                addTracker.getMyFriendMedicines(personArr.get(position).getSenderID());
            }
        });


        return rowView ;
    }
}