package com.example.administrator.naemamdaero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }
}
