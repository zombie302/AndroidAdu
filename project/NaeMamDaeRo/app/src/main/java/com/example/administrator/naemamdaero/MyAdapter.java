package com.example.administrator.naemamdaero;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.naemamdaero.database.MyData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-19.
 */

public class MyAdapter extends BaseAdapter {

    private ArrayList<Item> m_List;
    Context context;
    boolean hideAndSeek = false;
    public MyAdapter(Context context){
        m_List = new ArrayList<Item>();
        this.context = context;
    }

    class Item
    {
        String date;
        String title;
        boolean bCheck;
        long id;
        String contents;
        public Item(String date, String title, long id, String contents,boolean bCheck){
            this.date = date;
            this.title = title;
            this.id = id;
            this.contents = contents;
            this.bCheck = bCheck;
        }
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            //뷰생성
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            convertView = layoutInflater.inflate(R.layout.list_item, null, false);
        }

        TextView date = (TextView)convertView.findViewById(R.id.labelDate);
        TextView title = (TextView)convertView.findViewById(R.id.lableTitle);
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);


        if(hideAndSeek == false) {
            Log.i("","hideAndSeek F : position = " + position);
            checkBox.setVisibility(View.GONE);
        }
        else {
            Log.i("","hideAndSeek T : position = " + position);
            checkBox.setVisibility(View.VISIBLE);
        }


        date.setText(m_List.get(position).date);
        title.setText(m_List.get(position).title);
        checkBox.setChecked(m_List.get(position).bCheck);

        Item tempItem = m_List.get(position);
        checkBox.setTag(tempItem);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox tempCheckBox = (CheckBox)v;
                //tempCheckBox.setChecked(!tempCheckBox.isChecked());

                Item tempItem;
                tempItem = (Item)tempCheckBox.getTag();
                tempItem.bCheck = tempCheckBox.isChecked();

            }
        });

        return convertView;
    }
/*
    public void add(String date, String title, boolean cheak){
        Item item = new Item(date, title, cheak);
        m_List.add(item);
    }
*/
    public void remove(ArrayList<MyData> data){
        for(int i = 0; i < data.size(); i++) {
            MyData myData = data.get(i);

            removeItemById(myData.getId());

        }
    }

    public void removeItemById(long id)
    {
        for(int i = 0; i < m_List.size(); i++)
        {
            Item item = m_List.get(i);

            if(item.id == id)
            {
                m_List.remove(i);
                break;
            }
        }
    }

    public void bCheack (boolean hideAndSeek){
        this.hideAndSeek = hideAndSeek;
    }

    public void setData(ArrayList<MyData> data){
        m_List.clear();

        for(int i = 0; i<data.size(); i++) {
            Item item = new Item(data.get(i).getTime(), data.get(i).getTitle(),data.get(i).getId(), data.get(i).getContent(), false);
            m_List.add(item);
        }
    }
    public boolean getBcheack(int num){
        return m_List.get(num).bCheck;
    }

    public ArrayList<Item> getBcheackedItemList(){
        ArrayList<Item> checkedList = new ArrayList<Item>();

        for(int i = 0; i < m_List.size(); i ++)
        {
            if(m_List.get(i).bCheck == true)
            {
                checkedList.add(m_List.get(i));
            }
        }
        return checkedList;
    }
}

