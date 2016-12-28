package com.example.administrator.naemamdaero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.naemamdaero.database.MyDatabase;

public class DiaryInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_insert);
        this.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate( R.menu.menu2,menu2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btnCreat:
                // 항목이 선택되었음

                Toast.makeText(getApplicationContext(),"저장 되었습니다.", Toast.LENGTH_LONG).show();
                MyDatabase myDatabase = MyDatabase.getInstance(this);
                myDatabase.open();

                EditText a = (EditText)this.findViewById(R.id.editText);
                EditText b = (EditText)this.findViewById(R.id.editText2);
                Button c = (Button)this.findViewById(R.id.button);
                String d = a.getText().toString();
                String e = b.getText().toString();
                String f = c.getText().toString();

                myDatabase.insert(d,e,f);
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
                Button c = (Button)DiaryInsertActivity.this.findViewById(R.id.button);
                c.setText(a);
            }
        }, "카테고리 선택");

        categoryListsDlg.show();
    }
}
