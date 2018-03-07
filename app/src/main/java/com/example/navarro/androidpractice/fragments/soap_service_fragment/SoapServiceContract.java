package com.example.navarro.androidpractice.fragments.soap_service_fragment;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public interface SoapServiceContract {

    interface View{
        void showConvertingDialog();

        void hideConvertingDialog();

        void showError();

        void showResult(String result);
    }

    interface Presenter{
        void convertGrades(String grades, String from, String to);
    }
}
