package com.example.androidproject.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.R;
import com.example.androidproject.model.MedicineDose;

import java.util.ArrayList;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {

    private ArrayList<MedicineDose> medicineList;


    public MedicineListAdapter(ArrayList<MedicineDose> medicineList) {

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
        MedicineDose medicine = medicineList.get(position);

        holder.medicineNameTextView.setText(medicine.getName());
        holder.medicineStrengthText.setText(medicine.getHour()+"");
        holder.medicineDosageTextView.setText(medicine.getMinute()+"");
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


    public void setMedicineList(ArrayList<MedicineDose> medicineList) {
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
