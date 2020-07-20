package com.technomicron.qc.activity.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.technomicron.qc.activity.main.MainActivity;

public class MainActivity_Receiver extends BroadcastReceiver {


    public MainActivity_Receiver(){

        super();

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch(action) {
            case "RECEIVE_MESSAGE": {
                String channel = intent.getExtras().getString("channel");
                String sender = intent.getExtras().getString("sender");
                String text = intent.getExtras().getString("text");
                Log.i("---------------RECEIVER",action + ">>>"  + channel + text);
                MainActivity.getMainMethods().updateText( channel,  text, sender);
                break;
            }
            case "ACTION": {
                String sender = intent.getExtras().getString("sender");
                String channel = intent.getExtras().getString("channel");
                String text = intent.getExtras().getString("text");
                Log.i("---------------RECEIVER", action + ">>>" + sender + ": " + text);
                MainActivity.getMainMethods().updateText(channel, text, sender);
                break;
            }
            case "UPDATE_NOTIFICATION": {
                String extra = intent.getExtras().getString("extra");
                Log.i("---------------RECEIVER", action + ">>>" + action + ": " + extra);
                MainActivity.getMainMethods().updateNotification(action, extra);
                break;
            }
            case "RECEIVED_USER_DETAILS": {
                String igc = intent.getExtras().getString("igc");
                String pigc = intent.getExtras().getString("pigc");
                String cigc = intent.getExtras().getString("cigc");
                String attack_power = intent.getExtras().getString("attack_power");
                String freelancers = intent.getExtras().getString("freelancer");
                String freelancer_power = intent.getExtras().getString("freelancer_power");
                String fleetC = intent.getExtras().getString("fleetC");
                String fleetT = intent.getExtras().getString("fleetT");
                String roguesC = intent.getExtras().getString("roguesC");
                String roguesT = intent.getExtras().getString("roguesT");
                Log.i("---------------RECEIVER", action + ">>>" + igc + pigc + cigc +
                        attack_power + freelancers + freelancer_power + fleetC + fleetT + roguesC + roguesT);
                MainActivity.getMainMethods().updateUserDetails(igc, pigc, cigc, freelancers, fleetC, fleetT, roguesC, roguesT);
                break;
            }
            default:{
                return;
            }

        }

    }

}
