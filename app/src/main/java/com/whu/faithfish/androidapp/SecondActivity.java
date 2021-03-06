package com.whu.faithfish.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String content = intent.getStringExtra(MainActivity.MSG_NAME);
        TextView textView = new TextView(this);
        textView.setText(content);
        ViewGroup vg = (ViewGroup) findViewById(R.id.activity_second);
        vg.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this,"生成520！!",Toast.LENGTH_LONG).show();
                TextView txtv = (TextView) view;
                txtv.setText("520!");
            }
        });

    }
}
