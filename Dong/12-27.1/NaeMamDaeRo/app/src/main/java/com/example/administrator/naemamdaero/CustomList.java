package com.example.administrator.naemamdaero;

/**
 * Created by Administrator on 2016-11-05.
 */
public class CustomList {
    private String[] cName;
    private boolean checkBox = false;

    public CustomList (String[] s, boolean c){
        cName = s;
        checkBox = c;
    }

    public boolean isCheckBox(){
        return checkBox;
    }

    public void setCheckBox(boolean check){
        checkBox = check;
    }

}
