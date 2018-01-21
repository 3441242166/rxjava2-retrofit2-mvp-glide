package com.example.wanhao.apiapp.bean;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wanhao on 2018/1/20.
 */

public class Message {
    @SerializedName("title")
    String title;
    @SerializedName("ctime")
    String time;
    @SerializedName("description")
    String description;
    @SerializedName("picUrl")
    String imageUrl;
    @SerializedName("url")
    String contantUrl;

    Bitmap image;

    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContantUrl() {
        return contantUrl;
    }

    public void setContantUrl(String contantUrl) {
        this.contantUrl = contantUrl;
    }
}
