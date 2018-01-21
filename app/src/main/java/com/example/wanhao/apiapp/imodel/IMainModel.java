package com.example.wanhao.apiapp.imodel;

import com.example.wanhao.apiapp.base.IBaseRequestCallBack;
import com.example.wanhao.apiapp.bean.Message;

/**
 * Created by wanhao on 2018/1/20.
 */

public interface IMainModel {

    void getListData(int num,int isRand,IBaseRequestCallBack iBaseRequestCallBack);

    void getImage(Message message,int pos,IBaseRequestCallBack iBaseRequestCallBack);
}
