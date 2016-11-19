package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class testRoot extends AppCompatActivity {

    private static testRoot self = null;

    public static testRoot getInstance()
    {
        return self;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_root);

        findViewById(R.id.a).setOnClickListener(click);
        findViewById(R.id.b).setOnClickListener(click);
        findViewById(R.id.c).setOnClickListener(click);
        findViewById(R.id.d).setOnClickListener(click);
        findViewById(R.id.e).setOnClickListener(click);
        findViewById(R.id.f).setOnClickListener(click);
        findViewById(R.id.g).setOnClickListener(click);
        findViewById(R.id.h).setOnClickListener(click);
        findViewById(R.id.i).setOnClickListener(click);
        findViewById(R.id.j).setOnClickListener(click);

        self = this;
    }

    Button.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View V) {
            Intent nextInt = null;
            switch(V.getId()){
                case R.id.a:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.b:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.c:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.d:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.e:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.f:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.g:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.h:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.i:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
                case R.id.j:
                    nextInt = new Intent(testRoot.this, MainActivity.class);
                    break;
            }
            startActivity(nextInt);
        }
    };
}
