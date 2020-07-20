package com.technomicron.qc.ui.ordnance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;


public class Ordnance_Receiver extends BroadcastReceiver {

	public Ordnance_Receiver(){

		super();

	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();

		switch(action) {
			case "RECEIVED_USER_ORDNANCE": {
				ArrayList<String> ordnance0 = intent.getExtras().getStringArrayList("ordnance0");
				ArrayList<String> ordnance1 = intent.getExtras().getStringArrayList("ordnance1");
				ArrayList<String> ordnance2 = intent.getExtras().getStringArrayList("ordnance2");
				ArrayList<String> ordnance3 = intent.getExtras().getStringArrayList("ordnance3");
				ArrayList<String> ordnance4 = intent.getExtras().getStringArrayList("ordnance4");

				Ordnance_Fragment.getInstance().receivedOrdnanceDetails(ordnance0);
				Ordnance_Fragment.getInstance().receivedOrdnanceDetails(ordnance1);
				Ordnance_Fragment.getInstance().receivedOrdnanceDetails(ordnance2);
				Ordnance_Fragment.getInstance().receivedOrdnanceDetails(ordnance3);
				Ordnance_Fragment.getInstance().receivedOrdnanceDetails(ordnance4);
				Log.i("---------------RECEIVER", ordnance0.toString() + "\n" + ordnance1.toString() + "\n" + ordnance2.toString() + "\n" + ordnance3.toString() + "\n" + ordnance4.toString() + "\n" );
				break;
			}
			default:{
				return;
			}

		}

	}
}
