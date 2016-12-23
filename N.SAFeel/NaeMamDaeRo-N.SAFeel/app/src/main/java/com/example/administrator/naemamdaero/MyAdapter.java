package com.example.administrator.naemamdaero;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by pcx12 on 2016-11-05.
 */

public class MyAdapter extends BaseAdapter {

    Context context = null;

    private  ArrayList<Listitem> listitems = new ArrayList<>();


    class Listitem{

        String text1=null;
        String text2=null;
        boolean bCheek=false;
    }

    public MyAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(MyAdapter  == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            MyAdapter = layoutInflater.inflate(R.layout.listitem, null, false);

        }

        return MyAdapter ;
    }
}
