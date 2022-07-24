package com.example.ramapi_4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AsyncTasks.GetInfoLOE;
import com.example.ramapi_4.Interfaces.Callback1;
import com.example.ramapi_4.R;

import java.util.Arrays;

public class ListOfEpisodes extends AppCompatActivity implements Callback1 {

    private static final String TAG = "ListOfEpisodes";
    View view;
    Button button;
    Button sortBy;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading);

        LayoutInflater ltInflater = getLayoutInflater();
        setFullView(ltInflater);

        Bundle arg = getIntent().getExtras();
        CartoonPers cartoonPers = (CartoonPers) arg.getSerializable(CartoonPers.class.getSimpleName());

        new GetInfoLOE(ListOfEpisodes.this).execute(cartoonPers);
        flag = false;
        sortBy.setText("Sort by alph");// Перенести в onComplete
    }
    public void onComplete(CartoonPers result) {
        setFullAct(result);
    }


    private void setFullAct(CartoonPers pers){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListOfEpisodes.this,AboutCharacter.class);
                i.putExtra(CartoonPers.class.getSimpleName(),pers);
                startActivity(i);
            }
        });

        sortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                if(flag){
                    sortBy.setText("Sort by date");}else{sortBy.setText("Sort by alph");}

                new GetInfoLOE(ListOfEpisodes.this).execute(pers);
            }
        });

        ListView lvMain = (ListView) view.findViewById(R.id.lv);
        try{
        String[] names = new String[pers.getEpisodes().length];
        for(int i=0;i<pers.getEpisodes().length; i++){
            names[i] = pers.getEpisodes()[i].getName() +" "+ pers.getEpisodes()[i].getDate();
        }
        if(flag) Arrays.sort(names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_of_episode, names);


        lvMain.setAdapter(adapter);
        }catch (NullPointerException e){
            Log.e(TAG,"setFullAct", e);
        }
        setContentView(view);
    }

    private void setFullView(LayoutInflater ltInflater){
        view = ltInflater.inflate(R.layout.activity_list_of_episodes, null, false);
        button = view.findViewById(R.id.button1);
        sortBy = view.findViewById(R.id.sortBy);

    }
}