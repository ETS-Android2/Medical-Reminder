package com.example.androidproject;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.model.Medicine;

import java.util.ArrayList;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {

    private ArrayList<Medicine> medicineList;


    public MedicineListAdapter(ArrayList<Medicine> medicineList) {

        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.medicine_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);

        holder.medicineNameTextView.setText(medicine.getName());
        holder.medicineStrengthText.setText(medicine.getMedicineStrength()+"");
        holder.medicineDosageTextView.setText(medicine.getDosagesPerTime()+"");
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicineNameTextView;
        public TextView medicineStrengthText;
        public TextView medicineDosageTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineNameTextView = itemView.findViewById(R.id.MedicineNameTextViewRow);
            medicineStrengthText = itemView.findViewById(R.id.MedicineStrengthTextViewRow);
            medicineDosageTextView = itemView.findViewById(R.id.MedicineDosageTextViewRow);

        }
    }
}
