package com.technomicron.qc.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.App;
import com.technomicron.qc.R;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.technomicron.qc.App.CHANNEL_ID;


public class QCService extends Service  {

    private final QCService_Binder mBinder;
    private Chat_Client chatClient;
    IntentFilter serviceIntentFilter;
    NotificationManager mNotificationManager;
    Context context;
    String TAG = "QCService";

    public QCService() {
        super();
        this.mBinder = new QCService_Binder(this);
        this.chatClient = new Chat_Client();
    }

    public Chat_Client getChatClient(){
        return this.chatClient;
    }
    @Override

    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "in onCreate");
        initVars();
        startChatClient();


	    Intent notificationIntent = new Intent(context, MainActivity.class);
	    PendingIntent pendingIntent = PendingIntent.getActivity(context,
			    0, notificationIntent, 0);
	    Notification chatNotification = new NotificationCompat.Builder(context, CHANNEL_ID)
			    .setContentTitle("Notification")
			    .setContentText("Chat Service Started")
                //.setDefaults(Notification.DEFAULT_VIBRATE)
			    .setSmallIcon(R.drawable.ic_launcher_foreground)
			    .setContentIntent(pendingIntent)
			    //.setPriority(Notification.PRIORITY_LOW)
                .setOngoing(true)
			    .build();
        startForeground(1, chatNotification);

    }

    private void initVars(){
        this.serviceIntentFilter = new IntentFilter();
        this.context = getBaseContext();
        this.mNotificationManager = (NotificationManager) getSystemService(context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "in onStartCommand");
        return START_STICKY;
    }

    public void startChatClient(){
        Log.v(TAG, "in startChatClient");
        /**look in IRCCLIENT to see how they close thier thread */
        Thread chatThread = new Thread("QC Chat init"){
            @Override
            public void run(){
                Looper.prepare();

                try {
                    chatClient.connect("chat.technomicron.com", 6667);
                    chatClient.joinChannel("#system");
                    chatClient.joinChannel("#organization");
                    chatClient.joinChannel("#freelancer");
                    chatClient.joinChannel("#contacts");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        chatThread.start();
    }

    private Notification notificationBuilder(String title, String text){
        Log.v(TAG, "in notificationBuilder");
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
		        .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
		        .setPriority(Notification.PRIORITY_MAX)
                .build();
    }

    public void updateNotification(String title, String text){
        Log.v(TAG, "in updateNotification");
        Notification notification = notificationBuilder(title, text);
        mNotificationManager.notify(createRandomCode(7), notification);
    }

    public void disconnectChat(){
        Log.v(TAG, "in disconnectChat");
        if(chatClient.isConnected()){
            chatClient.disconnect();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "in onDestroy");
    }

    @Override
    public QCService_Binder onBind(Intent intent) {
        Log.v(TAG, "in onBind");
        return mBinder;
    }

    public int createRandomCode(int codeLength) {
        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return Integer.parseInt(sb.toString());
    }
}
