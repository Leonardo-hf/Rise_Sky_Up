package com.example.uglyread;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class recycler_city extends AppCompatActivity {
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_city);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null)
            actionBar.hide();
        Intent intent=getIntent();
        String place=intent.getStringExtra("place");
        TextView t=findViewById(R.id.diqu);
        if (place.equals("window.getAreaStat = (.*?)\\}(?=catch)")) {
            t.setText("省份");
        }
        if (place.equals("window.getListByCountryTypeService2true = (.*?)\\}(?=catch)")){
            t.setText("国家或地区");
        }
        List<city>cities=show(place);
        while (flag==0)
            Log.e("wdnmd", "do some thing");
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        RecyclerView recyclerView=findViewById(R.id.city_item);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<city> cities1=new ArrayList<>();
        cities1.addAll(cities);
        CityAdapter adapter=new CityAdapter(cities1);
        recyclerView.setAdapter(adapter);
    }
//获得网站html代码
    public List<city> show(final String string){
        final List<city> cities=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s;
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("https://ncov.dxy.cn/ncovh5/view/pneumonia").build();
                    Response response=client.newCall(request).execute();
                    s =response.body().string();
                    //初始化cities
                    String reg = string;
                    Pattern totalPattern = Pattern.compile(reg);
                    Matcher totalMatcher = totalPattern.matcher(s);
                    String result = "";
                    if (totalMatcher.find()) {
                        result = totalMatcher.group(1);
                    }
                    try{
                        JSONArray jsonArray=new JSONArray(result);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String name=jsonObject.getString("provinceName");
                            int current=jsonObject.getInt("currentConfirmedCount"),acquired=jsonObject.getInt("confirmedCount"),
                                    death=jsonObject.getInt("deadCount"),cured=jsonObject.getInt("curedCount");
                            city c=new city(name,current,acquired,death,cured);
                            cities.add(c);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                flag=1;
            }
        }).start();
        return cities;
    }
}