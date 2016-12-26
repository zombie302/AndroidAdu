package com.example.administrator.naemamdaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.naemamdaero.database.MyData;
import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-02.
 */

class chListViewItem{
    private  String con;
    private String arr1;
    private String arr2;
    private long id;
    boolean check;


    public void setCheck(boolean cheak){this.check = cheak;}

    public void setarr1(String string){
        arr1 = string;
    }

    public void setCon(String con){this.con = con;}

    public void setarr2(String string){
        arr2 = string;
    }

    public void setId(long string){
        id = string;
    }

    public boolean getCheck(){return check;}

    public String getArr1(){
        return arr1;
    }

    public String getCon() {return con; }

    public String getArr2(){
        return arr2;
    }

}

class sideListViewItem{
    private String arr1;
    private long id;


    public void setArr1(String string){
        arr1 = string;
    }

    public String getarr1(){
        return arr1;
    }

}


class cheakAdapter extends BaseAdapter {
    public ArrayList<chListViewItem> list = new ArrayList<chListViewItem>();


    public boolean isCheckMode = false;

    public void update(MyDatabase MDB) {
        list.clear();

        ArrayList<MyData> array = MDB.searchAll();

        for (int i = 0; i < array.size(); i++) {
            this.addItem(array.get(i).getId(), array.get(i).getTitle(), array.get(i).getContent(), array.get(i).getTime());
        }
    }

    public void updateTFilter(MyDatabase MDB,String key) {
        list.clear();

        ArrayList<MyData> array = MDB.searchAll();
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getTitle().contains(key) == true)
                this.addItem(array.get(i).getId(), array.get(i).getTitle(), array.get(i).getContent(), array.get(i).getTime());
        }
    }

    public void updateCFilter(MyDatabase MDB,String key) {
        list.clear();

        ArrayList<MyData> array = MDB.searchAll();
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getContent().contains(key) == true)
                this.addItem(array.get(i).getId(), array.get(i).getTitle(), array.get(i).getContent(), array.get(i).getTime());
        }
    }

    public void updateTCFilter(MyDatabase MDB,String key) {
        list.clear();

        ArrayList<MyData> array = MDB.searchAll();
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getContent().contains(key) == true || array.get(i).getTitle().contains(key) == true)
                this.addItem(array.get(i).getId(), array.get(i).getTitle(), array.get(i).getContent(), array.get(i).getTime());
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adaptercb, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView) ;
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textView2) ;
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

        if(isCheckMode == true) {
            checkBox.setVisibility(View.VISIBLE);
        }
        else
            checkBox.setVisibility(View.GONE);
        chListViewItem listViewItem = list.get(position);


        titleTextView.setText(listViewItem.getArr1());
        dateTextView.setText(listViewItem.getArr2());

        if(listViewItem.check == true) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
        return convertView;
    }

    public void addItem(long id, String title,String con, String date) {
        chListViewItem item = new chListViewItem();

        item.setId(id);
        item.setarr1(title);

        item.setarr2(date);

        list.add(item);
    }

    public void CListUp(ArrayList<MyData> arr){
        list.clear();
        for (int i=0;i<arr.size();i++)
            this.addItem(arr.get(i).getId(),arr.get(i).getTitle(),arr.get(i).getContent(),arr.get(i).getTime());
    }

    public void tFilter(MyDatabase MDB ,String key){
        updateTFilter(MDB, key);
    }

    public void cFilter(MyDatabase MDB, String key){
        updateCFilter(MDB, key);
    }

    public void tcFilter(MyDatabase MDB, String key){
        updateTCFilter(MDB, key);
    }
}

class sideAdapter extends BaseAdapter {
    public ArrayList<sideListViewItem> list = new ArrayList<sideListViewItem>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_adapter, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.sidetextView1) ;

        sideListViewItem listViewItem = list.get(position);

        titleTextView.setText(listViewItem.getarr1());

        return convertView;
    }

    public void addItem( String title) {
        sideListViewItem item = new sideListViewItem();

        item.setArr1(title);

        list.add(item);
    }

    public void update(MyDatabase MDB){
        list.clear();

        ArrayList<String> array = MDB.getCategory();

        for (int i=0;i<array.size();i++){
            this.addItem(array.get(i));
        }
    }

    public ArrayList<MyData> CG(MyDatabase MDB,int position){
        ArrayList<MyData> array =  MDB.searchByCategory(this.list.get(position).getarr1());

        return array;
    }




}

