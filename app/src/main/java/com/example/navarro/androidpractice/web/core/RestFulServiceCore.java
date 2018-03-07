package com.example.navarro.androidpractice.web.core;

import com.example.navarro.androidpractice.beans.unpersistible.RestResponse;
import com.example.navarro.androidpractice.beans.unpersistible.RestUniqueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public interface RestFulServiceCore {

    @GET("get/all")
    Call<RestResponse> getAll();

    @GET("get/iso2code/{param}")
    Call<RestUniqueResponse> getInfoByAlpha2(@Path("param") String alpha2_code);

    @GET("get/iso3code/{param}")
    Call<RestUniqueResponse> getInfoByAlpha3(@Path("param") String alpha3_code);

    @GET("search")
    Call<RestResponse> searchInfo(@Query("text") String text);
}
