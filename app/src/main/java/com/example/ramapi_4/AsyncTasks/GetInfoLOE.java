package com.example.ramapi_4.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ramapi_4.AppCon;
import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.AppObject.Episode;
import com.example.ramapi_4.AppObject.Parser;
import com.example.ramapi_4.Database.DoubleKey.DatabaseDoubleKey;
import com.example.ramapi_4.Database.Episode.DatabaseEpisode;
import com.example.ramapi_4.Interfaces.Callback1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//This class takes data from Server/Database for Activity.ListOfEpisodes
public class GetInfoLOE extends AsyncTask<CartoonPers, Void, CartoonPers> {
    private static final String TAG = "GetInfoLOE";
    private Callback1 callback;
    public GetInfoLOE(Callback1 callback) {
        this.callback = callback;

    }
    public void setDetailEpisodes(){
        int page =1;
        ArrayList<Episode> arrayList=new ArrayList<>();
        int allPages = howManyPageThere();

        while(page<=allPages){
            arrayList.addAll(getEpisodesFromPage(page));
            page++;

        }
    fromArrayListToDatabase(arrayList);

    }

    private void fromArrayListToDatabase(ArrayList<Episode> arrayList){
        for(int i=0;i<arrayList.size();i++){
            DatabaseEpisode.INSTANCE.getEpisodeDao().insertAll(arrayList.get(i));
        }
    }
private int howManyPageThere(){
    String request = Downloader.INSTANCE.getStringAbout("https://rickandmortyapi.com/api/episode");
    JSONObject jsonObject = null;
    try{
    jsonObject = new JSONObject(request);}catch (JSONException e){e.printStackTrace();}
    return Parser.INSTANCE.getCountOfPages(jsonObject);
}

private ArrayList<Episode> getEpisodesFromPage(int page){
        ArrayList<Episode> arrayList = new ArrayList<>();
        String request = Downloader.INSTANCE.getStringAbout(page,"https://rickandmortyapi.com/api/episode?page=");
        try{
        JSONObject jsonObject = new JSONObject(request);
        arrayList = Parser.INSTANCE.parseEpisodes(jsonObject);

        }catch (JSONException e){
            Log.e(TAG,"getEpisodesFromPage", e);
        }
        return arrayList;
}

    protected CartoonPers doInBackground(CartoonPers... cartoonPers) {

        if(DatabaseOfEpisodesIsNull()&& AppCon.isOnline()){
            setDetailEpisodes();
        }
        if(cartoonPers[0].getEpisodes()==null){
            Episode[] episodes = new Episode[DatabaseDoubleKey.INSTANCE.getDoublekeyDao().findByCharacterKey(cartoonPers[0].getId()).size()];
            for(int i=0;i<episodes.length;i++){
                episodes[i] = DatabaseEpisode.INSTANCE.getEpisodeDao().findById((int)DatabaseDoubleKey.INSTANCE.getDoublekeyDao().findByCharacterKey(cartoonPers[0].getId()).get(i).getEpisodeKey());
            }
            cartoonPers[0].setEpisodes(episodes);
        }
        return cartoonPers[0];
    }

    protected void onPostExecute(CartoonPers s) {
        if (callback != null) callback.onComplete(s);
    }

    private boolean DatabaseOfEpisodesIsNull(){
        return DatabaseEpisode.INSTANCE.getEpisodeDao().getCountOfRows()==0;
    }


}
