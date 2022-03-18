package com.example.androidproject.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Medicine {

    public static final String Pill = "Pill";
    public static final String Solution = "Solution";
    public static final String Injection = "Injection";
    public static final String Powder = "Powder";
    public static final String Drops = "Drops";
    public static final String Inhaler = "Inhaler";

    private String name;
    private String medicineForm;
    private String reasonOfTakingDrug;
    private String recurrenceOfTakingDrug;
    private int dosagesPerTime;
    private int medicineStrength;
    private int TreatmentDuration;
    private int recurrence;
    private int RefillReminder;

    private String startDate;
    private String endDate;

    private ArrayList<int[]> doseTime;

    public Medicine() {
    }

    static Medicine getMedicine(String json) {
        Gson gson = new Gson();
        Medicine medicine = gson.fromJson(json, Medicine.class);
        return medicine;
    }

    @Override
    public String toString() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }


    public ArrayList<int[]> getDoseTime() {
        return doseTime;
    }

    public void setDoseTime(ArrayList<int[]> doseTime) {
        this.doseTime = doseTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
    }

    public String getReasonOfTakingDrug() {
        return reasonOfTakingDrug;
    }

    public void setReasonOfTakingDrug(String reasonOfTakingDrug) {
        this.reasonOfTakingDrug = reasonOfTakingDrug;
    }

    public String getRecurrenceOfTakingDrug() {
        return recurrenceOfTakingDrug;
    }

    public void setRecurrenceOfTakingDrug(String recurrenceOfTakingDrug) {
        this.recurrenceOfTakingDrug = recurrenceOfTakingDrug;
    }

    public int getDosagesPerTime() {
        return dosagesPerTime;
    }

    public void setDosagesPerTime(int dosagesPerTime) {
        this.dosagesPerTime = dosagesPerTime;
    }

    public int getMedicineStrength() {
        return medicineStrength;
    }

    public void setMedicineStrength(int medicineStrength) {
        this.medicineStrength = medicineStrength;
    }

    public int getTreatmentDuration() {
        return TreatmentDuration;
    }

    public void setTreatmentDuration(int treatmentDuration) {
        TreatmentDuration = treatmentDuration;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public int getRefillReminder() {
        return RefillReminder;
    }

    public void setRefillReminder(int refillReminder) {
        RefillReminder = refillReminder;
    }


}
