package com.example.androidproject.local_data;

import androidx.room.TypeConverter;

import com.example.androidproject.model.MedicineDose;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<MedicineDose> fromString(String value) {
        Type listType = new TypeToken<ArrayList<MedicineDose>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<MedicineDose> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}

class Converter2{

    @TypeConverter
    public static ArrayList<int[]> fromString(String value) {
        Type listType = new TypeToken<ArrayList<int[]>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<int[]> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
