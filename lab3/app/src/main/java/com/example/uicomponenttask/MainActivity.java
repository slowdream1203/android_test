package com.example.uicomponenttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //用SimpleAdapted装配的ListView
        Button listViewButton = findViewById(R.id.listViewButton);
        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  SimpleAdapterListViewActivity.class);
                startActivity(intent);
            }
        });

        //自定义对话框
        Button btn_custom_dialog = findViewById(R.id.showCustomDialogButton);
        btn_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        //XML 定义菜单
        Button btn_xml_menu = findViewById(R.id.xmlMenuButton);
        btn_xml_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, XmlDefineMenuActivity.class);
                startActivity(intent);
            }
        });
//
        Button btn_action_context = findViewById(R.id.actionModeContextMenuButton);
        btn_action_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActionModeListViewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        // Inflate and set the layout for the dialog
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
                //设置按钮名称和事件监听
                .setPositiveButton(R.string.signIn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        //创建
        builder.create();
        //显示
        builder.show();
    }
}
