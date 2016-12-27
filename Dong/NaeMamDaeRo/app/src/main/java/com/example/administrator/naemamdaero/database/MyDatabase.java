package com.example.administrator.naemamdaero.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by user on 2016-12-23.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static MyDatabase self = null;
    private final static String DATABASE_NAME = "DB_DIARY";
    private final static String TB_DIARY = "TB_DIARY";
    private final static String TB_CATEGORY = "TB_CATEGORY";
    private final static int DATABASE_VER = 5;

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

        try
        {
            String DROP_SQL = "drop table if exists "+ TB_CATEGORY;
            db.execSQL(DROP_SQL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            self = null;
            return;
        }

        CREATE_SQL = "create table " + TB_CATEGORY + "("
                + "_id integer PRIMARY KEY autoincrement,"
                + "name text);";

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

        String SQL = "insert into "+ TB_CATEGORY + " (name) values ('공부');";
        db.execSQL(SQL);

        SQL = "insert into "+ TB_CATEGORY + " (name) values ('기타');";
        db.execSQL(SQL);
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

    public void delete(long id)
    {
        String SQL = "delete from"+ TB_DIARY +  " where _id="+id;
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

    public ArrayList<MyData> searchAll()
    {
        ArrayList<MyData> dataList = new ArrayList<MyData>();

        String SQL = "select * from "+ TB_DIARY ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        int count = cursor.getCount();

        Log.i("searchAll","ALL ======================");
        for(int i = 0; i < count; i ++)
        {
            cursor.moveToNext();

            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            long time = cursor.getLong(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
            String timeByStringType = dateFormat.format(time);
            String content = cursor.getString(3);
            String category = cursor.getString(4);
            MyData myData = new MyData(id, title, timeByStringType, content, category);
            dataList.add(myData);
        }

        cursor.close();

        return dataList;
    }

    public ArrayList<MyData> searchWithoutCategory(String categoryName)
    {
        ArrayList<MyData> dataList = new ArrayList<MyData>();

        String SQL = "select * from "+ TB_DIARY  + " where category!='"+categoryName+"';";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        int count = cursor.getCount();

        Log.i("searchWithoutCategory","ALL ======================");
        for(int i = 0; i < count; i ++)
        {
            cursor.moveToNext();

            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            long time = cursor.getLong(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
            String timeByStringType = dateFormat.format(time);
            String content = cursor.getString(3);
            String category = cursor.getString(4);
            MyData myData = new MyData(id, title, timeByStringType, content, category);
            dataList.add(myData);
        }

        cursor.close();

        return dataList;
    }

    public ArrayList<MyData> searchByCategory(String categoryName)
    {
        ArrayList<MyData> dataList = new ArrayList<MyData>();

        String SQL = "select * from "+ TB_DIARY  + " where category='"+categoryName+"';";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        int count = cursor.getCount();

        Log.i("searchByCategory","ALL ======================");
        for(int i = 0; i < count; i ++)
        {
            cursor.moveToNext();

            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            long time = cursor.getLong(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
            String timeByStringType = dateFormat.format(time);
            String content = cursor.getString(3);
            String category = cursor.getString(4);
            MyData myData = new MyData(id, title, timeByStringType, content, category);
            dataList.add(myData);
        }

        cursor.close();

        return dataList;
    }

    public ArrayList<String> getCategory()
    {
        ArrayList<String> categoryList = new ArrayList<String>();

        String SQL = "select * from "+ TB_CATEGORY +";";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        int count = cursor.getCount();

        Log.i("getCategory","ALL ======================");
        for(int i = 0; i < count; i ++)
        {
            cursor.moveToNext();
            String name = cursor.getString(1);
            categoryList.add(name);
        }

        cursor.close();

        return categoryList;
    }

    public boolean addCategory(String name)
    {
        String SQL = "insert into "+ TB_CATEGORY + " (name) values ('" + name + "');";
        sqLiteDatabase.execSQL(SQL);

        return true;
    }

    public boolean updateCategory(String oldName, String newName)
    {
        String SQL = "update "+ TB_CATEGORY + " set name='"+ newName +"' where name='"+oldName+"'";
        sqLiteDatabase.execSQL(SQL);

        ArrayList<MyData> diaryList = searchByCategory(oldName);

        for(MyData data : diaryList)
        {
            update(data.getId(),data.getTitle(),data.getContent(),newName);
        }

        return true;
    }

    public boolean deleteCategory(String name)
    {
        String SQL = "delete from "+ TB_CATEGORY + " where name='"+name+"'";
        sqLiteDatabase.execSQL(SQL);

        ArrayList<MyData> diaryList = searchByCategory(name);

        for(MyData data : diaryList)
        {
            update(data.getId(),data.getTitle(),data.getContent(),"");
        }
        return true;
    }

}
