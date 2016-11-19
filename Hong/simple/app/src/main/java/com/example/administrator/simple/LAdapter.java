package com.example.administrator.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-22.
 */

public class LAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;

    public LAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    ArrayList<String> data = new ArrayList<String>();

    @Override
    public int getCount() {
        return data.size();
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
        return inflater.inflate(R.layout.listitem, null, false);
    }
}
