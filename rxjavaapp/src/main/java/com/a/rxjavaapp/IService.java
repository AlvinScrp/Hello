package com.a.rxjavaapp;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//定义一个接口类
public interface IService {
    //通过注解定义我们的网络请求方法。返回 retrofit2.call
    @GET("users/{user}")
    Call<UserBean> userInfo(@Path("user") String user);

    //返回 RxJava  Single
    @GET("users/{user}")
    Single<UserBean> userInfoRx(@Path("user") String user);
}
