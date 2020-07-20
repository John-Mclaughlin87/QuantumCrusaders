package com.technomicron.qc.ui.freelance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;


public class Freelance_Receiver extends BroadcastReceiver {

	public Freelance_Receiver() {
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();

		switch (action) {
			case "RECEIVED_OWN_FREELANCERS": {
				ArrayList<String> player = intent.getExtras().getStringArrayList("freelancers_owned");
				//Log.v("Freelance_Receiver", player.toString());
				Freelance_Fragment.getInstance().receivedOwnFreelancerList(player);
				player.clear();
				break;
			}
			case "RECEIVED_USER_DETAILS": {
				String freelancer_power = intent.getExtras().getString("freelancer_power");
				String freelancer_defense = intent.getExtras().getString("freelancer_defense");
				String freelancer_covert_attack = intent.getExtras().getString("freelancer_covert_attack");
				String freelancer_covert_defense = intent.getExtras().getString("freelancer_covert_defense");
				String freelancerMax = intent.getExtras().getString("freelancerMax");
				String freelancerValue = intent.getExtras().getString("freelancerValue");
				//Log.i("---------------RECEIVER", action + ">>>" + freelancer_power + freelancer_defense + freelancer_covert_attack + freelancer_covert_defense);
				Freelance_Fragment.getInstance().receivedUserDetails(freelancer_power, freelancer_defense, freelancer_covert_attack, freelancer_covert_defense, freelancerMax, freelancerValue);
				break;
			}
			case "RECEIVED_HIREABLE_FREELANCERS": {
				ArrayList<String> player = intent.getExtras().getStringArrayList("freelancers_hireable");
				//Log.v("FreelanceHire_Receiver", player.toString());
				FreelanceHire_Fragment.getInstance().receivedHireableFreelancerList(player);
				player.clear();
				break;
			}
			default: {
				return;
			}
		}
	}
}
