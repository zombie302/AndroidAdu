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
        start(MainActivity.class);
    }

    public void dList(View V){
        start(MainActivity.class);
    }

    public void dView(View V){
        start(DiaryViewActivity.class);
    }

    public void dEdit(View V){
        start(DiaryEditActivity.class);
    }

    public void dInsert(View V){
        start(MainActivity.class);
    }

    public void cList(View V){
        start(MainActivity.class);
    }

    public void cDList(View V){
        start(MainActivity.class);
    }

    public void cAddDList(View V){
        start(MainActivity.class);
    }

    public void PopUp1(View V){
        start(MainActivity.class);
    }

    public void PopUp2(View V){
        start(MainActivity.class);
    }

    private void start(Class cls)
    {
        Intent i = new Intent(this, cls);
        startActivity(i);
    }
}
