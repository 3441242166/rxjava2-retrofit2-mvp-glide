package com.example.wanhao.apiapp.imodel;

import com.example.wanhao.apiapp.base.IBaseRequestCallBack;
import com.example.wanhao.apiapp.bean.Message;

/**
 * Created by wanhao on 2018/1/21.
 */

public interface IImageCallBack extends IBaseRequestCallBack<Message> {
    void requestSuccess(Message drawable, int pos);
}
