package com.example.androidproject;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {
    private Handler handler;
    private final Context context;
    private ArrayList<Medicine> medicineList;


    public MedicineListAdapter(Context context, ArrayList<Medicine> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTx.setText(medicineList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTx;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTx = itemView.findViewById(R.id.MedicineName);

        }
    }
}