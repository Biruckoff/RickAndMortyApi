package com.example.ramapi_4.Database.Episode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ramapi_4.AppObject.Episode;

import java.util.List;

@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM Episode")
    List<Episode> getAll();

    @Query("SELECT * FROM Episode WHERE id LIKE :id")
    Episode findById(int id);

    @Query("DELETE FROM Episode ")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM Episode")
    int getCountOfRows();

    @Insert
    void insertAll(Episode... episodes);

    @Delete
    void delete(Episode episode);
}
