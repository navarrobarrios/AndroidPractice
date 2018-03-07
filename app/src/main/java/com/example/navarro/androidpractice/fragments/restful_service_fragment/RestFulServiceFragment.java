package com.example.navarro.androidpractice.fragments.restful_service_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.navarro.androidpractice.R;
import com.example.navarro.androidpractice.adapters.ResultAdapter;
import com.example.navarro.androidpractice.beans.unpersistible.Result;

import java.util.List;

public class RestFulServiceFragment extends Fragment  implements  RestFulServiceContract.View{

    //region Variables
    //region Global
    private RestFulServiceContract.Presenter mPresenter;
    private MaterialDialog mGettingInformationDialog;
    //endregion
    //region Views
    private EditText mTextToSearch;
    private Spinner mOptions;
    private Button mSearch;
    private LinearLayout mResultsContainer;
    private RecyclerView mResultsRecycler;
    private ResultAdapter mResultAdapter;
    private TextView mMessageError;
    private LinearLayout mMessageErrorContainer;
    private LinearLayout mErrorContainer;
    private View _rootView;
    //endregion
    //endregion

    //region Constructor
    public RestFulServiceFragment() {
    }
    //endregion

    //region newInstance
    public static RestFulServiceFragment newInstance() {
        return new RestFulServiceFragment();
    }
    //endregion

    //region Fragment Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RestFulServicePresenter(RestFulServiceFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(_rootView == null ){

            View view =  inflater.inflate(R.layout.fragment_rest_ful, container, false);
            //region viewsInit
            mOptions = view.findViewById(R.id.fragment_rest_ful_options);
            mTextToSearch = view.findViewById(R.id.fragment_rest_ful_text_to_search);
            mSearch = view.findViewById(R.id.fragment_rest_ful_search);
            mResultsRecycler = view.findViewById(R.id.fragment_rest_ful_recycler);
            mResultsContainer = view.findViewById(R.id.fragment_rest_ful_result_container);
            mMessageError = view.findViewById(R.id.fragment_rest_ful_messages);
            mMessageErrorContainer = view.findViewById(R.id.fragment_rest_ful_messages_container);
            mErrorContainer = view.findViewById(R.id.fragment_rest_ful_error_container);
            //endregion
            loadMainInformation();

            return view;
        }else{
            return _rootView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.title_restful));
    }

    //endregion

    //region LocalMethods
    private void doAction(){
        switch (mOptions.getSelectedItemPosition()){
            case 0:
                mPresenter.getAllCountries();
                break;
            case 1:
                if(isValid(2)) mPresenter.getInformationByCode2(mTextToSearch.getText().toString());
                break;
            case 2:
                if(isValid(3))mPresenter.getInformationByCode3(mTextToSearch.getText().toString());
                break;
            case 3:
                if(isValid(null)) mPresenter.getInformationByAny(mTextToSearch.getText().toString());
                break;
        }
    }
    private boolean isValid(Integer n){
        boolean isValid = true;
        if(mTextToSearch.getText().toString().isEmpty()){
            mTextToSearch.setError("*Required Field");
            isValid = false;
        }else if( n!= null && mTextToSearch.getText().length() != n){
            mTextToSearch.setError("*Required exactly " + n + " letters" );
            isValid = false;
        }
        return isValid;
    }

    private void loadMainInformation(){
        mOptions.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.operations_array, android.R.layout.simple_spinner_dropdown_item));
        mOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mTextToSearch.setVisibility( i == 0 ? View.GONE : View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAction();
            }
        });
        mErrorContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAction();
            }
        });
    }
    //endregion

    @Override
    public void showUnknowError() {
        mResultsContainer.setVisibility(View.GONE);
        mMessageErrorContainer.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.VISIBLE);
    }

    //region RestFulServiceContract.View Methods
    @Override
    public void showGettingInformationDialog() {
        mGettingInformationDialog = new MaterialDialog.Builder(RestFulServiceFragment.this.getActivity())
                .content("Getting the result, please wait...")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .canceledOnTouchOutside(false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
    }

    @Override
    public void hideGettingIngotmationDialog() {
        if(mGettingInformationDialog != null && mGettingInformationDialog.isShowing()){
            mGettingInformationDialog.dismiss();
        }
    }

    @Override
    public void showResultFounded(List<Result> resultList) {
        mErrorContainer.setVisibility(View.GONE);
        mResultsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageErrorContainer.setVisibility(View.GONE);
        mResultsContainer.setVisibility(View.VISIBLE);
        mResultAdapter =  new ResultAdapter(resultList);
        mResultsRecycler.setAdapter(mResultAdapter);
    }

    @Override
    public void showNotResultFounded(List<String> messages) {
        mErrorContainer.setVisibility(View.GONE);
        mResultsContainer.setVisibility(View.GONE);
        mMessageErrorContainer.setVisibility(View.VISIBLE);
        String errorMessages = "";
        for (int i = 1; i < messages.size(); i++) {
            errorMessages += messages.get(i) + "\n";
        }
        mMessageError.setText(errorMessages);

    }
    //endregion

}
