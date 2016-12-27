package com.example.administrator.naemamdaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-23.
 */

public class customBaseAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private ArrayList<String> infoList = null;
    //    private ViewHolder viewHolder = null;
    private Context mContext = null;

    public customBaseAdapter(Context m, ArrayList<String> s){
        this.mContext = m;
        this.inflater = LayoutInflater.from(m);
        this.infoList = s;
    }

    @Override
    public int getCount() {
        return infoList.size();
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
        View v = convertView;

        if(v == null){
            v = inflater.inflate(R.layout.listitem, null);
        }

        TextView tv = (TextView)v.findViewById(R.id.textView);
        tv.setText(infoList.get(position));

        return v;
    }
}

