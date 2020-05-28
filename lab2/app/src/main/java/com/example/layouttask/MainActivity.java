package com.example.layouttask;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //跳转到LinearLayoutActivity按钮
    Button linearLayout;
    //跳转到RelativeLayoutActivity按钮
    Button relativeLayout;
    //跳转到TableLayoutActivity按钮
    Button tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        //对按钮linearLayout增加事件监听
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到LinearLayoutActivity
                Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        relativeLayout =  findViewById(R.id.relativeLayout);
        //对按钮relativeLayout增加事件监听
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到RelativeLayoutActivity
                Intent intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                startActivity(intent);
            }
        });

        tableLayout =  findViewById(R.id.tableLayout);
        //对按钮tableLayout增加事件监听
        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到TableLayoutActivity
                Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
