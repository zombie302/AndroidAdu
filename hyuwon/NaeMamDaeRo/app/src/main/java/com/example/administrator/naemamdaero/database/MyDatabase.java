package com.example.administrator.naemamdaero.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by user on 2016-12-23.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static MyDatabase self = null;
    private final static String DATABASE_NAME = "DB_DIARY";
    private final static String TB_DIARY = "TB_DIARY";
    private final static int DATABASE_VER = 4;

    private SQLiteDatabase sqLiteDatabase = null;

    MyDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public static MyDatabase getInstance(Context context)
    {
        if(self == null)
        {
            self = new MyDatabase(context);
        }

        return self;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("MyDatabase", "onCreate");
        createTable(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("MyDatabase", "onOpen");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("MyDatabase", "onUpgrade");
        createTable(db);
    }

    private void createTable(SQLiteDatabase db)
    {
        Log.i("MyDatabase", "createTable");
        try
        {
            String DROP_SQL = "drop table if exists "+ TB_DIARY;
            db.execSQL(DROP_SQL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            self = null;
            return;
        }

        String CREATE_SQL = "create table " + TB_DIARY + "("
                + "_id integer PRIMARY KEY autoincrement,"
                + "title text,"
                + "date integer,"
                + "content text,"
                + "category text);";

        try
        {
            db.execSQL(CREATE_SQL);
        }
        catch(Exception e)
        {
            self = null;
            e.printStackTrace();
            return;
        }
    }

    public boolean open()
    {
        sqLiteDatabase = getWritableDatabase();

        return true;
    }

    public void printRecord()
    {
        String SQL = "select * from "+ TB_DIARY ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        int count = cursor.getCount();

        Log.i("printRecord","ALL ======================");
        for(int i = 0; i < count; i ++)
        {
            cursor.moveToNext();

            Log.i("printRecord","_id : "+cursor.getLong(0));
            Log.i("printRecord","title : "+cursor.getString(1));

            long time = cursor.getLong(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
            String timeByStringType = dateFormat.format(time);

            Log.i("printRecord","time : "+timeByStringType);
            Log.i("printRecord","content : "+cursor.getString(3));
            Log.i("printRecord","category : "+cursor.getString(4));
        }

        cursor.close();
    }

    public void insert(String title, String content, String category)
    {
        String SQL = "insert into "+ TB_DIARY + " (title,date,content,category) values ('" + title + "','" + System.currentTimeMillis() + "','" + content + "','" + category + "');";
        sqLiteDatabase.execSQL(SQL);
    }

    public void update(long id, String title, String content, String category)
    {
        String SQL = "update "+ TB_DIARY + " set title='"+ title +"',content='"+content+"',category='"+category+"' where _id="+id;
        sqLiteDatabase.execSQL(SQL);
    }

    public MyData read(long id)
    {
        String SQL = "select * from "+ TB_DIARY + " where _id=?";
        String[] args = {""+id};
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, args);

        int count = cursor.getCount();

        if(count > 0)
        {
            cursor.moveToNext();

            long _id = cursor.getLong(0);
            String title = cursor.getString(1);

            long time = cursor.getLong(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
            String timeByStringType = dateFormat.format(time);
            String content = cursor.getString(3);
            String category = cursor.getString(4);
            return new MyData(_id, title, timeByStringType, content, category);
        }

        return null;

    }

    public void search()
    {

    }

}
