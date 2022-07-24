package com.example.ramapi_4.Database.Episode;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ramapi_4.AppObject.Episode;


   @Database(entities = {Episode.class}, version = 1)
   public abstract class EpisodeDB extends RoomDatabase {
      public abstract EpisodeDao episodeDao();
   }


