package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;

public class DiaryEditActivity extends AppCompatActivity {
     long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        this.getSupportActionBar().setTitle("");

        MyDatabase myDatabase = MyDatabase.getInstance(this);
        myDatabase.open();

        Intent intent = this.getIntent();
        id = getIntent().getLongExtra( "id", -1);

        Log.i("DiaryViewActivity","id = "+id);
        MyData m = myDatabase.read(id);

        EditText a = (EditText)this.findViewById(R.id.editText4);
        EditText b = (EditText)this.findViewById(R.id.editText5);
        Button c = (Button)this.findViewById(R.id.button3);
        a.setText(m.getTitle());
        b.setText(m.getContent());

        if(m.getCategory().equals("") == false)
            c.setText(m.getCategory());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btnMod:
                // 항목이 선택되었음
                //Toast.makeText(getApplicationContext(),"수정 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
                MyDatabase myDatabase = MyDatabase.getInstance(this);
                myDatabase.open();

                EditText a = (EditText)this.findViewById(R.id.editText4);
                EditText b = (EditText)this.findViewById(R.id.editText5);
                Button c = (Button)this.findViewById(R.id.button3);
                String d = a.getText().toString();
                String e = b.getText().toString();
                String f = c.getText().toString();

                myDatabase.update(id,d,e,f);
                myDatabase.printRecord();

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onbutton1(View v){
        CategoryLists categoryListsDlg = new CategoryLists(this, new CategoryName.OnCloseListener() {
            @Override
            public void onClose(String a) {
                Button c = (Button)DiaryEditActivity.this.findViewById(R.id.button3);
                c.setText(a);
            }
        }, "카테고리 선택");

        categoryListsDlg.show();
    }

}
