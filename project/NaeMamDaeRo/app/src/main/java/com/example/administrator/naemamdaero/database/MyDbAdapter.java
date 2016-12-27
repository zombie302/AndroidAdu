package com.example.administrator.naemamdaero.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.administrator.naemamdaero.database.MyDbAdapter.DATA_TYPE.CATEGORY_TYPE;
import static com.example.administrator.naemamdaero.database.MyDbAdapter.DATA_TYPE.DIARY_TYPE;
import static com.example.administrator.naemamdaero.database.MyDbAdapter.SEARCH_TYPE.ALL;

/**
 * Created by user on 2016-12-27.
 */

public class MyDbAdapter extends BaseAdapter {

    private final static String TAG = "MyDbAdapter";

    public enum DATA_TYPE { CATEGORY_TYPE, DIARY_TYPE }
    public enum SEARCH_TYPE { ALL, TITLE, CONTENTS }

    private class ViewHolder
    {
        public TextView text01 = null;
        public TextView text02 = null;
        public CheckBox checkBox = null;

        public ViewHolder()
        {

        }
    }

    private Context context = null;
    private MyDatabase myDatabase = null;

    private DATA_TYPE type = DIARY_TYPE;
    private int layoutRes = 0;
    private int text01ResId = 0;
    private int text02ResId = 0;
    private int checkboxResId = 0;

    private SEARCH_TYPE searchType = ALL;
    private String keyword = null;

    private ArrayList itemDataList = null;
    private boolean checkedList[] = null;
    private boolean bCheckVisible = false;

    public MyDbAdapter(Context context)
    {
        this.context = context;
        myDatabase = MyDatabase.getInstance(context);
        myDatabase.open();
    }

    public void setLayout(int layoutRes, int text01ResId, int text02ResId, int checkboxResId)
    {
        this.layoutRes = layoutRes;
        this.text01ResId = text01ResId;
        this.text02ResId = text02ResId;
        this.checkboxResId = checkboxResId;
    }

    public void loadDiaryData(String CategoryName)
    {
        type = DIARY_TYPE;

        if(CategoryName == null || CategoryName.equals(""))
        {
            itemDataList = myDatabase.searchAll();
        }
        else {
            itemDataList = myDatabase.searchByCategory(CategoryName);
        }

        checkedList = new boolean[itemDataList.size()];

        this.notifyDataSetChanged();
    }

    public void loadCategoryData()
    {
        type = CATEGORY_TYPE;

        itemDataList = myDatabase.getCategory();

        checkedList = new boolean[itemDataList.size()];

        this.notifyDataSetChanged();
    }

    public void search(SEARCH_TYPE searchType, String keyword)
    {
        this.searchType = searchType;
        this.keyword = keyword;

        this.notifyDataSetChanged();
    }

    public void setCheckVisible(boolean value)
    {
        this.bCheckVisible = value;
        checkedList = new boolean[itemDataList.size()];
        notifyDataSetChanged();
    }

    public boolean isCheckMode()
    {
        return this.bCheckVisible;
    }

    public ArrayList<MyData> getCheckedDiaryData()
    {
        ArrayList<MyData> retList = new ArrayList<MyData>();
        ArrayList<MyData> dbList = itemDataList;

        for(int i = 0; i < dbList.size(); i++)
        {
            if(checkedList[i] == true)
            {
                retList.add(dbList.get(i));
            }
        }

        return retList;
    }

    public ArrayList<String> getCheckedCategoryData()
    {
        ArrayList<String> retList = new ArrayList<String>();
        ArrayList<String> dbList = itemDataList;

        for(int i = 0; i < dbList.size(); i++)
        {
            if(checkedList[i] == true)
            {
                retList.add(dbList.get(i));
            }
        }

        return retList;
    }

    @Override
    public int getCount() {
        if(itemDataList == null)
            return 0;

        return itemDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            ViewHolder viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layoutRes, null, false);

            if(this.text01ResId != 0) {
                viewHolder.text01 = (TextView) convertView.findViewById(this.text01ResId);
            }

            if(this.text02ResId != 0) {
                viewHolder.text02 = (TextView) convertView.findViewById(this.text02ResId);
            }

            viewHolder.checkBox = (CheckBox)convertView.findViewById(this.checkboxResId);

            convertView.setTag(viewHolder);
        }

        setItemData(convertView,position);

        return convertView;
    }

    private void setDiaryItemData(View convertView, int position)
    {
        ViewHolder viewHolder = null;

        viewHolder = (ViewHolder)convertView.getTag();

        ArrayList<MyData> diaryDataList = itemDataList;
        viewHolder.text01.setText(diaryDataList.get(position).getTime());
        viewHolder.text02.setText(diaryDataList.get(position).getTitle());

        if(keyword == null || keyword.equals("")) {
            convertView.setVisibility(View.VISIBLE);
            convertView.setLayoutParams(new AbsListView.LayoutParams(-1,-2));
            return;
        }

        switch(searchType)
        {
            case TITLE:
                if(diaryDataList.get(position).getTitle().contains(keyword) == false)
                {
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,1));
                    convertView.setVisibility(View.GONE);
                    break;
                }
                else
                {
                    convertView.setVisibility(View.VISIBLE);
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,-2));
                }
                break;
            case CONTENTS:
                if(diaryDataList.get(position).getContent().contains(keyword) == false) {
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,1));
                    convertView.setVisibility(View.GONE);
                    break;
                }
                else
                {
                    convertView.setVisibility(View.VISIBLE);
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,-2));
                }
                break;
            case ALL:
                if(diaryDataList.get(position).getContent().contains(keyword) == false && diaryDataList.get(position).getTitle().contains(keyword) == false) {
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,1));
                    convertView.setVisibility(View.GONE);
                    break;
                }
                else
                {
                    convertView.setVisibility(View.VISIBLE);
                    convertView.setLayoutParams(new AbsListView.LayoutParams(-1,-2));
                }
                break;
        }
    }

    private void setCategoryItemData(View convertView, int position)
    {
        ViewHolder viewHolder = null;

        viewHolder = (ViewHolder)convertView.getTag();

        ArrayList<String> diaryDataList = itemDataList;
        viewHolder.text01.setText(diaryDataList.get(position));
    }

    private void setItemData(View convertView, int position)
    {
        ViewHolder viewHolder = null;

        viewHolder = (ViewHolder)convertView.getTag();

        if(type == DIARY_TYPE)
        {
            setDiaryItemData(convertView,position);
        }
        else {
            setCategoryItemData(convertView, position);
        }

        viewHolder.checkBox.setChecked(checkedList[position]);

        if(bCheckVisible == true) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }
        else
            viewHolder.checkBox.setVisibility(View.GONE);

        viewHolder.checkBox.setTag(position);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                int position = (int) checkBox.getTag();
                checkedList[position] = checkBox.isChecked();
            }
        });
    }
}
