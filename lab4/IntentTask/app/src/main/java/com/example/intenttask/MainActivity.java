package com.example.intenttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText urlEditText;
    Button goButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.editText);
        goButton = findViewById(R.id.bottom);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取网址
                String url = urlEditText.getText().toString();
                Intent intent = new Intent();
                //将url字符串解析为uri对象
                Uri uri = Uri.parse(url);
                //设置data
                intent.setData(uri);
                //设置动作为ACTION_VIEW，为了启动隐式Intent
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }
}
