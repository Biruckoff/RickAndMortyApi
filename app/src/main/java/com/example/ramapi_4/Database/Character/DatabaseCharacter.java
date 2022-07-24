package com.example.ramapi_4.Database.Character;


import androidx.room.Database;
import androidx.room.Room;

import com.example.ramapi_4.AppCon;

public class DatabaseCharacter {
    public static final DatabaseCharacter INSTANCE = new DatabaseCharacter();
    CharacterDB db = Room.databaseBuilder(AppCon.getContext(),
            CharacterDB.class, "CartoonPers").build();
    CharacterDao characterDao = db.characterDao();

    private DatabaseCharacter(){}

    public CharacterDao getCharacterDao() {
        return characterDao;
    }
}
