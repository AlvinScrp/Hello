package com.a.rxjavaapp;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IService {
    @GET("/banner/json")
    Observable<BannerResponse> banner();
}
