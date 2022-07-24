package com.example.ramapi_4.Database.DoubleKey;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ramapi_4.AppObject.DoubleKey;

@Database(entities = {DoubleKey.class}, version = 1)
public abstract class DoubleKeyDB extends RoomDatabase {
   public abstract DoubleKeyDao doubleKeyDao();
}
