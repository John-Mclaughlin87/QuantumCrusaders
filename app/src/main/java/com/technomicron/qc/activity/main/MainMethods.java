package com.technomicron.qc.activity.main;

import android.util.Log;
import android.view.ViewDebug;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.ui.chat.adaptar.Chat_Adapter;
import com.technomicron.qc.ui.chat.list.Chat_ArrayList_Preview;
import com.technomicron.qc.ui.chat.model.Chat_Model;
import com.technomicron.qc.ui.chat.model.Chat_Model_Preview;

import java.util.Locale;

public class MainMethods extends MainActivity {

	public MainMethods() {
		super();
	}

	public static NavController getNavController() {
		return navController;
	}

	public static NavOptions getNavOptions() {
		return navOptions;
	}

	public static Chat_Adapter getSystemAdapter() {
		return systemAdapter;
	}

	public static Chat_Adapter getContactsAdapter() {
		return contactsAdapter;
	}

	public static Chat_Adapter getFreelancersAdapter() {
		return freelancersAdapter;
	}

	public static Chat_Adapter getOrganizationAdapter() {
		return organizationAdapter;
	}

	public static Chat_ArrayList_Preview<Chat_Model> getQCSystemChatModel() {
		return qcSystemChatModel;
	}

	public static Chat_ArrayList_Preview<Chat_Model> getQCOrganizationModel() {
		return qcOrganizationChatModel;
	}

	public static Chat_ArrayList_Preview<Chat_Model> getQCFreelancersChatModel() {
		return qcFreelancersChatModel;
	}

	public static Chat_ArrayList_Preview<Chat_Model> getQCContactsChatModel() {
		return qcContactsChatModel;
	}

	public static String getCurrentChatFragmenttoChannel() {
		String finChannel = "";
		if (navController.getCurrentDestination().getId() == R.id.navigation_chat) {
			finChannel = "#system";
		} else if (navController.getCurrentDestination().getId() == R.id.navigation_chat_organization) {
			finChannel = "#organization";
		} else if (navController.getCurrentDestination().getId() == R.id.navigation_chat_freelancers) {
			finChannel = "#freelancer";
		} else if (navController.getCurrentDestination().getId() == R.id.navigation_chat_contacts) {
			finChannel = "#contacts";
		}
		Log.v("---------MainActivity", "in getCurrentChatFragmenttoChannel :: " + finChannel);
		return finChannel;
	}

