package com.example.wanhao.apiapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wanhao on 2017/8/9.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, "mySQLite.db", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //保存历史记录
        db.execSQL("create table HISTORY (title text primary key, time text,description text,imageUrl text" +
                ",contantUrl text)");
        //保存收藏
        db.execSQL("create table CONNECTION (title text primary key, time text,description text,imageUrl text" +
                ",contantUrl text)");
        //保存主界面上次的界面
        db.execSQL("create table LAST (title text primary key, time text,description text,imageUrl text" +
                ",contantUrl text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //用户生词库
//        if(newVersion == 8) {
//            db.execSQL("drop table TIMETASK");
//            db.execSQL("create table TIMETASK (datetime text primary key, image text,title INTEGER,time text)");
//        }
    }
}
