package com.example.ramapi_4.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.ramapi_4.AppCon;
import com.example.ramapi_4.AppObject.ACAdapter;
import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AppObject.Downloader;
import com.example.ramapi_4.AppObject.Parser;
import com.example.ramapi_4.AsyncTasks.CheckForUpdates;
import com.example.ramapi_4.AsyncTasks.GetInfoLOC;
import com.example.ramapi_4.Database.Character.DatabaseCharacter;
import com.example.ramapi_4.Database.DoubleKey.DatabaseDoubleKey;
import com.example.ramapi_4.Database.Episode.DatabaseEpisode;
import com.example.ramapi_4.Interfaces.Callback2;
import com.example.ramapi_4.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListOfCharacter extends AppCompatActivity implements Callback2 {
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ArrayList<CartoonPers> dataArrayList;
    ACAdapter adapter;
    int page =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.loading);

        if(AppCon.isOnline()){
            new CheckForUpdates().execute();}
            new GetInfoLOC(ListOfCharacter.this).execute(page);
    }


    public void onComplete(ArrayList<CartoonPers> result) {
            if(page==1){
            setContentView(R.layout.activity_list_of_character);
            nestedScrollView = findViewById(R.id.scroll_view);
            recyclerView = findViewById(R.id.recycler_view);
            setData(result);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));}

            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if(scrollY == v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){

                        setData(result);

                        new GetInfoLOC(ListOfCharacter.this).execute(++page);

                    }

                }
            });
    }

    public void setData(ArrayList<CartoonPers> list){
            if(page==1) dataArrayList = list;
            if(page!=1) dataArrayList.addAll(list);
            adapter = new ACAdapter(ListOfCharacter.this, dataArrayList);
            recyclerView.setAdapter(adapter);
        }

    }
