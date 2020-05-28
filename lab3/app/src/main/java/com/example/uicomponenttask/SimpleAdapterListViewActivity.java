package com.example.uicomponenttask;
import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
public class SimpleAdapterListViewActivity extends Activity {

    //listView对象
    ListView listView;
    //动物名称
    String[] animalName =
            {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    //动物图片
    int[] animalImage =
            {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指定view
        setContentView(R.layout.activity_simple_adapter_list_view);

        //获得listView组件
        listView = findViewById(R.id.listView);

        //创建一个ArrayList对象，对象的内容是HashMap<String,String>
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

        //list中添加hashMap对象，每个hashMap对象都有“name”和“image”两个分别的的键值
        //“name”和“image”的值来自animalName和animalImage数组
        for (int i=0;i<animalName.length;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("name",animalName[i]);
            hashMap.put("image",String.valueOf(animalImage[i]));
            arrayList.add(hashMap);
        }

        //键名
        String[] from={"name","image"};
        //item视图id
        int[] to={R.id.textView,R.id.imageView};
        //创建SimpleAdapter对象
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this,arrayList,
                        R.layout.simple_adapter_list_view_item,
                        from,
                        to);
        //设置listView的适配器
        listView.setAdapter(simpleAdapter);

        //设置Item事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),animalName[i],Toast.LENGTH_SHORT).show();//show the selected image in toast according to position
            }
        });

    }

}
