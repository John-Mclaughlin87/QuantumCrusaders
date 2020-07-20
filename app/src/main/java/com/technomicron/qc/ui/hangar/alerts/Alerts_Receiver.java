
package com.technomicron.qc.ui.hangar.alerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.technomicron.qc.ui.hangar.combat.Hangar_Fragment_Combat;

import java.util.ArrayList;


public class Alerts_Receiver extends BroadcastReceiver {
	String TAG = "Alerts_Receiver.java";
	public Alerts_Receiver(){
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String tfunct = "onReceive: ";
		String action = intent.getAction();

		switch(action) {
			case "RECEIVED_ALERT_LIST": {
				ArrayList<String> alert = intent.getExtras().getStringArrayList("alert");
				Log.v(TAG, tfunct + action + "- " + alert.toString());
				Alerts_Fragment.getInstance().receivedAlertsList(alert);
				alert.clear();
				break;
			}
			default:{
				return;
			}
		}
	}
}