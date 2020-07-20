package com.technomicron.qc.service;


import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import com.technomicron.protocol.IRCClient;
import com.technomicron.qc.App;


public class Chat_Client extends IRCClient  {


    public Chat_Client() {

        SharedPreferences qcShPrefs = App.getAppContext().getSharedPreferences("qcShPrefs", 0); // 0 - for private mode

        String name = qcShPrefs.getString("username", Settings.Secure.getString(App.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        this.setName(name);
        this.setVersion(name);
        this.setNick(name);
        this.setLogin(name);
        this.setVerbose(true);
        //this.setAutoNickChange(true);
        //this.setFinger("http://www.youtube.com/watch?v=oHg5SJYRHA0");
    }


    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String text) {
        Log.v("-----------Chat_Client", "in onMessage");
        Intent  intent = new Intent ("RECEIVE_MESSAGE");
        intent.putExtra("channel", channel);
        intent.putExtra("sender", sender);
        intent.putExtra("text", text);
        App.getAppContext().sendBroadcast(intent);

    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        Log.v("-----------Chat_Client", "in onAction");
        Intent intent = new Intent ("ACTION");
        intent.putExtra("sender", sender);
        intent.putExtra("text", action);
        App.getAppContext().sendBroadcast(intent);
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        super.onPrivateMessage(sender, login, hostname, message);
        Log.v("-----------Chat_Client", "in onMessage");
        Intent  intent = new Intent ("RECEIVE_MESSAGE");
        intent.putExtra("sender", sender);
        intent.putExtra("text", message);
        App.getAppContext().sendBroadcast(intent);
    }
}
