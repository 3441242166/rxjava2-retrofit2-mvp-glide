package com.example.wanhao.apiapp.service;

import com.example.wanhao.apiapp.config.ApiConstant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wanhao on 2018/1/20.
 */

public interface MainListService {

    @FormUrlEncoded
    @POST("social")
    Observable<Response<ResponseBody>> getSumData(@Field(ApiConstant.KEY) String key,       //url ApiConstant.TEL_NUM = telNum
                                             @Field(ApiConstant.NUM) int num);

    @GET("social")
    Observable<Response<ResponseBody>> getSumData(@Query(ApiConstant.NUM) int sum, @Query(ApiConstant.KEY) String key, @Query(ApiConstant.RAND) int rand);

}
