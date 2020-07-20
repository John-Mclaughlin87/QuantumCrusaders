package com.technomicron.qc.ui.hangar.alerts;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.api.APIAlerts;

import java.util.ArrayList;

public class Alerts_Fragment extends Fragment {
	String TAG = "Alerts_Fragment.java";

	public static Alerts_Fragment instance;

	static ArrayList arrayList;
	static Alerts_Adapter adapter;
	static ListView listView;
	static Alerts_Receiver receiver;
	static IntentFilter intentFilter;
	static APIAlerts api;

	public static Alerts_Fragment getInstance() {
		if (instance == null) {
			instance = new Alerts_Fragment();
		}
		return instance;
	}
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_alerts, container, false);
		setDefaultUIState();

		this.arrayList = new ArrayList<Alerts_Model>();
		this.adapter = new Alerts_Adapter(arrayList, App.getAppContext());
		this.listView = root.findViewById(R.id.fAAlertListView);
		listView.setAdapter(adapter);

		this.receiver = new Alerts_Receiver();
		this.intentFilter = new IntentFilter();
		intentFilter.addAction("RECEIVED_ALERT_LIST");
		getActivity().registerReceiver(receiver, intentFilter);

		this.api = new APIAlerts();
		api.getAlertList();

		return root;
	}

	@Override
	public void onDestroy() {
		super.onPause();
		String tFunct = "onDestroy: ";
		getActivity().unregisterReceiver(receiver);
		Log.v(TAG, tFunct);
		super.onDestroy();
	}

	private void setDefaultUIState(){
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().chatPreviewCardView.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);
	}

	public void receivedAlertsList(ArrayList<String> alert){
		String tFunct = "receivedAlertsList";
		Log.v(TAG, tFunct + ": " + alert);
		int alertID = Integer.parseInt(alert.get(0));
		String alertType = alert.get(1);
		int userID = Integer.parseInt(alert.get(2));
		String username = alert.get(3);
		int userID2 = Integer.parseInt(alert.get(4));
		String username2 = alert.get(5);
		int resources = Integer.parseInt(alert.get(6));
		arrayList.add(new Alerts_Model(alertType, userID, username, userID2, username2, alertID, resources));
		adapter.notifyDataSetChanged();


	}
}