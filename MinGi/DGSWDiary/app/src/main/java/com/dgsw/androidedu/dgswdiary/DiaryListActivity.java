package com.dgsw.androidedu.dgswdiary;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class DiaryListActivity extends AppCompatActivity {

    private DrawerLayout folderPanel = null;
    private Spinner spinner = null;
    private ListView diaryList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        this.getSupportActionBar().setTitle("고경현");
        this.getSupportActionBar().setHomeAsUpIndicator(R.mipmap.folder);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        folderPanel = (DrawerLayout)this.findViewById(R.id.folderPanel);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_conditions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        diaryList = (ListView)this.findViewById(R.id.diaryList);

        diaryList.setAdapter(new DiaryListAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.menu_diary_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int resId = item.getItemId();

        switch(resId)
        {
            case android.R.id.home:
                if(folderPanel.isDrawerOpen(GravityCompat.START))
                    folderPanel.closeDrawer(GravityCompat.START);
                else
                    folderPanel.openDrawer(GravityCompat.START);
                break;

            case R.id.visibleCheckBox:

                break;

            case R.id.goneCheckBox:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
