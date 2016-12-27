package com.example.administrator.naemamdaero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

        ListView listView;
        Adapter adapter;

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

        MyDatabase myDB = MyDatabase.getInstance(this);
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
                String str = "test";
                myDB.addCategory(str);
                adapter.addData(str);
                listView.setAdapter(adapter);
                return true;

            /*--------------------------------------삭제확인버튼 클릭---------------------------------------------*/
            case R.id.menu_delBtn:
                menu.clear();

                this.adapter.setCheckMode(false);
                this.listView.invalidate();
                createMenu(R.menu.menu_main, menu);

                //삭제
                // 체크된게 뭐냐?
                ArrayList<Adapter.Item> checkedList = adapter.getCheckedItem();

                for(int i = 0; i<checkedList.size(); i++){
                    Adapter.Item temp = (Adapter.Item)checkedList.get(i);
                    String  name = temp.cName;
                    myDB.removeCategory(name);
                    adapter.removeDataByName(temp.cName);
                }
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                return true;
        }
        //adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public void createMenu(int menuRes, Menu menu)
    {
        getMenuInflater().inflate(menuRes, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
    }
}