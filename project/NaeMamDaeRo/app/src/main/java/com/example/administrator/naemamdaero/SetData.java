package com.example.administrator.naemamdaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-11-05.
 */
public class SetData extends LinearLayout{

    protected void checkBoxClicked(){
        //findViewById(R.id.deleteButton).setOnClickListener(new Button.OnClickListener(){public void onClick(View v){printChecked(v)}});
    }

    private TextView cName;
    private Boolean checkBox;

    public SetData(Context context, CustomList cl) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.categorylist_layout, this, true);

        cName = (TextView) findViewById(R.id.category_name);

    }



}
