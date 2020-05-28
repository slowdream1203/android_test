package com.example.uicomponenttask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class XmlDefineMenuActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_option_menu);
        textView =  findViewById(R.id.textView);
    }

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.xml_option_menu, menu);
        return true;
    }

    /**
     * 处理选择事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_font_small:
                textView.setTextSize(10 * 2);
                return true;
            case R.id.menu_font_middle:
                textView.setTextSize(16 * 2);
                return true;
            case R.id.menu_font_big:
                textView.setTextSize(20 * 2);
                return true;
            case R.id.menu_normal:
                //显示Toast
                Toast.makeText(XmlDefineMenuActivity.this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_color_red:
                textView.setTextColor(Color.RED);
                return true;
            case R.id.menu_color_black:
                textView.setTextColor(Color.BLACK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
