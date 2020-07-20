package com.technomicron.qc;

import android.app.Application;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

public class App extends Application {

    public static final String CHANNEL_ID = "ChatServiceChannel";
    private static Context context;
    protected static App instance;
    private static String token;


    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();

        this.token = Settings.Secure.getString(App.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Chat Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    public static Context getAppContext() {
        return App.context;
    }

    public static String getToken() {
        return App.token;
    }

    public void showToastMessage(String msg) {
        Toast toast = Toast.makeText(this.context, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}