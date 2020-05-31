## Notepad期中实验

### 1.时间戳
#### 1.1 修改noteslist_item.xml
在noteslist_item.xml中额外添加一个textview来显示时间戳。

```java
    <TextView
        android:id="@+id/text2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingLeft="5dip"
        android:singleLine="true"
        android:gravity="center_vertical"
    />
```
#### 1.2 修改时间戳类型
在NotePadProvider中的insert方法和NoteEditor中的updateNote方法中修改时间戳格式。

NotePadProvider中的insert方法:

```java
Long now = Long.valueOf(System.currentTimeMillis());
//将毫秒数转换为时间的形式yy.MM.dd HH:mm:ss
Date date = new Date(now);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
String dateFormat = simpleDateFormat.format(date);
```
NoteEditor中的updateNote方法:

```java
long now = System.currentTimeMillis();
Date date = new Date(now);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
String dateFormat = simpleDateFormat.format(date);
```

#### 1.3 NoteList的修改

将PROJECTION添加上修改时间，将修改时间的列也投影出来.

```java
private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,//添加修改时间
    };
```
再将显示列dataColumns和他们的viewIDs加入修改时间这一属性

```java
String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE }//加入修改时间;
int[] viewIDs = { android.R.id.text1, R.id.text2 }
```
### 2.笔记查询
#### 2.1 添加笔记查询图标和选择事件
在list_option_menu.xml，添加一个搜索图标

```java
    <item
        android:id="@+id/menu_search"
        android:icon="@android:drawable/ic_search_category_default"
        android:showAsAction="always"
        android:title="search">
    </item>
```
在函数boolean onOptionsItemSelected(MenuItem item)中添加case:

```java
case R.id.menu_search:
                Intent intent = new Intent();
                intent.setClass(this, NoteSearch.class);
                this.startActivity(intent);
                return true;
```
#### 2.2 搜索部分新建Activity。
新建一个NoteSearch的Activity，布局中包含搜索框SearchView和列表框ListView

```java
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <SearchView
        android:id="@+id/search_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:iconifiedByDefault="false"
        android:queryHint="搜索">
    </SearchView>
    <ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        >
    </ListView>
</LinearLayout>
```
在NoteSearch.java中，为ListView和SearchVIew添加监听事件

```java

        search.setOnQueryTextListener(NoteSearch.this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //item触发点击事件后跳转到相应页面。
                Uri uri = ContentUris.withAppendedId(getIntent().getData(), l);
                String action = getIntent().getAction();
                if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
                    setResult(RESULT_OK, new Intent().setData(uri));
                } else {
                    startActivity(new Intent(Intent.ACTION_EDIT, uri));
                }
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String newText) {//搜索框内容发生改变后实施查询操作。
        Cursor cursor=sqLiteDatabase.query(
                NotePad.Notes.TABLE_NAME,
                PROJECTION,
                NotePad.Notes.COLUMN_NAME_TITLE+" like ? or "+NotePad.Notes.COLUMN_NAME_NOTE+" like ?",
                new String[]{"%"+newText+"%","%"+newText+"%"},
                null,
                null,
                NotePad.Notes.DEFAULT_SORT_ORDER);
        int[] viewIDs = { R.id.text3,R.id.text4};
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                NoteSearch.this,                             
                R.layout.searchlist_item,          
                cursor,                           
                new String[]{NotePad.Notes.COLUMN_NAME_TITLE,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE},
                viewIDs
        );
        listview.setAdapter(adapter);
        return true;
    }
```



