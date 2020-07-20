package com.technomicron.qc.ui.hangar.combat;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technomicron.qc.App;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIMethods;

import java.util.ArrayList;

public class Hangar_Fragment_Combat extends Fragment implements OnClickListener {


	public static Hangar_Fragment_Combat instance;

	static APIMethods api;
	static ArrayList arrayList;
	static Hangar_Fragment_Combat_Adapter adapter;
	static Hangar_Fragment_Combat_Receiver receiver;
	static IntentFilter intentFilter;
	static ListView combatListView;
	static View root;
	static Button refreshButton;

	public static Hangar_Fragment_Combat getInstance() {
		if (instance == null) {
			instance = new Hangar_Fragment_Combat();
		}
		return instance;
	}

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {

		setDefaultUIState();
		this.initVars(inflater, container);
		this.getCombatList();
		return root;
	}


	private void setDefaultUIState(){
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getActivity().unregisterReceiver(receiver);

	}

	private void initVars(LayoutInflater inflater, ViewGroup container){
		this.instance = this;
		this.intentFilter = new IntentFilter();
		this.receiver = new Hangar_Fragment_Combat_Receiver();
		this.arrayList = new ArrayList<Hangar_Fragment_Combat_Model>();
		this.adapter = new Hangar_Fragment_Combat_Adapter(arrayList, App.getAppContext());
		this.api = new APIMethods();
		this.root = inflater.inflate(R.layout.fragment_hangar_combat, container, false);
		this.combatListView = root.findViewById(R.id.hangar_combat_listview);
		this.refreshButton = root.findViewById(R.id.hfcRefresh);


		intentFilter.addAction("RECEIVED_COMBAT_LIST");
		getActivity().registerReceiver(receiver, intentFilter);
		combatListView.setAdapter(adapter);
		refreshButton.setOnClickListener(this);

	}

	public void receivedCombatList(ArrayList<String> player){
		String username = player.get(0);
		String userID = player.get(1);
		String orgName = player.get(2);
		String riskFactor = player.get(3);
		arrayList.add(new Hangar_Fragment_Combat_Model(username, userID, orgName, riskFactor));
		adapter.notifyDataSetChanged();
		Log.v("-----receivedCombatList", player.toString());
	}

	public void clearArrayList(){
		arrayList.clear();
		adapter.notifyDataSetChanged();
	}

	private void getCombatList(){
		clearArrayList();
		api.getCombatList();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.hfcRefresh:
				clearArrayList();
				api.getCombatList();
				break;
		}
	}
}