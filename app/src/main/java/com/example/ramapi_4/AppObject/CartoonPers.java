package com.example.ramapi_4.AppObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CartoonPers implements Serializable {
   @PrimaryKey
   int id;
   @ColumnInfo(name = "pic")
   String pic;
   @ColumnInfo(name = "name")
   String name;
   @ColumnInfo(name = "status")
   String status;
   @ColumnInfo(name = "location")
   String location;
   @ColumnInfo(name = "species")
   String species;

   @Ignore
   Episode[] episodes;

   public CartoonPers(String pic, String name, int id){
      this.name = name;
      this.pic = pic;
      this.id = id;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public void setSpecies(String species) {
      this.species = species;
   }

   public void setStatus(String status) {
      this.status = status;
   }


   public void setEpisodes(Episode[] episodes) {
      this.episodes = episodes;
   }

   public String getLocation() {
      return location;
   }

   public String getSpecies() {
      return species;
   }

   public String getStatus() {
     return status;
   }


   public Episode[] getEpisodes() {
      return episodes;
   }
   public int getId(){
      return id;
   }
   public String getName(){
      return name;
   }
   public String getPic(){
      return pic;
   }
}
