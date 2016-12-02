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

    public void startActivity(Class cls)
    {
        Intent i = new Intent(this, cls);
        this.startActivity(i);
    }

    public void onIntro(View v)
    {
    }

    public void onDiaryList(View v)
    {
    }

    public void onDiaryView(View v)
    {
    }

    public void onDiaryEdit(View v)
    {
    }

    public void onDiaryInsert(View v)
    {
    }

    public void onCategoryList(View v)
    {
    }

    public void onCategoryDiaryList(View v)
    {
    }

    public void onCategoryAddDiaryList(View v)
    {
    }

    public void onCategoryInsertDlg(View v)
    {
    }

    public void onCategoryListDlg(View v)
    {
        MyDialog dialog = new MyDialog(this);
        dialog.show();
    }


}
