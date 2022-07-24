package com.example.ramapi_4.Database.Character;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ramapi_4.AppObject.CartoonPers;

import java.util.List;

@Dao
public interface CharacterDao {
    @Query("SELECT * FROM CartoonPers")
    List<CartoonPers> getAll();

    @Query("SELECT * FROM CartoonPers WHERE id LIKE :id")
    CartoonPers findById(int id);

    @Query("SELECT * FROM CartoonPers WHERE id > :from AND id<:to ")
    List<CartoonPers> findFromToById(int from,int to);

    @Query("DELETE FROM CartoonPers")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM CartoonPers WHERE id > :from AND id<:to ")
    int getCountOfRowsFromTo(int from,int to);
    @Insert
    void insertAll(CartoonPers... cartoonPers);

    @Delete
    void delete(CartoonPers cartoonPers);
}
