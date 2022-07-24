package com.example.ramapi_4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramapi_4.AppObject.CartoonPers;
import com.example.ramapi_4.AsyncTasks.GetInfoAC;
import com.example.ramapi_4.Interfaces.Callback1;
import com.example.ramapi_4.R;
import com.squareup.picasso.Picasso;

public class AboutCharacter extends AppCompatActivity implements Callback1 {

    View view;
    TextView name;
    TextView species;
    TextView location;
    TextView status;
    Button button;
    Button back;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        LayoutInflater ltInflater= getLayoutInflater();
        setAllView(ltInflater);

        Bundle arg = getIntent().getExtras();
        CartoonPers cartoonPers = (CartoonPers) arg.getSerializable(CartoonPers.class.getSimpleName());
        new GetInfoAC(AboutCharacter.this).execute(cartoonPers);
    }

    public void onComplete(CartoonPers result) {
        setFullAct(result);
    }

    private void setFullAct(CartoonPers pers){
        name.setText(pers.getName());
        species.setText(pers.getSpecies());
        location.setText(pers.getLocation());
        status.setText(pers.getStatus());
        Picasso.get().load(pers.getPic()).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutCharacter.this,ListOfEpisodes.class);
                i.putExtra(CartoonPers.class.getSimpleName(),pers);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutCharacter.this, ListOfCharacter.class);
                startActivity(i);
            }
        });

        setContentView(view);
    }

    private void setAllView(LayoutInflater ltInflater){
        view = ltInflater.inflate(R.layout.activity_about_character, null, false);
        name = view.findViewById(R.id.name);
        species = view.findViewById(R.id.species);
        location = view.findViewById(R.id.location);
        status = view.findViewById(R.id.status);
        imageView = view.findViewById(R.id.image);
        button = view.findViewById(R.id.button);
        back = view.findViewById(R.id.back_button);

    }
}