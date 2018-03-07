package com.example.navarro.androidpractice.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.anavrrropc.practicejanb.R;
import com.example.navarro.androidpractice.fragments.firebase_service_fragment.FirebaseServiceFragment;
import com.example.navarro.androidpractice.fragments.more_options_fragment.MoreOptionsFragment;
import com.example.navarro.androidpractice.fragments.restful_service_fragment.RestFulServiceFragment;
import com.example.navarro.androidpractice.fragments.soap_service_fragment.SoapServiceFragment;
import com.example.navarro.androidpractice.utils.ChangeNavigationViewProperties;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //region Variables
    private BottomNavigationView mBottomNavigationView;
    public static final String BACK_STACK_NAME = "STACK";
    private List<Integer> mInstances;
    //endregion

    //region Activity Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.main_activity_bottom_navigation_view);
        configureBottomNavigationView();
        prepareFragmentManager();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            MainActivity.this.finish();
        }else{
            super.onBackPressed();
            unCheckMenu();
            mInstances.remove(mInstances.size()-1);
            mBottomNavigationView.getMenu().getItem(mInstances.get(mInstances.size() -1 )).setChecked(true);
        }
    }
    //endregion

    //region Local Methods
    private void unCheckMenu(){
        for (int i = 0; i < mBottomNavigationView.getMenu().size(); i++) {
            mBottomNavigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    private void changeFragment(Fragment fragment, String TAG){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_main_container, fragment, TAG)
                .addToBackStack(BACK_STACK_NAME)
                .commit();
    }

    private void prepareFragmentManager() {
        mInstances.add(0);
        changeFragment(RestFulServiceFragment.newInstance(),RestFulServiceFragment.class.getSimpleName());
    }

    private void configureBottomNavigationView() {
        ChangeNavigationViewProperties bottomNavigationViewHelper = new ChangeNavigationViewProperties();
        bottomNavigationViewHelper.removeShiftMode(mBottomNavigationView);
        mInstances = new ArrayList<>();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_main_nav_option_one:
                        mInstances.add(0);
                            changeFragment(RestFulServiceFragment.newInstance(), RestFulServiceFragment.class.getSimpleName());
                            return true;
                    case R.id.menu_main_nav_option_two:
                        mInstances.add(1);
                        changeFragment(SoapServiceFragment.newInstance(), SoapServiceFragment.class.getSimpleName());
                        return true;
                    case R.id.menu_main_nav_option_three:
                        mInstances.add(2);
                        changeFragment(FirebaseServiceFragment.newInstance(), FirebaseServiceFragment.class.getSimpleName());
                        return true;
                    case R.id.menu_main_nav_option_four:
                        mInstances.add(3);
                        changeFragment(MoreOptionsFragment.newInstance(), MoreOptionsFragment.class.getSimpleName());
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    //endregion

    //region Classes
    //region Interfaces
    public interface OnChangeFragment{
        void onChangeFragment();
    }
    //endregion
    //endregion
}
