package com.example.uglyread;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private ArrayList<city> cities;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,current,acquired,death,cured;
        public ViewHolder(View v){
            super(v);
            name=v.findViewById(R.id.name);
            current=v.findViewById(R.id.current);
            acquired=v.findViewById(R.id.acquired);
            death=v.findViewById(R.id.death);
            cured=v.findViewById(R.id.cure);
        }
    }
    public CityAdapter(ArrayList<city> cities1){
        cities=cities1;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_items,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        city c=cities.get(position);
        holder.name.setText(c.getName());
        holder.current.setText(Integer.toString(c.getCurrent()));
        holder.death.setText(Integer.toString(c.getDeath()));
        holder.acquired.setText(Integer.toString(c.getAcquired()));
        holder.cured.setText(Integer.toString(c.getCured()));

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

}
