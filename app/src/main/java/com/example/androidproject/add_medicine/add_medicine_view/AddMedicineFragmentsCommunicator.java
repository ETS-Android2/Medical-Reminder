package com.example.androidproject.add_medicine.add_medicine_view;

public interface AddMedicineFragmentsCommunicator {
    public void nextFragment();

    public void setName(String name);

    public void setMedicineForm(String medicineForm) ;

    public void setReasonOfTakingDrug(String reasonOfTakingDrug);

    public void setRecurrenceOfTakingDrug(String recurrenceOfTakingDrug) ;

    public void setDosagesPerTime(int dosagesPerTime) ;

    public void setMedicineStrength(int medicineStrength) ;

    public void setTreatmentDuration(int treatmentDuration);

    public void setRecurrence(int recurrence);

    public void setRefillReminder(int refillReminder);


}
