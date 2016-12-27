package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class IntroActivity extends AppCompatActivity {

    ProgressBar v = null;

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            int percent = msg.what;

            v.setProgress(percent);

            Message a = new Message();

            a.what = percent+10;

            if(percent == 100)
            {
                Intent intent = new Intent(IntroActivity.this, DiaryListActivity.class);
                startActivity(intent);
                finish();
                return;
            }

            handler.sendMessageDelayed(a,1000);



        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        v = (ProgressBar)findViewById(R.id.progressBar);
        Message a = new Message();

        a.what = 10;
        handler.sendMessageDelayed(a,1000);

    }
}
