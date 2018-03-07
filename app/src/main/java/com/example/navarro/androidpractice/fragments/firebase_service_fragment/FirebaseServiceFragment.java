package com.example.navarro.androidpractice.fragments.firebase_service_fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.anavrrropc.practicejanb.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler;

public class FirebaseServiceFragment extends Fragment{

    //region Variables
    //region Views
    private Button mScanCode;
    private EditText mResult;
    //endregion
    //endregion

    //region Constructor
    public FirebaseServiceFragment() {
        // Required empty public constructor
    }
    //endregion

    //region newInstance
    public static FirebaseServiceFragment newInstance() {
        return new FirebaseServiceFragment();
    }
    //endregion

    //region Fragment Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firebase, container, false);
        mResult = view.findViewById(R.id.fragment_firebase_result);
        mScanCode = view.findViewById(R.id.fragment_firebase_btn);
        mScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.title_firebase);
    }

    //endregion

    //region Local Methods
    //endregion
}
