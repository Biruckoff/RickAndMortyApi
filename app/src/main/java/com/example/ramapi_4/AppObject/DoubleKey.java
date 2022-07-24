package com.example.ramapi_4.AppObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index(value = {"characterKey", "episodeKey"}, unique = true)
}
)
public class DoubleKey {

    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "characterKey")
    int characterKey;
    @ColumnInfo(name = "episodeKey")
    int episodeKey;
    public DoubleKey(int characterKey, int episodeKey){
        this.characterKey = characterKey;
        this.episodeKey = episodeKey;
    }

    public int getCharacterKey() {
        return characterKey;
    }

    public int getEpisodeKey() {
        return episodeKey;
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
}
