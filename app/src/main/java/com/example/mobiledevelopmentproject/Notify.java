package com.example.mobiledevelopmentproject;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notify extends Application {
    public static final String channel_1_ID = "channel";

    @Override
    public void onCreate(){
        super.onCreate();

        createChannel();
    }

    //Creates Notification Channel
    public void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    channel_1_ID,
                    "Channel1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
