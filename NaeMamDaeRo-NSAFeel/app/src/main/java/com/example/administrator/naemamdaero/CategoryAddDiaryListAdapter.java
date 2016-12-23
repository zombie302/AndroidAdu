package com.example.administrator.naemamdaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by pcx12 on 2016-12-03.
 */

public class CategoryAddDiaryListAdapter extends BaseAdapter {

    ArrayList<String> arrayList = new ArrayList<String>();

    public CategoryAddDiaryListAdapter()
    {
        arrayList.add("3");
        arrayList.add("4");
    }

    @Override
    public int getCount() {
        return arrayList.size();
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

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View v;
        v = layoutInflater.inflate(R.layout.listtiem_categoryadddiarylistactivity, null, false);
        v.findViewById()
        return v;

    }

    Context c;

    public void setContext(CategoryAddDiaryListActivity categoryAddDiaryListActivity) {
        c = categoryAddDiaryListActivity;
    }
}
