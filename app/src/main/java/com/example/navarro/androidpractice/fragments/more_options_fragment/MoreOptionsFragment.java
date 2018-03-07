package com.example.navarro.androidpractice.fragments.more_options_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navarro.androidpractice.R;


public class MoreOptionsFragment extends Fragment {

    //region Constructor
    public MoreOptionsFragment() {
    }
    //endregion

    //region getInstance
    public static MoreOptionsFragment newInstance() {
        return new MoreOptionsFragment();
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
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.title_more));
    }

    //endregion
}
