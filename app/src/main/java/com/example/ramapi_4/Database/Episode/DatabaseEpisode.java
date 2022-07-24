package com.example.ramapi_4.Database.Episode;

import androidx.room.Room;

import com.example.ramapi_4.AppCon;

public class DatabaseEpisode {
    public static final DatabaseEpisode INSTANCE = new DatabaseEpisode();
    EpisodeDB db = Room.databaseBuilder(AppCon.getContext(),
            EpisodeDB.class, "Episode").build();
    EpisodeDao episodeDao = db.episodeDao();

    private DatabaseEpisode(){

    }

    public EpisodeDao getEpisodeDao() {
        return episodeDao;
    }
}