package com.example.administrator.naemamdaero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;

public class DiaryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_diary_view);

        TextView v = (TextView)findViewById(R.id.textView);
        TextView b = (TextView)findViewById(R.id.textView3);
        TextView c = (TextView)findViewById(R.id.textView2);
        TextView f = (TextView)findViewById(R.id.button2);

        MyDatabase m = MyDatabase.getInstance(this);
        m.open();

        MyData e = m.read(1);

        v.setText(e.getTime());
        b.setText(e.getTitle());
        c.setText(e.getContent());
        f.setText(e.getCategory());


        //
    }

    public void onButton2Clicked(View v){
//        Intent myIntent = new Intent(this, DiaryEditActivity.class);
//        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate( R.menu.menu_diaryview,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(getApplicationContext(), "삭제 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.edit:
                Toast.makeText(getApplicationContext(), "편집 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickButton(View v){
        Toast.makeText(getApplicationContext(),"카테고리 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

}
