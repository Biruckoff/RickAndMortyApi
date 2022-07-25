package com.example.ramapi_4.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;


import com.example.ramapi_4.AppCon;
import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AppObject.DoubleKey;
import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.AppObject.Parser;
import com.example.ramapi_4.Database.Character.DatabaseCharacter;
import com.example.ramapi_4.Database.DoubleKey.DatabaseDoubleKey;
import com.example.ramapi_4.Interfaces.Callback1;


import org.json.JSONException;
import org.json.JSONObject;

//This class takes data from Server/Database for Activity.AboutCharacter
public class GetInfoAC extends AsyncTask<CartoonPers, Void, CartoonPers> {
   private static final String TAG = "GetInfoAC";
   private final Callback1 callback;
   public static final String BASEURL = "https://rickandmortyapi.com/api/character/";


   public GetInfoAC(Callback1 callback) {
      this.callback = callback;
   }
   @Override
   protected CartoonPers doInBackground(CartoonPers... cartoonPers) {
      if(isPersNull(cartoonPers[0])){
         return getDetailPerson(cartoonPers[0]);
      }else{
         return cartoonPers[0];
      }

   }
   @Override
   protected void onPostExecute(CartoonPers s) {
      if (callback != null) callback.onComplete(s);
   }


   private CartoonPers getDetailPerson(CartoonPers cartoonPers) {
      if(isLineInDatabaseNullFor(cartoonPers)) {
         if(AppCon.isOnline()) {
            DatabaseCharacter.INSTANCE.getCharacterDao().delete(cartoonPers);

            cartoonPers = getFromServer(cartoonPers);

            DatabaseCharacter.INSTANCE.getCharacterDao().insertAll(cartoonPers);
         }
      }else{
         cartoonPers = DatabaseCharacter.INSTANCE.getCharacterDao().findById(cartoonPers.getId());
      }

      return cartoonPers;
   }
   private CartoonPers getFromServer(CartoonPers pers){
      try {
         JSONObject detail = new JSONObject(Downloader.INSTANCE.getStringAbout(pers.getId(), BASEURL));
         pers.setStatus(Parser.INSTANCE.parseStatus(detail));
         pers.setLocation(Parser.INSTANCE.parseLocation(detail));
         pers.setSpecies(Parser.INSTANCE.parseSpecies(detail));
         if (DatabaseDoubleKey.INSTANCE.getDoublekeyDao().getCountOfRows(pers.getId()) == 0) {
            pers = setBound(pers, Parser.INSTANCE.parseEpisodesIds(detail));
         }

      } catch (JSONException e) {
         Log.e(TAG,"getFromServer", e);
      }
      return  pers;
   }
   private CartoonPers setBound(CartoonPers cartoonPers, int[] ids){
      for(int i=0;i<ids.length;i++){

         DatabaseDoubleKey.INSTANCE.getDoublekeyDao().delete(new DoubleKey(cartoonPers.getId(),ids[i]));
         DatabaseDoubleKey.INSTANCE.getDoublekeyDao().insertAll(new DoubleKey(cartoonPers.getId(),ids[i]));

      }
      return cartoonPers;

   }
   private boolean isLineInDatabaseNullFor(CartoonPers cartoonPers){
      CartoonPers pers = DatabaseCharacter.INSTANCE.getCharacterDao().findById(cartoonPers.getId());
      if(pers.getStatus() == null || pers.getLocation() == null || pers.getSpecies()== null||
              DatabaseDoubleKey.INSTANCE.getDoublekeyDao().getCountOfRows(cartoonPers.getId())==0) return true;
      return false;

   }
   private boolean isPersNull(CartoonPers cartoonPers){
      if(cartoonPers.getStatus() == null || cartoonPers.getLocation() == null || cartoonPers.getSpecies()== null ||
      DatabaseDoubleKey.INSTANCE.getDoublekeyDao().getCountOfRows(cartoonPers.getId())==0) return true;
      return false;
   }

}
