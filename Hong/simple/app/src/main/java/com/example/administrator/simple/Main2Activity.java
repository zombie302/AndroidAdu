package com.example.administrator.simple;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();
        this.getWindow().addFlags(FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
    }

    public void prev(View V){
        Intent prevint = new Intent(this, MainActivity.class);
        startActivity(prevint);
    }
}
