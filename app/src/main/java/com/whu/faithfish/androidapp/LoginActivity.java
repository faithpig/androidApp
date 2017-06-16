package com.whu.faithfish.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText uid;
    private EditText pwd;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uid = (EditText) findViewById(R.id.edit_uid);
        pwd = (EditText) findViewById(R.id.edit_pwd);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = uid.getText().toString();
                String password = pwd.getText().toString();
                if(("faithfish").equals(account)&&("123456").equals(password)){
                    Intent intent = new Intent(LoginActivity.this, ListViewActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "用户名或者是密码错了！",Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

}
