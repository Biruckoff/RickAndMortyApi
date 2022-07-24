package com.example.ramapi_4.Database.DoubleKey;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ramapi_4.AppObject.DoubleKey;

import java.util.List;

@Dao
public interface DoubleKeyDao {
    @Query("SELECT * FROM DoubleKey")
    List<DoubleKey> getAll();

    @Query("SELECT * FROM DoubleKey WHERE id LIKE :id")
    DoubleKey findById(int id);

    @Query("SELECT * FROM DoubleKey WHERE characterKey LIKE :id")
    List<DoubleKey> findByCharacterKey(int id);

    @Query("DELETE FROM DoubleKey ")
    void deleteAll();

    @Query("SELECT COUNT(characterKey) FROM DoubleKey WHERE characterKey LIKE :persId")
    int getCountOfRows(int persId);
    @Insert
    void insertAll(DoubleKey... doubleKey);

    @Delete
    void delete(DoubleKey doubleKey);
}
