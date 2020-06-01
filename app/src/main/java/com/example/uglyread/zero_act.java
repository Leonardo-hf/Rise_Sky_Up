package com.example.uglyread;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zero_act extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zero_layout);
        //new game 初始化数据库-选择数据库
        //先置事件测试
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null)
            actionBar.hide();
        MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(this);
        myDatabaseHelper.openDatabase();
        myDatabaseHelper.closeDatabase();
        LitePal.getDatabase();
        Button continues = (Button)findViewById(R.id.continues);
        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(zero_act.this,tosave.class);
            intent.putExtra("wheretosave", 1);
            startActivity(intent);
        }
        });
        final Button setting=findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zero_act.this", "onClick: ");
                Intent intent=new Intent(zero_act.this,setting.class);
                startActivity(intent);

            }
        });
        final Button start = (Button)findViewById(R.id.starts);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] temp=new int[100];
                for(int n=0;n<temp.length;n++)
                    temp[n]=1;
                ArrayList<SingleTech> temp2 = new ArrayList<>();
                Statues.setTosolve(temp2);
                Statues.setTechlist(temp2);
                Statues.setLiveEvent(temp);
                Statues.setEvent_index(0);
                Statues.setEventlist(new ArrayList<Event>());
                Statues.setIsInplement(new ArrayList<Integer>());
                Random example=new Random();
                Statues.setSatisfaction(example.nextInt(21)+20);
                Statues.setFame(example.nextInt(11)+15);
                Statues.setMoney(example.nextInt(501)+1000);
                Statues.setDay(1);
                Statues.setLeft(25);
                Statues.setKeyunlock(new int[100]);
                Statues.setStage(1);
                Statues.setPeople(10000);
                Statues.setDeath(0);

                ///////////////////////////
                Intent intent=new Intent(zero_act.this,first_act.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        final Button feed=findViewById(R.id.feed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(zero_act.this,Feed.class);
                startActivity(intent);
            }
        });
    }
}
