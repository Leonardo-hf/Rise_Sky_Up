package com.example.uglyread;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class second_act extends AppCompatActivity {
    private ArrayList<SingleTech> techList = new ArrayList<>();
    private ArrayList<Integer> isInplement = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        initTech();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.techtree);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SingleTechAdapter singleTechAdapter = new SingleTechAdapter(techList, second_act.this);
        //recyclerView.getRecycledViewPool().setMaxRecycledViews(singleTechAdapter.getItemViewType(),0);
        recyclerView.setAdapter(singleTechAdapter);
    }

    public void initTech() {
        if (Statues.getTechlist().isEmpty()) {
            techList.addAll(LitePal.findAll(SingleTech.class));
            if (Statues.getIsInplement().isEmpty()) {
                for (int i = 0; i < techList.size(); i++)
                    isInplement.add(0);
                Statues.setIsInplement(isInplement);
            }
            Statues.setTechlist(techList);
        } else {
            techList = Statues.getTechlist();
        }
    }
}