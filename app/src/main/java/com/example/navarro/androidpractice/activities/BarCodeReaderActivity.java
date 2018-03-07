package com.example.navarro.androidpractice.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarCodeReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    //region Variables
    //region Views
    private ZXingScannerView mScannerView;
    public static String BAR_CODE_READER_DATA = "rsult_from_QRScanner";
    public static int BAR_CODE_READER_RESULT = 0x6453;
    //endregion
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {
        Intent data = new Intent();
        data.putExtra(BAR_CODE_READER_DATA, rawResult.getText());
        if (getParent() == null) {
            setResult(Activity.RESULT_OK, data);
        } else {
            getParent().setResult(Activity.RESULT_OK, data);
        }
        this.finish();
    }
}