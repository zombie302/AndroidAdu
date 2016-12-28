package com.example.administrator.naemamdaero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

public class CategoryDiaryListActivity extends AppCompatActivity {

    private ListView m_ListView;
    private MyAdapter m_Adapter;
    Menu menu = null;
    String category;
    String cName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_diary_list);
        Intent intent = getIntent();
        cName = intent.getStringExtra("category");

        setTitle(cName);

        String[] str = getResources().getStringArray(R.array.Myarray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, str);
        Spinner spi = (Spinner)findViewById(R.id.searchSpinner);
        spi.setAdapter(adapter);

        m_Adapter = new MyAdapter(this);
        m_ListView = (ListView)findViewById(R.id.listview);
        m_ListView.setAdapter(m_Adapter);

        MyDatabase db = MyDatabase.getInstance(this);
        db.open();
        //db.insert("히어로즈 오브 더 스톰", "꿀잼", cName);
        ArrayList<MyData> arrayList = db.searchByCategory(cName);
        m_Adapter.setData(arrayList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id;
        id = item.getItemId();
        if(id == R.id.diaryRemove){
            m_Adapter.bCheack(true);
            m_ListView.invalidateViews();
            menu.clear();
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_remove, menu);
        }
        if(id == R.id.remove_end){
            m_Adapter.bCheack(false);
            m_ListView.invalidateViews();
            menu.clear();
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
        }
        if(id == R.id.remove_check){
            MyDatabase db = MyDatabase.getInstance(this);
            ArrayList<MyData> arrayList = db.searchByCategory(cName);
            remove(arrayList,db);
        }
        if(id == R.id.diaryMod){
            ChangeCategory();
        }
        if(id == R.id.diaryAdd){
            Intent i = new Intent(this, CategoryAddDiaryListActivity.class);
            i.putExtra("category", cName);
            startActivity(i);

        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){

        this.menu = menu;

        // 메뉴에 아이템추가
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }
    public void remove(ArrayList<MyData> arrayList, MyDatabase db){
        ArrayList<MyAdapter.Item> itemList = m_Adapter.getBcheackedItemList();
        for(int i = 0; i<itemList.size(); i++){
            db.update(itemList.get(i).id, itemList.get(i).title, itemList.get(i).contents, "");
            m_Adapter.removeItemById(itemList.get(i).id);
        }
        m_Adapter.notifyDataSetChanged();
    }

    public void onSearchClicked(View view)
    {
        loadData();
    }

    private void loadData()
    {
        final Spinner spinner1 = (Spinner)findViewById(R.id.searchSpinner);
        final EditText editText = (EditText)findViewById(R.id.searchEditText);
        String selType = spinner1.getSelectedItem().toString();
        String keyword = editText.getText().toString();

        MyDatabase db = MyDatabase.getInstance(this);
        db.open();

        ArrayList<MyData> dbList = db.searchByCategory(cName);
        ArrayList<MyData> tempList = new ArrayList<MyData>();

        for(int i = 0; i < dbList.size(); i++)
        {
            MyData myData = dbList.get(i);
            if(selType.equals("제목") && myData.getTitle().contains(keyword) == true)
            {
                tempList.add(myData);
            }
            else if(selType.equals("내용") && myData.getContent().contains(keyword) == true)
            {
                tempList.add(myData);
            }
            else if(selType.equals("제목+내용") && (myData.getContent().contains(keyword) == true || myData.getTitle().contains(keyword) == true))
            {
                tempList.add(myData);
            }
        }

        m_Adapter.setData(tempList);
        m_Adapter.notifyDataSetChanged();
    }
    private void ChangeCategory(){
        final MyDatabase db = MyDatabase.getInstance(this);
        ArrayList<MyData> dbList = db.searchByCategory(cName);
        category = cName;
        CategoryName categoryName = new CategoryName(CategoryDiaryListActivity.this, new CategoryName.OnCloseListener() {
            @Override
            public void onClose(String a) {
                db.updateCategory(category, a);
                setTitle(a);
                cName = a;
                ArrayList<MyData> arrayList = db.searchByCategory(cName);
                m_Adapter.setData(arrayList);
                m_Adapter.notifyDataSetChanged();
            }
        }, category);
        categoryName.show();


    }
}
