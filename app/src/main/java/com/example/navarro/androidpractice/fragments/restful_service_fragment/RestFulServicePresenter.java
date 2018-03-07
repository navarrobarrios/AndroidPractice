package com.example.navarro.androidpractice.fragments.restful_service_fragment;

import com.example.navarro.androidpractice.beans.unpersistible.RestResponse;
import com.example.navarro.androidpractice.beans.unpersistible.RestResponse_;
import com.example.navarro.androidpractice.beans.unpersistible.RestUniqueResponse;
import com.example.navarro.androidpractice.beans.unpersistible.RestUniqueResponse_;
import com.example.navarro.androidpractice.beans.unpersistible.Result;
import com.example.navarro.androidpractice.web.core.RestFulServiceCore;
import com.example.navarro.androidpractice.web.managers.RestFulServiceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public class RestFulServicePresenter implements RestFulServiceContract.Presenter {

    //region Variables
    private RestFulServiceContract.View mView;
    //endregion

    //region Constructor
    public RestFulServicePresenter(RestFulServiceContract.View mView) {
        this.mView = mView;
    }
    //endregion

    //region RestFulServiceContract.Presenter Methods
    @Override
    public void getAllCountries() {
        mView.showGettingInformationDialog();
        Call<RestResponse> all= RestFulServiceManager.getInstance().start(RestFulServiceCore.class).getAll();
        all.enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                mView.hideGettingIngotmationDialog();
                RestResponse_ response1 = response.body().getRestResponse();
                if(response1.getResult().size() == 0){
                    mView.showNotResultFounded(response1.getMessages());
                }else{
                    mView.showResultFounded(response1.getResult());
                }
            }

            @Override
            public void onFailure(Call<RestResponse> call, Throwable t) {
                mView.hideGettingIngotmationDialog();
                mView.showUnknowError();
            }
        });
    }

    @Override
    public void getInformationByCode2(String code2) {
        mView.showGettingInformationDialog();
        Call<RestUniqueResponse> infoByAlpha2 = RestFulServiceManager.getInstance().start(RestFulServiceCore.class).getInfoByAlpha2(code2);
        infoByAlpha2.enqueue(new Callback<RestUniqueResponse>() {
            @Override
            public void onResponse(Call<RestUniqueResponse> call, Response<RestUniqueResponse> response) {
                mView.hideGettingIngotmationDialog();
                RestUniqueResponse_ resp = response.body().getRestResponse();
                if(resp.getResult() != null){
                    List<Result> results = new ArrayList<>();
                    results.add(resp.getResult());
                    mView.showResultFounded(results);
                }else{
                    mView.showNotResultFounded(resp.getMessages());
                }
            }

            @Override
            public void onFailure(Call<RestUniqueResponse> call, Throwable t) {
                mView.hideGettingIngotmationDialog();
                mView.showUnknowError();
            }
        });
    }

    @Override
    public void getInformationByCode3(String code3) {
        mView.showGettingInformationDialog();
        Call<RestUniqueResponse> infoByAlpha2 = RestFulServiceManager.getInstance().start(RestFulServiceCore.class).getInfoByAlpha3(code3);
        infoByAlpha2.enqueue(new Callback<RestUniqueResponse>() {
            @Override
            public void onResponse(Call<RestUniqueResponse> call, Response<RestUniqueResponse> response) {
                mView.hideGettingIngotmationDialog();
                RestUniqueResponse_ resp = response.body().getRestResponse();
                if(resp.getResult() != null){
                    List<Result> results = new ArrayList<>();
                    results.add(resp.getResult());
                    mView.showResultFounded(results);
                }else{
                    mView.showNotResultFounded(resp.getMessages());
                }
            }

            @Override
            public void onFailure(Call<RestUniqueResponse> call, Throwable t) {
                mView.hideGettingIngotmationDialog();
                mView.showUnknowError();
            }
        });

    }

    @Override
    public void getInformationByAny(String any) {
        mView.showGettingInformationDialog();
        Call<RestResponse> all= RestFulServiceManager.getInstance().start(RestFulServiceCore.class).searchInfo(any);
        all.enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                mView.hideGettingIngotmationDialog();
                RestResponse_ response1 = response.body().getRestResponse();
                if(response1.getResult().size() == 0){
                    mView.showNotResultFounded(response1.getMessages());
                }else{
                    mView.showResultFounded(response1.getResult());
                }
            }

            @Override
            public void onFailure(Call<RestResponse> call, Throwable t) {
                mView.hideGettingIngotmationDialog();
                mView.showUnknowError();
            }
        });
    }
    //endregion
}
