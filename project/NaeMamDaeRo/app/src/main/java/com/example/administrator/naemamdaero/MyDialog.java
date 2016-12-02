package com.example.administrator.naemamdaero;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by user on 2016-12-03.
 */

public class MyDialog extends Dialog {

    public MyDialog(Context context)
    {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_my_dialog);
    }

}
