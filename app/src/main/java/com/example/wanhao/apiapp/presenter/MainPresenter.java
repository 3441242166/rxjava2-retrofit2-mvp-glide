package com.example.wanhao.apiapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.wanhao.apiapp.base.BasePresenterImp;
import com.example.wanhao.apiapp.bean.Message;
import com.example.wanhao.apiapp.config.Constant;
import com.example.wanhao.apiapp.imodel.IImageCallBack;
import com.example.wanhao.apiapp.imodel.IMainModel;
import com.example.wanhao.apiapp.imodel.IMessageCallBack;
import com.example.wanhao.apiapp.iview.IMainView;
import com.example.wanhao.apiapp.model.MainModel;
import com.example.wanhao.apiapp.sqlite.HistoryDao;
import com.example.wanhao.apiapp.sqlite.LastDao;

import java.util.List;


/**
 * Created by wanhao on 2018/1/20.
 */

public class MainPresenter extends BasePresenterImp<IMainView,List<Message>>{
    private static final String TAG = "MainPresenter";

    IMainView iMainView;
    IMainModel mainModel;
    HistoryDao historyDao;
    LastDao lastDao;
    Context context;
    //保存已加载图片数量
    int sum = 0;

    public MainPresenter(IMainView view, Context context){
        super(view);
        this.iMainView = view;
        this.context = context;
        mainModel = new MainModel();
        lastDao = new LastDao(context);
        historyDao = new HistoryDao(context);
    }

    public void getListData(){
        if(lastDao.alertAllMessage().size()>0){
            getDataBySD();
        }else{
            getDataByInternet();
        }
    }

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

    private void getDataBySD() {
        List<Message> list = lastDao.alertAllMessage();
        iMainView.loadDataSuccess(list);
        iMainView.disimissProgress();
    }

    public void getDataByInternet(){
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
                savaLast(callBack);
                iMainView.loadDataSuccess(callBack);
            }

        };

        mainModel.getListData(Constant.DEAULT_NUM_PER_PAGE, 0,callBack);
    }
    //保存历史记录
    public void saveHistory(Message message) {
        historyDao.addMessage(message);
    }
    //保存列表
    public void savaLast(List<Message> list){
        lastDao.deleteAll();
        for(int x=0;x<list.size();x++)
            lastDao.addMessage(list.get(x));
    }


    public void getBannerData() {
        final IImageCallBack callBacks = new IImageCallBack() {
            @Override
            public void requestSuccess(Message drawable, int pos) {

            }

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
            }

            @Override
            public void requestSuccess(Message callBack) {

            }

        };

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
            }

            @Override
            public void requestSuccess(final List<Message> callBack) {
                Log.i(TAG, "requestSuccess: ");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for(int x=0;x<callBack.size();x++){
//                            mainModel.getImage(callBack.get(x),x,callBacks);
//                            try {
//                                callBack.get(x).setImage(Glide.with(context)
//                                        .load(callBack.get(x).getImageUrl())
//                                        .asBitmap() //必须
//                                        .centerCrop()
//                                        .into(1000, 500)
//                                        .get());
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        iMainView.bannerComplete(callBack);
//                    }
//                }).start();
                iMainView.bannerComplete(callBack);
            }

        };

        mainModel.getListData(Constant.DEAULT_NUM_BANNER, 0,callBack);
    }
}



