package com.example.administrator.example04;

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
    public void onClick1(View v){
        Intent i = new Intent(this, FullscrrenActivity.class);
        startActivity(i);
    }
    public void onClick2(View v){
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }
}
