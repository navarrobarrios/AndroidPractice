package com.example.navarro.androidpractice.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by ${Navarro} on 3/13/18.
 */

public class BatteryBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.hasExtra("YOLO")){
            Log.e("B-TAG", intent.getStringExtra("YOLO"));
        }

    }
}
