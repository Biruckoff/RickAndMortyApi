package com.example.ramapi_4.Database.DoubleKey;

import androidx.room.Room;

import com.example.ramapi_4.AppCon;


public class DatabaseDoubleKey {
    public static final DatabaseDoubleKey INSTANCE = new DatabaseDoubleKey();
    DoubleKeyDB db = Room.databaseBuilder(AppCon.getContext(),
            DoubleKeyDB.class, "DoubleKey").build();
    DoubleKeyDao doublekeyDao = db.doubleKeyDao();

    private DatabaseDoubleKey(){

    }

    public DoubleKeyDao getDoublekeyDao() {
        return doublekeyDao;
    }
}
