package com.example.administrator.naemamdaero;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

class setListView{
    ListView listView;
    Adapter adapter;

}


public class CategoryListActivity extends AppCompatActivity {

        ListView listView;
        Adapter adapter;

        public void setListView(){
            setContentView(R.layout.activity_category_list);
            listView = (ListView) findViewById(R.id.listView);
            adapter = new Adapter(this);
            listView.setAdapter(adapter);
        }

        public void onDeleteButtonCancleClicked(View v){
            this.adapter.setCheckMode(false);
            this.listView.invalidate();
            listView.setAdapter(adapter);
        }

        public void onDeleteButtonClicked(View v){
            this.adapter.setCheckMode(false);
            this.listView.invalidate();
            listView.setAdapter(adapter);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_category_list);

            listView = (ListView) findViewById(R.id.listView);

            CheckBox c ;

            String ary[] = {"a", "b", "okay?", "String", "9", "4", "e", "d", "b", "q", "ehhhhhhh??"};

            adapter = new Adapter(this);

            /*adapter.addData("1");
            adapter.addData("2");
            adapter.addData("3");
            adapter.addData("4");
            adapter.addData("5");
            adapter.addData("6");
            adapter.addData("7");
            adapter.addData("8");
            adapter.addData("9");
            adapter.addData("10");
            adapter.addData("11");
            adapter.addData("12");
            adapter.addData("13");
            adapter.addData("14");
            adapter.addData("15");
            adapter.addData("16");
            adapter.addData("17");
            adapter.addData("Final!");*/
            for(int i = 0; i<11; i++)
                adapter.addData(ary[i]);

            listView.setAdapter(adapter);



        }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("카테고리");
        this.menu = menu;
        createMenu(R.menu.menu_main, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d("test", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch(id) {
            case R.id.menu_delete:
                this.adapter.setCheckMode(true);
                this.listView.invalidate();
                listView.setAdapter(adapter);
                menu.close();
                menu.clear();
                createMenu(R.menu.menu_delete, menu);
                return true;

            case R.id.menu_add:
                this.listView.invalidate();
                return true;

            case R.id.menu_delBtn:
                menu.clear();

                this.adapter.setCheckMode(false);
                this.listView.invalidate();
                listView.setAdapter(adapter);
                createMenu(R.menu.menu_main, menu);

                //삭제
                // 체크된게 뭐냐?
                adapter.getCheckedItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createMenu(int menuRes, Menu menu)
    {
        getMenuInflater().inflate(menuRes, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
    }
}