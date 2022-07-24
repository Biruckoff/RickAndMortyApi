package com.example.ramapi_4.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ramapi_4.AppCon;
import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.AppObject.Parser;
import com.example.ramapi_4.Database.Character.DatabaseCharacter;
import com.example.ramapi_4.Database.Episode.DatabaseEpisode;
import com.example.ramapi_4.Interfaces.Callback2;
import com.example.ramapi_4.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
// This class takes data from Server/Database for Activity.ListOfCharacter
public class GetInfoLOC extends AsyncTask<Integer, Void, ArrayList<CartoonPers>> {

   private static final String BASEURL = "https://rickandmortyapi.com/api/character?page=";
   private static final String TAG = "GetInfoLOC";
   private final int COUNTINPAGE = 20;
   public Callback2 callback;
   public GetInfoLOC(Callback2 callback) {
      this.callback = callback;

   }


   @Override
   protected ArrayList<CartoonPers> doInBackground(Integer... ints) {
      int limit = getLimit(); //get limit of pages
      ArrayList<CartoonPers> cartoonPers = new ArrayList<>();
      if(ints[0]<=limit){
      if(isDateBaseIsEpmtyForPage(ints[0]) ){
         if(AppCon.isOnline()){
         cartoonPers = getDetailFromServer(ints[0]);
         addArrayListInDB(cartoonPers);
         }
      }else{
         int from = (ints[0]-1)*COUNTINPAGE;
         int to = ints[0]*COUNTINPAGE;
         cartoonPers = (ArrayList<CartoonPers>) DatabaseCharacter.INSTANCE.getCharacterDao().findFromToById(from,to);

      }}
      return cartoonPers;
   }

   private boolean isDateBaseIsEpmtyForPage(int page){
      int from = (page-1)*COUNTINPAGE;
      int to = page*COUNTINPAGE;
      return DatabaseCharacter.INSTANCE.getCharacterDao().getCountOfRowsFromTo(from,to)==0;
   }

   private ArrayList<CartoonPers> getDetailFromServer(int page){
      JSONObject jsonObject1 = null;
      try{
      jsonObject1 = new JSONObject(Downloader.INSTANCE.getStringAbout(BASEURL+page));}
      catch (JSONException e){
         Log.e(TAG,"getDetailFromServer", e);
      }
      return Parser.INSTANCE.parseLOC(jsonObject1);
   }
   protected void onPostExecute(ArrayList<CartoonPers> s) {
      if (callback != null) callback.onComplete(s);
   }
   private void addArrayListInDB(ArrayList<CartoonPers> cartoonPers){
      for(int i=0;i<cartoonPers.size();i++) DatabaseCharacter.INSTANCE.getCharacterDao().insertAll(cartoonPers.get(i));
   }
   private int getLimit(){
      int limit = DatabaseCharacter.INSTANCE.getCharacterDao().getAll().size()/COUNTINPAGE;
      try{
      limit = Parser.INSTANCE.getCountOfPages( new JSONObject(Downloader.INSTANCE.getStringAbout("https://rickandmortyapi.com/api/character")));

      }catch (JSONException e){
         Log.e(TAG,"addArrayListInDB", e);
      }catch (NullPointerException e){
         Log.e(TAG,"addArrayListInDB", e);
      }
   return limit;}
}

