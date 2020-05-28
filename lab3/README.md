
### 1、用SimpleAdapter来装配ListView

1. 创建activity_simple_adapter_list_view.xml，该布局文件里设置了一个ListView控件
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent">
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:divider="#000"
        android:dividerHeight="2dp"
        android:listSelector="#E91E1E" >
    </ListView>
</RelativeLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426014108596.png)

2. 再创建simple_adapter_list_view_item.xml，里面设置的ListView的Item
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:gravity="center_vertical">
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:padding="5dp"
        android:layout_alignParentRight="true"
        android:layout_marginRight="10dp"
        android:src="@drawable/lion">
    </ImageView>
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="lion"
        android:layout_marginLeft="10dp"
        android:textSize="25sp"
        android:textColor="#000" >
    </TextView>
</RelativeLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426014831878.png)

3. 创建类SimpleAdapterListViewActivity并指定其view，设置动物名称数组animalName和动物图片数组animalImage，并将他们对应放在一个ArrayList<HashMap<String,String>>的对象中。新增一个数组from，from的值是Map集合里面的key值。新增一个数组to，里面是item布局相应的控件id。创建SimpleAdapter对象，并设置给ListView
```java
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
```
4. 在MainActivity中增加一个按钮，跳转到SimpleAdapterListViewActivity
5. 效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426015506618.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)
### 2、自定义Dialog
1. 创建custom_dialog.xml，实现Dialog的布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:src="@drawable/header_logo"
        android:layout_width="match_parent"
        android:layout_height="64dp"
        android:scaleType="center"
        android:background="#F7FFBB33"
        android:contentDescription="@string/app_name" />
    <EditText
        android:id="@+id/username"
        android:inputType="textEmailAddress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginLeft="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="4dp"
        android:hint="username" />
    <EditText
        android:id="@+id/password"
        android:inputType="textPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:layout_marginLeft="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="16dp"
        android:fontFamily="sans-serif"
        android:hint="password"/>

</LinearLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426015749823.png)

2. 在MainActivity中增加createDialog()方法和按钮来显示Dialog，先创建 AlertDialog.Builder对象，再获得LayoutInflater对象，用AlertDialog.Builder的setView()方法和LayoutInflater的inflate()方法指定view和显示。
```java
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
```

```java
  //自定义对话框
        Button btn_custom_dialog = findViewById(R.id.showCustomDialogButton);
        btn_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
```

3. 效果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426020824635.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)

### 3、使用XML定制选项菜单
1. 先创建需要显示菜单的界面activity_xml_option_menu，里面设置的一个测试文本
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    tools:context=".XmlDefineMenuActivity">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/textView"
        android:text="测试使用 XML 定义菜单"
        android:textSize="30sp"/>
</LinearLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020042602122055.png)


2. 创建选项菜单xml_option_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:title="@string/menu_Font">
        <menu>
            <item
                android:id="@+id/menu_font_small"
                android:title="@string/menu_font_small"/>
            <item
                android:id="@+id/menu_font_middle"
                android:title="@string/menu_font_middle"/>
            <item
                android:id="@+id/menu_font_big"
                android:title="@string/menu_font_big"/>
        </menu>

    </item>
    <item
        android:id="@+id/menu_normal"
        android:title="@string/menu_Normal">
    </item>
    <item android:title="@string/menu_Color">
        <menu>
            <item
                android:id="@+id/menu_color_red"
                android:title="@string/menu_color_red" />
            <item
                android:id="@+id/menu_color_black"
                android:title="@string/menu_color_black"/>
        </menu>
    </item>

</menu>
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426021424676.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)

3. 创建XmlDefineMenuActivity来生成选项菜单，通过onCreateOptionsMenu(Menu menu)方法来显示菜单，通过onOptionsItemSelected(MenuItem item)来处理选择事件
```java
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
```

4. 效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426023014428.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)

### 4、创建上下文操作模式 (ActionMode)的上下文菜单
1. 创建一个listView
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
        <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:divider="#000"
            android:dividerHeight="2dp"
            android:choiceMode="multipleChoice">
        </ListView>
</LinearLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426023311182.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_vertical">
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/android" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="40sp"
        android:layout_marginLeft="30dp"
        android:text="one"
        android:textStyle="bold" />
</LinearLayout>
```
2. 创建上下文菜单
```xml
. <?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/item_delete"
        android:icon="@android:drawable/ic_menu_delete"
        app:showAsAction="ifRoom|withText"
        android:title="Delete"
        android:titleCondensed="Delete">
    </item>
</menu>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426023626199.png)
3.创建ActionModeListViewActivity，创建了一个内部类MySimpleAdapter继承自SimpleAdapter，所以数据装配方式和SimpleAdapter一样，但在给内部类中添加了一些方法来修改选项显示。通过ListView.setMultiChoiceModeListener()方法添加了一个匿名内部类AbsListView.MultiChoiceModeListener，在该匿名内部类中设置了控制上下文菜单显示和选项显示的方法步骤。

```java
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

```

4. 效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200426024313431.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhcm1hbmRmdWxs,size_16,color_FFFFFF,t_70)







