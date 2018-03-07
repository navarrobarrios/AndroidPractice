package com.example.navarro.androidpractice.fragments.restful_service_fragment;


import com.example.navarro.androidpractice.beans.unpersistible.Result;

import java.util.List;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public interface RestFulServiceContract {
    interface View{

        void showUnknowError();

        void showGettingInformationDialog();

        void hideGettingIngotmationDialog();

        void showResultFounded(List<Result> resultList);

        void showNotResultFounded(List<String> messages);
    }

    interface Presenter{
        void getAllCountries();

        void getInformationByCode2(String code2);

        void getInformationByCode3(String code3);

        void getInformationByAny(String any);
    }
}
