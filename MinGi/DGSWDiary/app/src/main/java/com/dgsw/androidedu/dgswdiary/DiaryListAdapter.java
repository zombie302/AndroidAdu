package com.dgsw.androidedu.dgswdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-05.
 */

public class DiaryListAdapter extends BaseAdapter {

    private ArrayList<String> dataList = new ArrayList();
    private Context parentActivity = null;

    public DiaryListAdapter(Context activity)
    {
        dataList.add("테스트");
        dataList.add("테스트");
        dataList.add("테스트");
        dataList.add("테스트");
        dataList.add("테스트");

        parentActivity = activity;
    }
    @Override
    public int getCount() {
        return dataList.size();
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
            LayoutInflater layoutInflater = LayoutInflater.from(parentActivity);

            convertView = layoutInflater.inflate(R.layout.listitem, null, false);

        }

        TextView tv = (TextView)convertView.findViewById(R.id.diaryTitle);
        tv.setText(dataList.get(position));

        return convertView;
    }
}
