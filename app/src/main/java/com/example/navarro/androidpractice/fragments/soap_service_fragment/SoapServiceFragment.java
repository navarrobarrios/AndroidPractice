package com.example.navarro.androidpractice.fragments.soap_service_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.navarro.androidpractice.R;
import com.example.navarro.androidpractice.web.managers.SoapServiceManager;

public class SoapServiceFragment extends Fragment implements SoapServiceContract.View{

    //region Variables
    private LinearLayout mErrorContainer, mDataResultContainer;
    private MaterialDialog mConvertingDialog;
    private TextView mFrom, mTo, mFromText, mToText;
    private Spinner mFromSpinner, mToSpinner;
    private EditText mGrades;
    private Button mConvert;
    private SoapServiceContract.Presenter mPresenter;
    //endregion

    //region Constructor
    public SoapServiceFragment() {
    }
    //endregion

    //region newInstance
    public static SoapServiceFragment newInstance() {
        return new SoapServiceFragment();
    }
    //endregion

    //region Activity Methods

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SoapServicePresenter(SoapServiceFragment.this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soap, container, false);
        //region ViewsInit
        mDataResultContainer = view.findViewById(R.id.fragment_soap_result_container);
        mErrorContainer = view.findViewById(R.id.fragment_soap_error_container);
        mFromText = view.findViewById(R.id.fragment_soap_from_result_text);
        mFromSpinner = view.findViewById(R.id.fragment_soap_from_spinner);
        mToText = view.findViewById(R.id.fragment_soap_to_result_text);
        mToSpinner = view.findViewById(R.id.fragment_soap_to_spinner);
        mConvert = view.findViewById(R.id.fragment_soap_convert_btn);
        mFrom = view.findViewById(R.id.fragment_soap_from_result);
        mGrades = view.findViewById(R.id.fragment_soap_grades);
        mTo = view.findViewById(R.id.fragment_soap_to_result);
        //endregion
        loadMainInformation();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.title_soap);
    }
    //endregion

    //region Local Methods
    private void loadMainInformation(){
        mFromSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.grades_types_array, android.R.layout.simple_spinner_dropdown_item));
        mToSpinner.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.grades_types_array, android.R.layout.simple_spinner_dropdown_item));

        mErrorContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToConvert();
            }
        });

        mConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToConvert();
            }
        });

    }

    private String getUnitSelected(int selection){
        switch (selection){
            case 0:
                return SoapServiceManager.UNIT_CELSIUS;
            case 1:
                return SoapServiceManager.UNIT_FAHRENHEIT;
            case 2:
                return SoapServiceManager.UNIT_RANKINE;
            case 3:
                return SoapServiceManager.UNIT_REAUMUR;
            case 4:
                return SoapServiceManager.UNIT_KELVIN;
            default:
                return null;
        }
    }

    private void sendDataToConvert(){
        if(validateField()){
            mPresenter.convertGrades(mGrades.getText().toString(),
                    getUnitSelected(mFromSpinner.getSelectedItemPosition()),
                    getUnitSelected(mToSpinner.getSelectedItemPosition()));

        }
    }

    private boolean validateField() {
        boolean isValid = true;
        if(mGrades.getText().toString().isEmpty()){
            isValid = false;
            mGrades.setError("*Required field");
        }else {
            try{
                Double.parseDouble(mGrades.getText().toString());
            }catch (NumberFormatException e){
                isValid = false;
                mGrades.setError("Invalid number");
            }
        }
        return isValid;
    }


    //endregion

    //region SoapServiceContract.View Methods
    @Override
    public void showConvertingDialog() {
        mConvertingDialog = new MaterialDialog.Builder(getActivity())
                .content("Converting grades, please wait...")
                .progressIndeterminateStyle(false)
                .progress(true, 0)
                .cancelable(false)
                .autoDismiss(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    @Override
    public void hideConvertingDialog() {
        if(mConvertingDialog != null && mConvertingDialog.isShowing()){
            mConvertingDialog.dismiss();
        }
    }

    @Override
    public void showError() {
        mDataResultContainer.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(String result) {
        mErrorContainer.setVisibility(View.GONE);
        mDataResultContainer.setVisibility(View.VISIBLE);
        mFromText.setText(mFromSpinner.getItemAtPosition(mFromSpinner.getSelectedItemPosition()).toString());
        mToText.setText(mToSpinner.getItemAtPosition(mToSpinner.getSelectedItemPosition()).toString());
        mFrom.setText(mGrades.getText().toString());
        mTo.setText(result);
    }

    //endregion

}
