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
    private String medicineStrengthUnit;
    private int TreatmentDuration;
    private int recurrence;
    private int RefillReminder;

    private String startDate;
    private String endDate;

    public Medicine(String name, String medicineForm, String reasonOfTakingDrug, String recurrenceOfTakingDrug, int dosagesPerTime, int medicineStrength, String medicineStrengthUnit, int treatmentDuration, int recurrence, int refillReminder) {
        this.name = name;
        this.medicineForm = medicineForm;
        this.reasonOfTakingDrug = reasonOfTakingDrug;
        this.recurrenceOfTakingDrug = recurrenceOfTakingDrug;
        this.dosagesPerTime = dosagesPerTime;
        this.medicineStrength = medicineStrength;
        this.medicineStrengthUnit = medicineStrengthUnit;
        TreatmentDuration = treatmentDuration;
        this.recurrence = recurrence;
        RefillReminder = refillReminder;
    }

    private ArrayList<int[]> doseTime;

    public Medicine() {
    }



    public String getMedicineStrengthUnit() {
        return medicineStrengthUnit;
    }

    public void setMedicineStrengthUnit(String medicineStrengthUnit) {
        this.medicineStrengthUnit = medicineStrengthUnit;
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
