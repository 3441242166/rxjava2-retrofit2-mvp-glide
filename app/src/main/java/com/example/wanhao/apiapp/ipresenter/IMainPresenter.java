package com.example.wanhao.apiapp.ipresenter;

import com.example.wanhao.apiapp.bean.Message;

import java.util.List;

/**
 * Created by wanhao on 2018/1/20.
 */

public interface IMainPresenter {
    void getListData();
    void getImage(List<Message> list);
}

