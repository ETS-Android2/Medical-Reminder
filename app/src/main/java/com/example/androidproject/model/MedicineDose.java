package com.example.androidproject.model;

public class MedicineDose {
    private String name;
    private int dose;
    private int hour;
    private int minute;

    public MedicineDose(){}

    public MedicineDose(String name, int dose, int hour, int minute) {
        this.name = name;
        this.dose = dose;
        this.hour = hour;
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
