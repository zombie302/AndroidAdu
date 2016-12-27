package com.example.administrator.naemamdaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-05.
 */
public class Adapter extends BaseAdapter {

    ArrayList<Item> itemList = new ArrayList<Item>();
    Context c = null;

    class Item{
        String cName;
        boolean checkBox;
        //ViewHolder viewHolder;

        public Item(String s, boolean c){
            cName = s;
            checkBox = c;

        }
    }

    public Adapter(Context c)
    {
        this.c = c;
    }

    public void addData(String str1)
    {
        Item item = new Item(str1, false);

        itemList.add(item);
    }
    public void removeData(Item item){
        for(Item i : itemList)
        {
            if(i.cName.equals(item.cName))
            {
                itemList.remove(i);
            }
        }
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    boolean bCheckMode = false;

    public void setCheckMode(boolean bCheckMode){
        this.bCheckMode = bCheckMode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(c);

            convertView = layoutInflater.inflate(R.layout.categorylist_layout, null, false);
        }

        TextView tv1 = (TextView)convertView.findViewById(R.id.category_name);
        tv1.setText(itemList.get(position).cName);
        CheckBox b = (CheckBox)convertView.findViewById(R.id.checkbox);
        b.setChecked(itemList.get(position).checkBox);
        b.setTag(itemList.get(position));

        CompoundButton.OnCheckedChangeListener a = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Item item = (Item)buttonView.getTag();
                item.checkBox = isChecked;
            }
        };

        b.setOnCheckedChangeListener(a);

        if(bCheckMode == false){
            b.setVisibility(View.GONE);}
        else if(bCheckMode == true){
            b.setVisibility(View.VISIBLE);}
        return convertView;
    }

    public ArrayList<Item> getCheckedItem()
    {
        ArrayList<Item> checkedList = new ArrayList<Item>();

        for(int i= 0 ; i <itemList.size(); i++) {
            Item item = itemList.get(i);

            if(item.checkBox == true)
            {
                Item checkItem = new Item(item.cName, item.checkBox);
                checkedList.add(checkItem);
            }
        }

        return checkedList;
    }

}