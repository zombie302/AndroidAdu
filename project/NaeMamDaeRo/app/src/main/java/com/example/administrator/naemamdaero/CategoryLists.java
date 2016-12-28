package com.example.administrator.naemamdaero;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.naemamdaero.database.MyDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-23.
 */

public class CategoryLists extends Dialog{
    ListView ls = null;
    TextView tv = null;
    ArrayList<String> adapterList = new ArrayList<String>();
    Context con;
    String title;
    String returner;
    CategoryName.OnCloseListener closeListener;

    public CategoryLists(Context context, CategoryName.OnCloseListener onCloseListener, String title) {
        super(context);
        this.title = title;
        con = context;
        this.closeListener = onCloseListener;
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                CategoryLists.this.closeListener.onClose(returner);
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.category_lists);
        tv = (TextView)findViewById(R.id.textView);
        tv.setText(title);

        MyDatabase myDatabase = MyDatabase.getInstance(con);
        myDatabase.open();

        ArrayList<String> categoryList = myDatabase.getCategory();

        for(String category : categoryList)
        {
            adapterList.add(category);
        }
        ls = (ListView)findViewById(R.id.list);
        ls.setAdapter(new customBaseAdapter(con, adapterList));

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returner = adapterList.get(position);
                CategoryLists.this.dismiss();
            }
        });
    }

}
