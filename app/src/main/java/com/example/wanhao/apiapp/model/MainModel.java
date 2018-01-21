package com.example.wanhao.apiapp.model;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.example.wanhao.apiapp.base.IBaseRequestCallBack;
import com.example.wanhao.apiapp.bean.Message;
import com.example.wanhao.apiapp.bean.MessageResponse;
import com.example.wanhao.apiapp.config.ApiConfig;
import com.example.wanhao.apiapp.config.Constant;
import com.example.wanhao.apiapp.helper.RetrofitHelper;
import com.example.wanhao.apiapp.imodel.IImageCallBack;
import com.example.wanhao.apiapp.imodel.IMainModel;
import com.example.wanhao.apiapp.service.DownloadImageService;
import com.example.wanhao.apiapp.service.MainListService;
import com.google.gson.Gson;

import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by wanhao on 2018/1/20.
 */

public class MainModel implements IMainModel{
    private static final String TAG = "MainModel";
    MessageResponse msg;
    int pos;

    public MainModel(){

    }

    @Override
    public void getListData(int num, int isRand, final IBaseRequestCallBack iBaseRequestCallBack) {
        final MainListService service = RetrofitHelper.get().create(MainListService.class);
        iBaseRequestCallBack.beforeRequest();
        service.getSumData(num, ApiConfig.QUEST_KEY,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> response) throws Exception {
                        Gson gson = new Gson();
                        msg = gson.fromJson(response.body().string(),MessageResponse.class);

                        for(int x=0;x<msg.getAr().size();x++) {
                            Log.i(TAG, "accept: "+ msg.getAr().get(x).getImageUrl());
                            if (TextUtils.isEmpty(msg.getAr().get(x).getImageUrl()))
                                msg.getAr().get(x).setType(Constant.NORMAL_MESSAGE);
                            else
                                msg.getAr().get(x).setType(Constant.IMAGE_MESSAGE);
                        }

                        if(msg.getCode().equals(Constant.REQUEST_SUCCESS)){
                            iBaseRequestCallBack.requestSuccess(msg.getAr());
                            iBaseRequestCallBack.requestComplete();
                        }else {
                            iBaseRequestCallBack.requestError(new Throwable("网络错误"));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iBaseRequestCallBack.requestError(throwable);
                        Log.w(TAG, throwable);
                    }
                });
    }

    @Override
    public void getImage(final Message message, final int pos, final IBaseRequestCallBack iBaseRequestCallBack) {
        //获取图片
        DownloadImageService service = RetrofitHelper.get().create(DownloadImageService.class);

            service.downloadPicFromNet(message.getImageUrl())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            InputStream inputStream = responseBody.byteStream();
                            message.setImage(BitmapFactory.decodeStream(inputStream));
                            ((IImageCallBack)iBaseRequestCallBack).requestSuccess(message,pos);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.w(TAG, throwable);
                            ((IImageCallBack)iBaseRequestCallBack).requestSuccess(message,pos);
                        }
                    });
    }
}
