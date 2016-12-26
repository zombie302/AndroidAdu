package com.example.administrator.naemamdaero.database;

import android.util.Log;

/**
 * Created by user on 2016-12-23.
 */

public class MyData {
    private long id;
    private String title;
    private String time;
    private String content;
    private String category;

    public MyData(long id, String title, String time, String content, String category)
    {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void print()
    {
        Log.i("MyData","_id : "+id);
        Log.i("MyData","title : "+title);
        Log.i("MyData","time : "+time);
        Log.i("MyData","content : "+content);
        Log.i("MyData","category : "+category);
    }
}
