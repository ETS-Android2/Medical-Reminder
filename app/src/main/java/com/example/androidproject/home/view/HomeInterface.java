package com.example.androidproject.home.view;

import com.example.androidproject.model.Medicine;

import java.util.ArrayList;
import java.util.HashMap;

public interface HomeInterface {

    public void updateMedicineList(HashMap<String, ArrayList<Medicine>> map);
}
