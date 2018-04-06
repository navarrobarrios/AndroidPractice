package com.example.navarro.androidpractice.services;

import android.content.Intent;
import android.os.Bundle;

import com.example.navarro.androidpractice.activities.MainActivity;
import com.facebook.notifications.NotificationsManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by ${Navarro} on 4/5/18.
 */
public class FMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Bundle data = new Bundle();
        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
            data.putString(entry.getKey(), entry.getValue());
        }


        if (NotificationsManager.canPresentCard(data)) {
            NotificationsManager.presentNotification(
                    this,
                    data,
                    new Intent(getApplicationContext(), MainActivity.class)
            );
        }
    }
}
