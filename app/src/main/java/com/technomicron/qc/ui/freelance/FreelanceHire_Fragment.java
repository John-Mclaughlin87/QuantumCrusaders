package com.technomicron.qc.ui.freelance;

import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.api.APIFreelancer;
import com.technomicron.qc.ui.hangar.Hangar_Vault_Dialog;

import java.util.ArrayList;

public class FreelanceHire_Fragment extends Fragment implements View.OnClickListener  {

	APIFreelancer api;
	static ArrayList arrayList;
	static ListView listView;
	static FreelanceHire_Adapter adapter;
	static Freelance_Receiver receiver;
	static IntentFilter intentFilter;
	static Button filterResultsButton;
	String TAG = "FreelanceHire_Fragment";


	public static FreelanceHire_Fragment instance;
	public static FreelanceHire_Fragment getInstance() {
		if (instance == null) {
			instance = new FreelanceHire_Fragment();
		}
		return instance;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.fHFFilterResultsButton:
				FreelanceHireFilter_Dialog dialog = new FreelanceHireFilter_Dialog(getActivity());
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				//dialog.setCancelable(false);
				//dialog.setCanceledOnTouchOutside(false);
				dialog.show();
				break;

		}
	}

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		String tFunct = "onCreateView";
		setDefaultUIState();
		View root = inflater.inflate(R.layout.fragment_freelance_hire, container, false);
		this.api = new APIFreelancer();
		this.receiver = new Freelance_Receiver();
		this.intentFilter = new IntentFilter();
		intentFilter.addAction("RECEIVED_HIREABLE_FREELANCERS");
		Log.v(TAG, tFunct + ": register receiver");
		getActivity().registerReceiver(receiver, intentFilter);
		this.arrayList = new ArrayList<Freelance_ArrayList>();
		this.adapter = new FreelanceHire_Adapter(arrayList, App.getAppContext());
		this.listView = root.findViewById(R.id.fHFListview);
		this.filterResultsButton = root.findViewById(R.id.fHFFilterResultsButton);
		filterResultsButton.setOnClickListener(this);
		listView.setAdapter(adapter);
		//arrayList.add(new Freelance_Model("username23234234234234", "userID", "attack", "defense", "covertAttack", "covertDefense", "value"));
		//arrayList.add(new Freelance_Model("username23234234234234", "userID", "attack", "defense", "covertAttack", "covertDefense", "value"));
		//arrayList.add(new Freelance_Model("username23234234234234", "userID", "attack", "defense", "covertAttack", "covertDefense", "value"));
		//arrayList.add(new Freelance_Model("username23234234234234", "userID", "attack", "defense", "covertAttack", "covertDefense", "value"));
		//arrayList.add(new Freelance_Model("username23234234234234", "userID", "attack", "defense", "covertAttack", "covertDefense", "value"));
		//adapter.notifyDataSetChanged();
		adapter.clear();
		adapter.notifyDataSetChanged();
		api.getHireableFreelancerList();
		return root;
	}

	public void removeListItem(Freelance_Model pos){
		arrayList.remove(pos);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onDestroyView() {
		String tFunct = "onDestroyView";
		Log.v(TAG, tFunct);
		super.onDestroyView();
		getActivity().unregisterReceiver(receiver);
	}

	private void setDefaultUIState(){
		String tFunct = "setDefaultUIState";
		//Log.v(TAG, tFunct);
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);
	}

	public void receivedHireableFreelancerList(ArrayList<String> player) {
		String tFunct = "receivedHirableFreelancerList";
		//Log.v(TAG, tFunct);
		String username = player.get(0);
		String userID = player.get(1);
		String attack = player.get(2);
		String defense = player.get(3);
		String covertAttack = player.get(4);
		String covertDefense = player.get(5);
		String value = player.get(6);
		arrayList.add(new Freelance_Model(username, userID, attack, defense, covertAttack, covertDefense, value));
		adapter.notifyDataSetChanged();
		//Log.v(TAG, tFunct + ":" + player.toString());
	}
}
