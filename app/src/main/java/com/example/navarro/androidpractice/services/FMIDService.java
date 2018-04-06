package com.example.navarro.androidpractice.services;

import android.util.Log;

import com.facebook.notifications.internal.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ${Navarro} on 4/6/18.
 */
public class FMIDService extends FirebaseInstanceIdService {
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            com.facebook.appevents.AppEventsLogger.setPushNotificationsRegistrationId(refreshedToken);
        } catch (Exception e) {
            Log.e("test", "Failed to complete token refresh", e);
        }
    }
}