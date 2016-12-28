package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    String add;
    MyDatabase myDB;

    public void setListView(){
        setContentView(R.layout.activity_category_list);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new Adapter(this);
        listView.setAdapter(adapter);
    }

    public void onDeleteButtonCancleClicked(View v){
        this.adapter.setCheckMode(false);
        this.listView.invalidate();
        listView.setAdapter(adapter);
    }

    public void onDeleteButtonClicked(View v){
        this.adapter.setCheckMode(false);
        this.listView.invalidate();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        listView = (ListView) findViewById(R.id.listView);
        //ArrayList<String> list =  null;

        CheckBox c ;

        adapter = new Adapter(this);

        MyDatabase myDB = MyDatabase.getInstance(this);
        myDB.open();

        ArrayList<String> list = myDB.getCategory();

        for(int i = 0; i<list.size(); i++)
            adapter.addData(list.get(i));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CategoryListActivity.this, CategoryDiaryListActivity.class);
                Adapter.Item item = (Adapter.Item)adapter.getItem(position);
                i.putExtra("category", item.cName);
                startActivity(i);
            }
        });
    }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("카테고리");
        this.menu = menu;
        createMenu(R.menu.menu_main, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        myDB = MyDatabase.getInstance(this);
        myDB.open();
        Log.d("test", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch(id) {
            /*--------------------------------------삭제버튼 클릭---------------------------------------------*/
            case R.id.menu_delete:
                this.adapter.setCheckMode(true);
                this.listView.invalidate();
                listView.setAdapter(adapter);
                menu.close();
                menu.clear();
                createMenu(R.menu.menu_delete, menu);
                return true;

            /*--------------------------------------추가---------------------------------------------*/
            case R.id.menu_add:
                this.listView.invalidate();

                CategoryName categoryName = new CategoryName(CategoryListActivity.this, new CategoryName.OnCloseListener(){
                    @Override
                    public void onClose(String a) {
                        CategoryListActivity.this.add = a;

                        if(myDB.addCategory(add)==true) {
                            adapter.addData(add);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Category Overlap.", Toast.LENGTH_LONG).show();
                        }

                        listView.setAdapter(adapter);
                    }
                });
                categoryName.show();

                return true;

            /*--------------------------------------삭제확인버튼 클릭---------------------------------------------*/
            case R.id.menu_delBtn:
                menu.clear();

                this.adapter.setCheckMode(false);
                this.listView.invalidate();
                createMenu(R.menu.menu_main, menu);

                ArrayList<Adapter.Item> checkedList = adapter.getCheckedItem();
                for(int i = 0; i<checkedList.size(); i++) {
                    Adapter.Item temp = (Adapter.Item)checkedList.get(i);
                    String name = temp.cName;
                    myDB.deleteCategory(name);
                    adapter.removeData(temp);
                }

                listView.setAdapter(adapter);
                return true;
        }
        //listView.invalidateViews();
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public void createMenu(int menuRes, Menu menu)
    {
        getMenuInflater().inflate(menuRes, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
    }
}