package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void intro(View V){
        start(IntroActivity.class);
    }

    public void dList(View V){
        start(DiaryListActivity.class);
    }

    public void dView(View V){
        start(DiaryViewActivity.class);
    }

    public void dEdit(View V){
        start(DiaryEditActivity.class);
    }

    public void dInsert(View V) { start(DiaryInsertActivity.class); }
    

    public void cList(View V){ start(CategoryListActivity.class); }

    public void cDList(View V){
        start(MainActivity.class);
    }

    public void cAddDList(View V){
        start(MainActivity.class);
    }

    public void PopUp1(View V){
        CategoryLists cl = new CategoryLists(MainActivity.this, new CategoryName.OnCloseListener() {
            @Override
            public void onClose(String a) {

            }
        },  "카테고리 선택");
        cl.show();
    }

    public void PopUp2(View V){
        CategoryName cn = new CategoryName(MainActivity.this, new CategoryName.OnCloseListener() {
            @Override
            public void onClose(String a) {

            }
        });
        cn.show();
    }

    private void start(Class cls)
    {
        Intent i = new Intent(this, cls);
        startActivity(i);
    }
}
