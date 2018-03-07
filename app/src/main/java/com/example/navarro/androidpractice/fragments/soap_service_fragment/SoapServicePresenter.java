package com.example.navarro.androidpractice.fragments.soap_service_fragment;

import android.content.Context;
import android.os.AsyncTask;

import com.example.navarro.androidpractice.web.managers.SoapServiceManager;


/**
 * Created by ${Navarro} on 9/14/17.
 */

public class SoapServicePresenter implements SoapServiceContract.Presenter {

    //region Variables
    private SoapServiceContract.View mView;
    private Context mContext;
    //endregion

    //region Constructor
    public SoapServicePresenter(SoapServiceContract.View view, Context context) {
        this.mView = view;
        this.mContext = context;
    }
    //endregion


    @Override
    public void convertGrades(final String grades, final String from, final String to) {
        mView.showConvertingDialog();
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String... strings) {
                return SoapServiceManager.getInstance().convertGrades(grades, from, to);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                mView.hideConvertingDialog();
                if(s != null){
                    mView.showResult(s);
                }else{
                    mView.showError();
                }
            }
        }.execute();

    }
}
