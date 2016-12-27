package com.example.administrator.naemamdaero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;
import com.example.administrator.naemamdaero.database.MyDbAdapter;

import java.util.ArrayList;

import static com.example.administrator.naemamdaero.database.MyDbAdapter.SEARCH_TYPE.ALL;
import static com.example.administrator.naemamdaero.database.MyDbAdapter.SEARCH_TYPE.TITLE;
import static com.example.administrator.naemamdaero.database.MyDbAdapter.SEARCH_TYPE.CONTENTS;

public class CategoryAddDiaryListActivity extends AppCompatActivity {
    MyDatabase myDatabase = null;
    MyDbAdapter myDbAdapter = null;

    String category = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("");
        setContentView(R.layout.activity_category_add_diary_list);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        category = getIntent().getStringExtra("category");
        ListView view = null;
        view = (ListView)this.findViewById(R.id.lv);

        myDatabase = MyDatabase.getInstance(this);
        myDatabase.open();
        myDatabase.printRecord();
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.setLayout(R.layout.activity_category_add_diary_list_item, R.id.txt_data,R.id.txt_ti,R.id.cb);
        myDbAdapter.loadDiaryDataWithout(category);
        myDbAdapter.setCheckVisible(true);
        view.setAdapter(myDbAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int resId = item.getItemId();

        if(resId == R.id.add){
            ArrayList<MyData> checkedData = myDbAdapter.getCheckedDiaryData();

            for(int i = 0; i < checkedData.size(); i++)
            {
                MyData myData = checkedData.get(i);

                myDatabase.update(myData.getId(), myData.getTitle(), myData.getContent(), category);
            }

            myDbAdapter.loadDiaryDataWithout(category);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadd, menu);
        return true;

    }


    // 버튼 선택시 실행
    public void onSearchClicked(View v)
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
         EditText editText = (EditText) findViewById(R.id.editText);

        int s = spinner.getSelectedItemPosition();
        Editable e = editText.getText();


         //spinner에서 선택된  위치를 가져온다. 0,1,2...

        if(s==0){
           //editable 형의 변수를 받아서 ...
        myDbAdapter.search(TITLE, e.toString());
        }
        if(s==1) {
            myDbAdapter.search(CONTENTS, e.toString());
        }
        if(s==2) {
            myDbAdapter.search(ALL, e.toString());
        }
    }



}
