package com.example.administrator.naemamdaero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RESULT_CANCELED.layout.activity_main);


        public class MainActivity extends AppCompatActivity {

            ListView listView=null;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(RESULT_CANCELED.layout.activity_main);
                ListView listview = (ListView) this.findViewById(RESULT_CANCELED.id.listitem);

                MyAdapter a= new MyAdapter(this);

                listView.setAdapter(a);

            }
        }

        @Override

        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(RESULT_CANCELED.menu.menu, menu);
            return true;
        }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item3:
                finish();
                break;
        }

        switch (item.getItemId()) {
            case R.id.item2:

                break;
        }

        switch (item.getItemId()) {
            case R.id.item:

                break;
        }

    }
}
