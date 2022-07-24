package com.example.ramapi_4.AppObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Episode implements Serializable {
   @PrimaryKey
   private int id;

   @ColumnInfo(name = "name")
   private String name;
   @ColumnInfo(name = "date")
   private String date;
   public Episode(String name, String date){
      this.name = name;
      this.date = date;
   }

   public String getDate() {
      return date;
   }

   public String getName() {
      return name;
   }

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id= id;
   }
}