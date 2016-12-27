package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

public class DiaryListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    cheakAdapter adapterm;
    sideAdapter sideAdapter1;
    Layout layout;
    DrawerLayout DLayout;
    LinearLayout SLayout;
    ListView listview ;
    ListView listview2 ;
    MyDatabase MDB;
    Spinner spinner;
    TextView sTx;
    MenuInflater menuInflater;
    Menu menu;


    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapterm.update(MDB);
        sideAdapter1.update(MDB);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("전체");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        MDB = MyDatabase.getInstance(this);
        MDB.open();


        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.arrow_down_float);

        LinearLayout layout = (LinearLayout) findViewById(R.id.side_Layout);
        DrawerLayout DrawerLayout = (DrawerLayout) findViewById(R.id.Drawer_Layout);

        this.DLayout = DrawerLayout;
        this.SLayout = layout;

        // Adapter 생성
        adapterm = new cheakAdapter() ;
        sideAdapter1 = new sideAdapter() ;

        adapterm.update(MDB);
        sideAdapter1.update(MDB);

        listview = (ListView) findViewById(R.id.list_main);
        listview.setAdapter(adapterm);

        listview2 = (ListView) findViewById((R.id.ListView_side)) ;
        listview2.setAdapter(sideAdapter1);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ad = new Intent(DiaryListActivity.this,DiaryViewActivity.class);
                ad.putExtra("id",adapterm.list.get(position).getId());

                Log.i("DiaryListActivity","id = "+adapterm.list.get(position).getId());
                startActivity(ad);
                return;
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sideListViewItem obj = (sideListViewItem)sideAdapter1.getItem(position);
                setTitle(obj.getarr1());
                DLayout.closeDrawer(SLayout);
                ArrayList<MyData> array =  sideAdapter1.CG(MDB,position);
                adapterm.CListUp(array);
                adapterm.notifyDataSetChanged();
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
        this.menu = menu;
        menuInflater = getMenuInflater();
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
                Intent i = new Intent(this,DiaryInsertActivity.class);
                startActivity(i);
                adapterm.update(MDB);
                break;
            case R.id.menu_d:
                menu.clear();
                adapterm.isCheckMode = true;
                menuInflater.inflate(R.menu.rm_option, menu);
                listview.invalidateViews();
                break;
            case R.id.menu_s:
                Intent j = new Intent(this,CategoryListActivity.class);
                startActivity(j);
                break;
            case R.id.menu_ok:
                menu.clear();
                adapterm.delCheckData(MDB);
                menuInflater.inflate(R.menu.option_mainlist, menu);
                adapterm.isCheckMode = false;
                listview.invalidateViews();
                break;
            case R.id.menu_cancle:
                menu.clear();
                menuInflater.inflate(R.menu.option_mainlist, menu);
                adapterm.isCheckMode = false;
                listview.invalidateViews();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void onSC(View v){
        sTx = (TextView) findViewById(R.id.ST);
        String text = sTx.getText().toString();
        spinner = (Spinner) findViewById(R.id.SO);
        String spInt = (String)spinner.getSelectedItem();
        switch (spInt){
            case "제목":
                adapterm.tFilter(MDB,text);
                listview.invalidateViews();
                break;
            case "내용":
                adapterm.cFilter(MDB,text);
                listview.invalidateViews();
                break;
            case "제목+내용":
                adapterm.tcFilter(MDB,text);
                listview.invalidateViews();
                break;
        }
    }

    public void onSideAllText(View v){
        adapterm.update(MDB);
        DLayout.closeDrawer(SLayout);
        this.setTitle("전체");
        listview.invalidateViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
