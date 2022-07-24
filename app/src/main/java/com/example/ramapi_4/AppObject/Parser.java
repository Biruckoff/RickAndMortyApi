package com.example.ramapi_4.AppObject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//This class parse
public class Parser {
    public static final Parser INSTANCE = new Parser();
    private static final String TAG = "Parser";


    private Parser(){}
// Parse status using character's JSON object
    public String parseStatus(JSONObject data){
        String status = "error";
        try{
            status = (String) data.get("status");
        }catch (JSONException e){
            Log.e(TAG,"parseStatus", e);
        }
        return status;
    }
// Parse status using character's JSON object
    public String parseLocation(JSONObject data){
        String location = "error";
        try{
            location = (String) ((JSONObject) data.get("location")).get("name");

        }catch (JSONException e){
            Log.e(TAG,"parseLocation", e);
        }
        return location;
    }
// Parse species using character's JSON object
    public String parseSpecies(JSONObject data){
        String species = "error";
        try{
            species = (String) data.get("species");
        }catch (JSONException e){
            Log.e(TAG,"parseSpecies", e);
        }
        return species;
    }
// Parse Episodes' ids using character's JSON object
    public int[] parseEpisodesIds(JSONObject data) {
        int[] links = null;
        try {
            JSONArray jsonArray = (JSONArray) data.get("episode");
            links = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                links[i] = Integer.parseInt(jsonArray.get(i).toString().replaceAll("[^0-9]", ""));
            }
        }catch (JSONException e){
            Log.e(TAG,"parseEpisodesIds", e);
        }
        return links;
    }

//Parse list of character using JSON object.
    public ArrayList<CartoonPers> parseLOC(JSONObject jsonObject1){
        ArrayList<CartoonPers> arrayList = new ArrayList<>();
        try{
            JSONArray jsonArray = jsonObject1.getJSONArray("results");
            for(int i=0;i<jsonArray.length();i++){
                CartoonPers cartoonPers = new CartoonPers(((JSONObject)jsonArray.get(i)).get("image").toString(),((JSONObject)jsonArray.get(i)).get("name").toString(), Integer.parseInt((((JSONObject) jsonArray.get(i)).get("id")).toString()));
                arrayList.add(cartoonPers);
            }
        }catch (JSONException e){
            Log.e(TAG,"parseLOC", e);
        }

        return arrayList;
    }
//Parse list of episodes using JSON object.
    public ArrayList<Episode> parseEpisodes(JSONObject jsonObject){
        ArrayList<Episode> arrayList = new ArrayList<>();
        try{
            JSONArray jsonArray1 = jsonObject.getJSONArray("results");
            arrayList = getEpisodeJSONArray(jsonArray1);
        }catch (JSONException e){
            Log.e(TAG,"parseEpisodes", e);
        }
        return arrayList;
    }
//Parse list of episodes using JSON object. Support for parseEpisodes()
    private ArrayList<Episode> getEpisodeJSONArray(JSONArray jsonArray1){
        ArrayList<Episode> arrayList = new ArrayList<>();
        int i=0;
        for(;i<jsonArray1.length();i++){
            try{
                arrayList.add(new Episode( ((JSONObject)jsonArray1.get(i)).get("name").toString() , ((JSONObject)jsonArray1.get(i)).get("air_date").toString()));
                arrayList.get(i).setId( ((JSONObject)jsonArray1.get(i)).getInt("id"));
            }catch (JSONException e){
                Log.e(TAG,"getEpisodeJSONArray", e);
            }
        }

        return arrayList;
    }
    //Parse count of pages using JSON objects of main links(characters/episodes/locations)
    public int getCountOfPages(JSONObject jsonObject){
         int pages = 0;
        try{
        JSONObject jsonObject1 =(JSONObject) jsonObject.getJSONObject("info");
        pages = jsonObject1.getInt("pages");
        }catch (JSONException e){
            Log.e(TAG,"getCountOfPages", e);
        }
    return pages;
    }

}
