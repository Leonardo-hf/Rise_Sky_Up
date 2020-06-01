package com.example.uglyread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.jar.Attributes;

public class TitleLayout extends LinearLayout {
    //private static Activity mActivity;
    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context,attrs);
        //mActivity = activity;
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button buttonf=(Button)findViewById(R.id.buttonf);
        Button buttonb=(Button)findViewById(R.id.buttonb);
        buttonf.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "其实这只是一个装饰品", Toast.LENGTH_SHORT).show();
                /*KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_BACK);
                mActivity.onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);*/
            }
        });

        buttonb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,tosave.class);
                //intent.putExtra("wheretosave",1);
                getContext().startActivity(intent);}

        });
    }
}

