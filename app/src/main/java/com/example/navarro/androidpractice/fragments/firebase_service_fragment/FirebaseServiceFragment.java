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
import android.widget.TextView;

import com.example.navarro.androidpractice.R;
import com.example.navarro.androidpractice.beans.unpersistible.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FirebaseServiceFragment extends Fragment{

    //region Variables
    //region Views
    TextView mUsername, mPassword, mName, mLastname, mAge;
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
        mUsername = view.findViewById(R.id.fragment_firebase_username);
        mPassword = view.findViewById(R.id.fragment_firebase_password);
        mName = view.findViewById(R.id.fragment_firebase_name);
        mLastname = view.findViewById(R.id.fragment_firebase_lastname);
        mAge = view.findViewById(R.id.fragment_firebase_age);

        getInformationFromFirebase();
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
    private void getInformationFromFirebase(){
        FirebaseDatabase.getInstance().goOnline();
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<User> userList = new ArrayList<>();
                for (DataSnapshot postSnapShot: dataSnapshot.getChildren()) {
                    User user = postSnapShot.getValue(User.class);
                    userList.add(user);
                }
                mName.setText(userList.get(0).getUsername());
                mPassword.setText(userList.get(0).getPassword());
                mName.setText(userList.get(0).getName());
                mLastname.setText(userList.get(0).getLastname());
                mAge.setText("" + userList.get(0).getAge());
                FirebaseDatabase.getInstance().goOffline();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //endregion
}
