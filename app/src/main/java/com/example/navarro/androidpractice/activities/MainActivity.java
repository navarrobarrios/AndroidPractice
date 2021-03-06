package com.example.navarro.androidpractice.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.navarro.androidpractice.R;
import com.example.navarro.androidpractice.fragments.firebase_service_fragment.FirebaseServiceFragment;
import com.example.navarro.androidpractice.fragments.more_options_fragment.MoreOptionsFragment;
import com.example.navarro.androidpractice.fragments.restful_service_fragment.RestFulServiceFragment;
import com.example.navarro.androidpractice.fragments.soap_service_fragment.SoapServiceFragment;
import com.example.navarro.androidpractice.services.BatteryBroadcast;
import com.example.navarro.androidpractice.utils.ChangeNavigationViewProperties;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.notifications.NotificationsManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        if (getIntent() != null && getIntent().getExtras() != null){
            NotificationsManager.presentCard(this, getIntent().getExtras());
        }

//        Intent intent = new Intent(this, BatteryBroadcast.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 001,  intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, 1000, pendingIntent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        FacebookSdk.sdkInitialize(this);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        AppEventsLogger.setPushNotificationsRegistrationId(refreshedToken);
        AppEventsLogger.setUserID(UUID.randomUUID().toString());
        NotificationsManager.presentCardFromNotification(this);
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent() != null && getIntent().getExtras() != null){
            NotificationsManager.presentCard(this, getIntent().getExtras());
        }
        NotificationsManager.presentCardFromNotification(this);
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
