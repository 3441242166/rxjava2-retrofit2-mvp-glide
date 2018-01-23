package com.example.wanhao.apiapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wanhao on 2017/10/10.
 */

public class SaveDataUtil {
    /**
     * 保存一对键值对到指定的SharedPreferences文件中
     * @param context
     * @param filename
     * @param key
     * @param value
     * @return
     */
    public static boolean saveToSharedPreferences(Context context, String filename, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 从指定的文件中读取指定key的value
     * @param context
     * @param filename
     * @param key
     * @return
     */
    public static String getValueFromSharedPreferences(Context context, String filename, String key){
        String value = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        if (sharedPreferences != null){
            value = sharedPreferences.getString(key, null);
        }
        return value;
    }

    public static void saveImg(String imgName, Bitmap bitmap) {

        File file = new File("/sdcard/", imgName + ".png");

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("", "成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//读取本地图片 参数路径

    private static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(pathString);
        } catch (Exception e) {

        }
        return bitmap;

    }
}