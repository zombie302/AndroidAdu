package com.example.administrator.naemamdaero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddDiaryListActivity extends AppCompatActivity {
   ListView listview=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add_diary_list);


        int view=R.id.listview;
        listview=(ListView) this.findViewById(view);
        CategoryAddDiaryListAdapter categoryadddiarylistadapter=new CategoryAddDiaryListAdapter();
        categoryadddiarylistadapter.setContext(this);

        listview.setAdapter(categoryadddiarylistadapter);

    }

}
