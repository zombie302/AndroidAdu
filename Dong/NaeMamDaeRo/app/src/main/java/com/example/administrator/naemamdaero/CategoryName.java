package com.example.administrator.naemamdaero;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016-12-21.
 */

public class CategoryName extends Dialog{

    MainActivity parent = null;
    String a = null;
    private EditText e;
    private Button b;
    Context con;


    private Button.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CategoryName.this.dismiss();
            CategoryName.this.closeListener.onClose(e.getText().toString());
        }
    };

    interface OnCloseListener{
        public void onClose(String a);
    }

    OnCloseListener closeListener = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.category_name);
        e = (EditText) findViewById(R.id.editText);
            if(a!=null){
            e.setText(a);
        }

        b = (Button) findViewById(R.id.okButton);
        b.setOnClickListener(click);
    }

    public CategoryName(Context context, OnCloseListener onCloseListener){
        super(context);
        con=context;
        this.closeListener = onCloseListener;
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                InputMethodManager imm;
                imm = (InputMethodManager)con.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
            }
        });
    }

    public CategoryName(Context context, OnCloseListener onCloseListener, String cn){
        super(context);
        con=context;
        a = cn;
        this.closeListener = onCloseListener;
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                InputMethodManager imm;
                imm = (InputMethodManager)con.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(e.getWindowToken(), 0);

            }
        });
    }

}