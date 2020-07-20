package com.technomicron.qc.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.technomicron.qc.App;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.util.Map;

public class FirebaseService extends FirebaseMessagingService {
	String TAG = "FirebaseService";
	APIMethods api = new APIMethods();

	/**
	 * Called when message is received.
	 *
	 * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
	 */
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		// Check if message contains a data payload (beauty messages).
		if (remoteMessage.getData().size() > 0) {
			//Log.d(TAG, "Message data payload: " + remoteMessage.getData());
			createAndSendNotificationB(remoteMessage);
		}

		// Check if message contains a notification payload (from console).
		if (remoteMessage.getNotification() != null) {
			Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
			try {
				MainActivity.getMainMethods().updateNotification(
						remoteMessage.getNotification().getTitle(),
						remoteMessage.getNotification().getBody()
				);
			} catch (RuntimeException e){
				//App.getInstance().showToastMessage("Firebase got excited");
			}
		}
	}

	private void createAndSendNotificationB(RemoteMessage remoteMessage){
		//Code here
		//App.getInstance().showToastMessage(remoteMessage.toString());
		Map data = remoteMessage.getData();
		String action;
		action = (String) data.get("action");
		switch (action) {
			case "getPlayerDetails": // Notification
				api.getPlayerDetails();
				break;

			default:
				break;
		}
		Log.d(TAG, action);
	}

	@Override
	public void onNewToken(String fcmID) {
		Log.d(TAG, "Refreshed token: " + fcmID);
		api.updateFcmID(fcmID);
		Shared_Prefs.setString(App.getAppContext(), "fcmID", fcmID);
		// If you want to send messages to this application instance or
		// manage this apps subscriptions on the server side, send the
		// Instance ID token to your app server.
		//sendRegistrationToServer(token);
	}


}
