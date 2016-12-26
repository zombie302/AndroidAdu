package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class DiaryListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    cheakAdapter adapterm;
    sideAdapter sideAdapter1;
    Layout layout;
    DrawerLayout DLayout;
    LinearLayout SLayout;
    ListView listview ;
    ListView listview2 ;


    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.arrow_down_float);


        LinearLayout layout = (LinearLayout) findViewById(R.id.side_Layout);
        DrawerLayout DrawerLayout = (DrawerLayout) findViewById(R.id.Drawer_Layout);

        this.DLayout = DrawerLayout;
        this.SLayout = layout;



        // Adapter 생성
        adapterm = new cheakAdapter() ;
        sideAdapter1 = new sideAdapter() ;


        adapterm.addItem("제목","2016-12-21");
        adapterm.addItem("asdfaaaaaaaaaaaaa","fdsa");
        adapterm.addItem("asdfaaaaaaaa9llllllllaaaaa","fdsa");

        sideAdapter1.addItem("asdf1");
        sideAdapter1.addItem("asdf2");
        sideAdapter1.addItem("asdf3");

        listview = (ListView) findViewById(R.id.list_main);
        listview.setAdapter(adapterm);

        listview2 = (ListView) findViewById((R.id.ListView_side)) ;
        listview2.setAdapter(sideAdapter1);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ad = new Intent(DiaryListActivity.this,DiaryViewActivity.class);
                startActivity(ad);
                return;
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.SO);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_mainlist, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(DLayout.isDrawerOpen(SLayout))
            DLayout.closeDrawer(SLayout);
        else if (DLayout.isDrawerOpen(SLayout)==false && adapterm.isCheckMode==true) {
            adapterm.isCheckMode = false;
            listview.invalidateViews();
        }
        else
            super.onBackPressed();
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                if (DLayout.isDrawerOpen(SLayout))
                    DLayout.closeDrawer(SLayout);
                else
                    DLayout.openDrawer(SLayout);
                break;
            case R.id.menu_w:
                Intent i = new Intent(this,DiaryEditActivity.class);
                startActivity(i);
                break;
            case R.id.menu_d:
                adapterm.isCheckMode = true;
                listview.invalidateViews();
                break;
            case R.id.menu_s:
                Intent j = new Intent(this,CategoryListActivity.class);
                startActivity(j);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
