package com.example.wanhao.apiapp.iview;

import com.example.wanhao.apiapp.base.IBaseView;
import com.example.wanhao.apiapp.bean.Message;

import java.util.List;

/**
 * Created by wanhao on 2018/1/20.
 */

public interface IMainView extends IBaseView<List<Message>> {
    //图片加载完毕调用
    void ImageComplete();

}