	public static int chatPreviewDestination(int position) {
		int chatPreviewDestination = R.id.navigation_chat_organization;
		Chat_Model_Preview dataModel = qcChatPreviewChatModel.get(position);
		int currentDestination = navController.getCurrentDestination().getId();
		if (currentDestination == R.id.navigation_system) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_system_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_system_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_system_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_system_to_navigation_chat_contacts;
			}
		} else if (currentDestination == R.id.navigation_organization) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_organization_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_organization_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_organization_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_organization_to_navigation_chat_contacts;
			}
		} else if (currentDestination == R.id.navigation_hangar) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_hangar_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_hangar_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_hangar_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_hangar_to_navigation_chat_contacts;
			}
		} else if (currentDestination == R.id.navigation_ordnance) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_ordnance_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_ordnance_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_ordnance_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_ordnance_to_navigation_chat_contacts;
			}
		} else if (currentDestination == R.id.navigation_freelance) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_freelance_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_freelance_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_freelance_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_freelance_to_navigation_chat_contacts;
			}
		} else if (currentDestination == R.id.navigation_hangar_fleetyards) {
			if (dataModel.getChannel().matches("#system")) {
				chatPreviewDestination = R.id.action_navigation_hangar_fleetyards_to_navigation_chat;
			} else if (dataModel.getChannel().matches("#organization")) {
				chatPreviewDestination = R.id.action_navigation_hangar_fleetyards_to_navigation_chat_organization;
			} else if (dataModel.getChannel().matches("#freelancers")) {
				chatPreviewDestination = R.id.action_navigation_hangar_fleetyards_to_navigation_chat_freelancers;
			} else if (dataModel.getChannel().matches("#contacts")) {
				chatPreviewDestination = R.id.action_navigation_hangar_fleetyards_to_navigation_chat_contacts;
			}
		}
		return chatPreviewDestination;
	}

	public void startReceiver() {
		mainActivityIntentFilter.addAction("RECEIVED_USER_DETAILS");
		mainActivityIntentFilter.addAction("RECEIVE_MESSAGE");
		mainActivityIntentFilter.addAction("ACTION");
		mainActivityIntentFilter.addAction("UPDATE_NOTIFICATION");
		MainActivity.getInstance().registerReceiver(mainActivity_Receiver, mainActivityIntentFilter);
	}

	public void updateText(String channel, String text, String sender) {
		Log.v("---------MainActivity", "in updateText");

		if (channel.matches("#system")) {
			qcSystemChatModel.add(new Chat_Model(channel, text, sender));
			systemAdapter.notifyDataSetChanged();
			qcChatPreviewChatModel.add(new Chat_Model_Preview(channel, text, sender));
			qcChatPreviewAdapter.notifyDataSetChanged();
		} else if (channel.matches("#organization")) {
			qcOrganizationChatModel.add(new Chat_Model(channel, text, sender));
			organizationAdapter.notifyDataSetChanged();
			qcChatPreviewChatModel.add(new Chat_Model_Preview(channel, text, sender));
			qcChatPreviewAdapter.notifyDataSetChanged();
		} else if (channel.matches("#freelancer")) {
			qcFreelancersChatModel.add(new Chat_Model(channel, text, sender));
			freelancersAdapter.notifyDataSetChanged();
			qcChatPreviewChatModel.add(new Chat_Model_Preview(channel, text, sender));
			qcChatPreviewAdapter.notifyDataSetChanged();
		} else if (channel.matches("#contacts")) {
			qcContactsChatModel.add(new Chat_Model(channel, text, sender));
			contactsAdapter.notifyDataSetChanged();
			qcChatPreviewChatModel.add(new Chat_Model_Preview(channel, text, sender));
			qcChatPreviewAdapter.notifyDataSetChanged();
		}
	}

	public void sendMessage(String channel, String text, String sender) {
		updateText(channel, text, sender);
		if(QCService.getChatClient().isConnected()){
			QCService.getChatClient().sendMessage(channel, text);
		} else {
			QCService.startChatClient();
			QCService.getChatClient().sendMessage(channel, text);
		}
	}

	public void updateNotification(String title, String text) {
		Log.v("-----------MainMethods", "in updateNotification");
		QCService.updateNotification(title, text);
	}

	public void chatButton() {
		int currentDestination = navController.getCurrentDestination().getId();

		if (currentDestination != R.id.navigation_chat
				&& currentDestination != R.id.navigation_chat_organization
				&& currentDestination != R.id.navigation_chat_freelancers
				&& currentDestination != R.id.navigation_chat_contacts) {
			if (currentDestination == R.id.navigation_system) {
				navController.navigate(R.id.action_navigation_system_to_navigation_chat, null, navOptions);
			} else if (currentDestination == R.id.navigation_organization) {
				navController.navigate(R.id.action_navigation_organization_to_navigation_chat, null, navOptions);
			} else if (currentDestination == R.id.navigation_hangar) {
				navController.navigate(R.id.action_navigation_hangar_to_navigation_chat, null, navOptions);
			} else if (currentDestination == R.id.navigation_ordnance) {
				navController.navigate(R.id.action_navigation_ordnance_to_navigation_chat, null, navOptions);
			} else if (currentDestination == R.id.navigation_freelance) {
				navController.navigate(R.id.action_navigation_freelance_to_navigation_chat, null, navOptions);
			} else if (currentDestination == R.id.navigation_hangar_fleetyards) {
				navController.navigate(R.id.action_navigation_hangar_fleetyards_to_navigation_chat, null, navOptions);
			}
		}
		String editText_input = editTextMain.getText().toString();

		if (editText_input.matches("")) {
			//do nothing
		} else {
			Log.v("---------MainMethods", "in sendmessageclick");
			String sender = QCService.getChatClient()._name;
			editTextMain.setText("");
			sendMessage(getCurrentChatFragmenttoChannel(), editText_input, sender);

		}
	}

	public void updateUserDetails(String igc, String pigc, String cigc, String freelancers, String fleetC, String fleetT, String roguesC, String roguesT){

		String fFleetC = formatStringComma(Integer.parseInt(fleetC));
		String fFleetT = formatStringComma(Integer.parseInt(fleetT));
		String fRoguesC = formatStringComma(Integer.parseInt(roguesC));
		String fRoguesT = formatStringComma(Integer.parseInt(roguesT));

		igc_value.setText(igc);
		pigc_value.setText(pigc);
		fleetMax.setText(fFleetT);
		fleetCurrent.setText(fFleetC);
		covertMax.setText(fRoguesT);
		covertCurrent.setText(fRoguesC);
		freelancersTV.setText(freelancers);
		if(fleetC.matches("0")){
			fleetProgress.setProgress(0);
		} else {
			int fProgress = ((Integer.parseInt(fleetC) * 100) / Integer.parseInt(fleetT));
			fleetProgress.setProgress(fProgress);
		}
		if(roguesC.matches("0")){
			covertProgress.setProgress(0);
		} else {
			int cProgress = ((Integer.parseInt(roguesC) * 100) / Integer.parseInt(roguesT));
			covertProgress.setProgress(cProgress);
		}

	}

	private String formatStringComma(int i){
		String fString = String.format("%,d", i);
		return fString;
	}
}
