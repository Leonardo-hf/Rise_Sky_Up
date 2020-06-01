package com.example.uglyread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingleTech_detail extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tech_detail);
        Intent content=getIntent();
        String detail=content.getStringExtra("content");
        TextView textView=findViewById(R.id.tech_detail_content);
        textView.setText(detail);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
