package com.example.uicomponenttask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ActionModeListViewActivity extends Activity {

    //listView对象
    ListView listView;

    String[] data = {"One", "Two", "Three", "Four", "Five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指定view
        setContentView(R.layout.activity_action_mode_list_view);

        //获得listView组件
        listView = findViewById(R.id.listView);

        //创建一个ArrayList对象，对象的内容是HashMap<String,String>
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("data", data[i]);
            arrayList.add(hashMap);
        }

        //键名
        String[] from = {"data"};
        //item视图id
        int[] to = {R.id.textView};
        //创建SimpleAdapter对象
        final MySimpleAdapter mySimpleAdapter =
                new MySimpleAdapter(this,
                        arrayList,
                        R.layout.action_mode_list_view_item,
                        from,
                        to);
        //设置listView的适配器
        listView.setAdapter(mySimpleAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener(){
            int n = 0;


            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                n = 0;
                //创建菜单
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_delete:
                        Toast.makeText(getApplicationContext(), "删除成功",Toast.LENGTH_SHORT).show();
                        n = 0;
                        mySimpleAdapter.clearSelection();
                        //退出
                        mode.finish();
                }
                return true;
//                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mySimpleAdapter.clearSelection();
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Toast.makeText(getApplicationContext(), position+"",Toast.LENGTH_SHORT).show();
                //更新选项背景颜色
                if (checked) {
                    n++;
                    mySimpleAdapter.setNewSelection(position, true);
                } else {
                    n--;
                    mySimpleAdapter.removeSelection(position);
                }
                mode.setTitle(n + " selected");

            }
        });

    }
    private class MySimpleAdapter extends SimpleAdapter{
        private HashMap<Integer, Boolean> mSelection = new HashMap<>();

        MySimpleAdapter(Context context, List<HashMap<String, String>> data, int resource, String[] from, int[] to){
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            //更换Item背景颜色
            v.setBackgroundColor(getResources().getColor(android.R.color.background_light, null));

            if (mSelection.get(position) != null) {
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light, null));
            }
            return v;
        }

        //选中某个选项
        public void setNewSelection(int position, boolean value) {
            mSelection.put(position, value);
            notifyDataSetChanged();
        }

        //取消某个选项
        public void removeSelection(int position) {
            mSelection.remove(position);
            notifyDataSetChanged();
        }
        //取消所有选项
        public void clearSelection() {
            mSelection = new HashMap<>();
            notifyDataSetChanged();
        }

    }

}
