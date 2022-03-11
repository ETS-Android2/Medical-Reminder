package com.example.androidproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "medicine_list")
public class MedicineList {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "list")
    private ArrayList<MedicineDose> medicineDoseArrayList;

    public MedicineList(@NonNull String date, ArrayList<MedicineDose> medicineDoseArrayList) {
        this.date = date;
        this.medicineDoseArrayList = medicineDoseArrayList;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public ArrayList<MedicineDose> getMedicineDoseArrayList() {
        return medicineDoseArrayList;
    }

    public void setMedicineDoseArrayList(ArrayList<MedicineDose> medicineDoseArrayList) {
        this.medicineDoseArrayList = medicineDoseArrayList;
    }
}
