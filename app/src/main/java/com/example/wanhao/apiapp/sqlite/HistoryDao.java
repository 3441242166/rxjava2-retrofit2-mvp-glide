package com.example.wanhao.apiapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.wanhao.apiapp.bean.Message;
import com.example.wanhao.apiapp.config.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanhao on 2017/10/19.
 */

public class HistoryDao {
    private static final String TAG = "HistoryDao";
    private SQLiteDatabase database;
    private DatabaseHelper mMyDBHelper;


    public HistoryDao(Context context) {
        mMyDBHelper = new DatabaseHelper(context);
    }

    public long addMessage(Message message){
        SQLiteDatabase sqLiteDatabase =  mMyDBHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("title",message.getTitle());
        contentValues.put("time",message.getTime());
        contentValues.put("contantUrl",message.getContantUrl());
        contentValues.put("imageUrl",message.getImageUrl());
        contentValues.put("description",message.getDescription());

        long rowid=sqLiteDatabase.insert("HISTORY",null,contentValues);

        sqLiteDatabase.close();
        return rowid;
    }

    public int deleteMessage(String time){
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete("HISTORY","time=?", new String[]{time});
        sqLiteDatabase.close();
        return deleteResult;
    }

    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete("HISTORY","",null);
        sqLiteDatabase.close();
    }

    public List<Message> alertAllMessage(){
        SQLiteDatabase readableDatabase = mMyDBHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery("select * from HISTORY", new String[]{});

        List<Message> list =new ArrayList<Message>();

        while (cursor.moveToNext()) {
            Message myWord = new Message();
            myWord.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            myWord.setTime(cursor.getString(cursor.getColumnIndex("time")));
            myWord.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            myWord.setImageUrl(cursor.getString(cursor.getColumnIndex("imageurl")));
            myWord.setContantUrl(cursor.getString(cursor.getColumnIndex("contanturl")));
            if(TextUtils.isEmpty(myWord.getImageUrl())){
                myWord.setType(Constant.NORMAL_MESSAGE);
            }else{
                myWord.setType(Constant.IMAGE_MESSAGE);
            }

            list.add(myWord);
        }

        cursor.close(); // 记得关闭 corsor
        readableDatabase.close(); // 关闭数据库
        return list;
    }
}
