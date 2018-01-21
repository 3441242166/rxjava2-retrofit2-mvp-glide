package com.example.wanhao.apiapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.wanhao.apiapp.base.BasePresenterImp;
import com.example.wanhao.apiapp.bean.Message;
import com.example.wanhao.apiapp.config.Constant;
import com.example.wanhao.apiapp.imodel.IImageCallBack;
import com.example.wanhao.apiapp.imodel.IMainModel;
import com.example.wanhao.apiapp.imodel.IMessageCallBack;
import com.example.wanhao.apiapp.ipresenter.IMainPresenter;
import com.example.wanhao.apiapp.iview.IMainView;
import com.example.wanhao.apiapp.model.MainModel;

import java.util.List;


/**
 * Created by wanhao on 2018/1/20.
 */

public class MainPresenter extends BasePresenterImp<IMainView,List<Message>> implements IMainPresenter{
    private static final String TAG = "MainPresenter";

    IMainView iMainView;
    IMainModel mainModel;
    int sum = 0;
    Context context;

    public MainPresenter(IMainView view, Context context){
        super(view);
        this.iMainView = view;
        this.context = context;
        mainModel = new MainModel();
    }

    public void getListData(){
        IMessageCallBack callBack = new IMessageCallBack() {
            @Override
            public void beforeRequest() {
                iMainView.showProgress();
            }

            @Override
            public void requestError(Throwable throwable) {
                iMainView.loadDataError(throwable);
            }

            @Override
            public void requestComplete() {
                Log.i(TAG, "requestComplete: ");
                iMainView.disimissProgress();
            }

            @Override
            public void requestSuccess(List<Message> callBack) {
                Log.i(TAG, "requestSuccess: ");
                iMainView.loadDataSuccess(callBack);
            }

        };

        mainModel.getListData(Constant.DEAULT_NUM_PER_PAGE, 0,callBack);
    }

    @Override
    public void getImage(final List<Message> list) {
        IImageCallBack callBack = new IImageCallBack() {

            @Override
            public void beforeRequest() {

            }

            @Override
            public void requestError(Throwable throwable) {

            }

            @Override
            public void requestComplete() {
                iMainView.ImageComplete();
            }

            @Override
            public void requestSuccess(Message callBack) {

            }

            @Override
            public void requestSuccess(Message message,int pos) {
                Log.i(TAG, "requestSuccess: image"+message.getImage());
                list.set(pos,message);
                iMainView.loadDataSuccess(list);
                sum++;
                if( sum == list.size()){
                    requestComplete();
                    sum=0;
                }
            }

        };
        for(int x=0;x<list.size();x++){
            mainModel.getImage(list.get(x),x,callBack);
        }
    }
}
