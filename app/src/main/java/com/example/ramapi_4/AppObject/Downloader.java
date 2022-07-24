package com.example.ramapi_4.AppObject;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ramapi_4.AppCon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
//This class get response from server
public class Downloader {
   public static final Downloader INSTANCE = new Downloader();
   private static final String TAG = "Downloader";


   private Downloader(){}

   public String getStringAbout(int id,String BASEURL){
      String result = "";

      try {
         URL url = new URL( BASEURL + id);

         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         result = fromStreamToString(con);
         con.disconnect();
      }catch (MalformedURLException e){
         Log.e(TAG,"getStringAbout", e);
      }catch (ProtocolException e){
         Log.e(TAG,"getStringAbout", e);
      }catch (IOException e){
         Log.e(TAG,"getStringAbout", e);
      }
      return result ;
   }

   public String getStringAbout(String BASEURL){
      String result = "";

      try {
         URL url = new URL( BASEURL);

         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         result = fromStreamToString(con);
         con.disconnect();
      }catch (MalformedURLException e){
         Log.e(TAG,"getStringAbout", e);
      }catch (ProtocolException e){
         Log.e(TAG,"getStringAbout", e);
      }catch (IOException e){
         Log.e(TAG,"getStringAbout", e);
      }
      return result ;

   }


   private String fromStreamToString(HttpURLConnection connection){

      try {
         BufferedReader in = new BufferedReader(
                 new InputStreamReader(connection.getInputStream()));
         StringBuffer content = new StringBuffer();
         String inputLine;
         while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
         }
         in.close();
         return content.toString();
      }catch(IOException e){
         Log.e(TAG,"fromStreamToString", e);
         return null;
      }

   }
}

