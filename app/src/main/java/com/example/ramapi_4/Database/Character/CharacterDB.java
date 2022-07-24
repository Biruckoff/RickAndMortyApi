package com.example.ramapi_4.Database.Character;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ramapi_4.AppObject.CartoonPers;

@Database(entities = {CartoonPers.class}, version = 1)
public abstract class CharacterDB extends RoomDatabase {
   public abstract CharacterDao characterDao();
}
