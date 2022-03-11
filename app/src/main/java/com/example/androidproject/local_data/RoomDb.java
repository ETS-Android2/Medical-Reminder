package com.example.androidproject.local_data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.androidproject.model.MedicineList;

@Database(entities = {MedicineList.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RoomDb extends RoomDatabase {
    private static RoomDb roomDb = null;

    public abstract ListDao listDao();

    public static synchronized RoomDb getInstance(Context context) {
        if (roomDb == null) {
            roomDb = Room.databaseBuilder(context, RoomDb.class, "medicine_list").build();
        }
        return roomDb;
    }
}
