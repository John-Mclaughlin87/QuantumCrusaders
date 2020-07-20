
package com.technomicron.qc.ui.hangar.combat;

		import android.content.BroadcastReceiver;
		import android.content.Context;
		import android.content.Intent;
		import android.util.Log;

		import java.util.ArrayList;


public class Hangar_Fragment_Combat_Receiver extends BroadcastReceiver {

	public Hangar_Fragment_Combat_Receiver(){
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();

		switch(action) {
			case "RECEIVED_COMBAT_LIST": {
				ArrayList<String> player = intent.getExtras().getStringArrayList("player");
				Log.v("-HANGAR-COMBAT-RECEIVER", player.toString());
				Hangar_Fragment_Combat.getInstance().receivedCombatList(player);
				player.clear();
				break;
			}
			default:{
				return;
			}
		}
	}
}