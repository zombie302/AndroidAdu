package com.example.administrator.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void f2(View V){
        Intent nextint = new Intent(this, Main2Activity.class);
        startActivity(nextint);
    }

    public void f3(View V){
        Intent nextint = new Intent(this, Main3Activity.class);
        startActivity(nextint);
    }
}
