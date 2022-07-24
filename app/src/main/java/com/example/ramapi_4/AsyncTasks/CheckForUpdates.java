package com.example.ramapi_4.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.Database.Character.DatabaseCharacter;
import com.example.ramapi_4.Database.DoubleKey.DatabaseDoubleKey;
import com.example.ramapi_4.Database.Episode.DatabaseEpisode;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckForUpdates extends AsyncTask<Void, Void,Void> {

   private static final String TAG = "CheckForUpdates";

   @Override
   protected Void doInBackground(Void... voids) {
      int count = DatabaseEpisode.INSTANCE.getEpisodeDao().getCountOfRows();
      String str = Downloader.INSTANCE.getStringAbout("https://rickandmortyapi.com/api/episode");
      try{
         JSONObject jsonObject = new JSONObject(str);
         count = Integer.parseInt(   ((JSONObject)  jsonObject.getJSONObject("info")).get("count") .toString());

      }catch (JSONException e){
         Log.e(TAG,"doInBackground", e);
      }

      if(DatabaseEpisode.INSTANCE.getEpisodeDao().getCountOfRows() != count){
         DatabaseEpisode.INSTANCE.getEpisodeDao().deleteAll();
         DatabaseDoubleKey.INSTANCE.getDoublekeyDao().deleteAll();
      }
      return null;
   }

}
