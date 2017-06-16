package com.whu.faithfish.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.whu.faithfish.adapter.FruitAdapter;
import com.whu.faithfish.pojo.Fruit;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().hide();
        fruitList = initFruit();
        FruitAdapter adapter = new FruitAdapter(ListViewActivity.this,
                R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(BaseActivity.OFFLINEACT);
                sendBroadcast(intent);
            }
        });
    }

    private List<Fruit> initFruit(){
        List<Fruit> list =  new ArrayList<>();
        for(int i=0; i<10; i++){
            Fruit f1 = new Fruit("banana", R.drawable.banana);
            Fruit f2 = new Fruit("cherry", R.drawable.cherry);
            Fruit f3 = new Fruit("grape", R.drawable.grape);
            Fruit f4 = new Fruit("peach", R.drawable.peach);
            Fruit f5 = new Fruit("pineapple", R.drawable.pineapple);
            list.add(f1);
            list.add(f2);
            list.add(f3);
            list.add(f4);
            list.add(f5);
        }
        return list;
    }
}
