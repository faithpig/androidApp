package com.whu.faithfish.androidapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected final static String MSG_NAME = "com.whu.faithfish.androidapp";
    private static final String TAG = "MainActivity";//logt+Tab键自动生成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.del_item:
                Toast.makeText(this,"del",Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
        }
        return true;
    }

    public void sendMessage(View view){
//        Intent intent = new Intent(this, SecondActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String msg = editText.getText().toString();
//        intent.putExtra(MSG_NAME,msg);
//        startActivity(intent);
//        Intent intent = new Intent("com.whu.faithfish.ACTION_START");
//        intent.addCategory("com.whu.faithfish.MY_CATEGORY");
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:10086"));
        startActivity(intent);
    }
}
