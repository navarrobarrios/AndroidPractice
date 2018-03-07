package com.example.navarro.androidpractice.web.managers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public class RestFulServiceManager {

    //region Variables
    private static final String BASE_URL = "http://services.groupkt.com/country/";
    private static Retrofit mRetrofit;
    //endregion

    //region getInstance
    public static RestFulServiceManager getInstance(){
        return new RestFulServiceManager();
    }
    //endregion

    //region Constructor
    public RestFulServiceManager() {
        mRetrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL).build();
    }
    //endregion

    //region Operations
    public <S> S start(Class<S> classDef){
        return mRetrofit.create(classDef);
    }
    //endregion
}
