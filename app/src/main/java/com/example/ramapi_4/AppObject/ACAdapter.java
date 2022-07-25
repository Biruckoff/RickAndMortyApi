package com.example.ramapi_4.AppObject;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ramapi_4.Activity.AboutCharacter;
import com.example.ramapi_4.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ACAdapter extends RecyclerView.Adapter<ACAdapter.ViewHolder>{

    private ArrayList<CartoonPers> dataArrayList;
    private Activity activity;

    public ACAdapter(Activity activity, ArrayList<CartoonPers> dataArrayList){
        this.activity = activity;
        this.dataArrayList = dataArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartoonPers data = dataArrayList.get(position);

        holder.textView.setText(data.getName());
        Picasso.get().load(data.getPic()).into(holder.imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, AboutCharacter.class);
                i.putExtra(CartoonPers.class.getSimpleName(),data);
                activity.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
            button = itemView.findViewById(R.id.button_getMore);

        }
    }
}
