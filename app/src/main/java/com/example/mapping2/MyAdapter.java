package com.example.mapping2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public MyAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    Context context;
    ArrayList arrayList;

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        mapsinfo map = (mapsinfo) arrayList.get(position);
        holder.tv1.setText(map.name);
        holder.tv2.setText(map.review);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MapsActivity.class);
                i.putExtra("longitude",map.longitide);
                i.putExtra("latitude",map.latitide);
                view.getContext().startActivity(i);

            }
        });
     
        
    }

    @Override
    public int getItemCount() {
     return  arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv1,tv2;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.nameTextView);
            tv2 = itemView.findViewById(R.id.reviewTextView);
            btn = itemView.findViewById(R.id.viewOnMapButton);


        }
    }
}
