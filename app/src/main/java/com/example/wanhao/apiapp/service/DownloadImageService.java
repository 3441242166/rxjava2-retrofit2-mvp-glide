package com.example.wanhao.apiapp.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wanhao on 2018/1/21.
 */

public interface DownloadImageService {
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);
}
