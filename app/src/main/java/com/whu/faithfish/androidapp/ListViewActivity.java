package com.whu.faithfish.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    private String[] data = {"apple","apple","bnana","apple","bnana","apple","bnana",
            "apple","bnana","apple","bnana","apple","bnana","apple","bnana",
            "apple","bnana","apple","bnana","apple","bnana","apple","bnana","apple","bnana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListViewActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
