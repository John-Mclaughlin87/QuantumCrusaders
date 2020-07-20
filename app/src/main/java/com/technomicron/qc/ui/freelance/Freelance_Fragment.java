package com.technomicron.qc.ui.freelance;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import com.technomicron.qc.App;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIFreelancer;
import com.technomicron.qc.gamedata.Shared_Prefs;
import com.technomicron.qc.ui.hangar.combat.Hangar_Fragment_Combat_Adapter;
import com.technomicron.qc.ui.hangar.combat.Hangar_Fragment_Combat_Model;
import com.technomicron.qc.ui.hangar.combat.Hangar_Fragment_Combat_Receiver;

import java.util.ArrayList;

public class Freelance_Fragment extends Fragment implements View.OnClickListener {

	public static Freelance_Fragment instance;
	APIFreelancer api;
	static ArrayList arrayList;
	static Freelance_Adapter adapter;
	static Freelance_Receiver receiver;
	static IntentFilter intentFilter;
	static ListView listView;
	static TextView attack;
	static TextView defense;
	static TextView cAttack;
	static TextView cDefense;
	static TextView mFreelancers;
	static TextView value;
	static CardView listDefaultText;
	static Button hireButton;

	String TAG = "Freelance_Fragment: ";

	public static Freelance_Fragment getInstance() {
		if (instance == null) {
			instance = new Freelance_Fragment();
		}
		return instance;
	}

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		String tFunct = "onCreateView: ";
		Log.v(TAG, tFunct);

		View root = inflater.inflate(R.layout.fragment_freelance, container, false);

		setViewIDs(root);

		initVars();
		initReceiverStuff();
		setDefaultUIState();
		getData();
		hireButton.setOnClickListener(this);
		return root;
	}

	private void initVars(){
		String tFunct = "initVars: ";
		Log.v(TAG, tFunct);
		this.api = new APIFreelancer();
		if(receiver == null){
			Log.v(TAG, tFunct + "create receiver");
			this.receiver = new Freelance_Receiver();
		}
		this.intentFilter = new IntentFilter();
		this.arrayList = new ArrayList<Freelance_ArrayList>();
		this.adapter = new Freelance_Adapter(arrayList, App.getAppContext());
		listView.setAdapter(adapter);

	}

	private void initReceiverStuff(){
		String tFunct = "initReceiverStuff: ";
		intentFilter.addAction("RECEIVED_OWN_FREELANCERS");
		intentFilter.addAction("RECEIVED_USER_DETAILS");
		intentFilter.addAction("RECEIVED_HIREABLE_FREELANCERS");
		Log.v(TAG, tFunct + "register receiver");
		getActivity().registerReceiver(receiver, intentFilter);
	}

	private void setViewIDs(View root){
		String tFunct = "setViewIDs: ";
		Log.v(TAG, tFunct);
		this.listView = root.findViewById(R.id.fFListView);
		this.attack = root.findViewById(R.id.fFAttack);
		this.defense = root.findViewById(R.id.fFDefense);
		this.cAttack = root.findViewById(R.id.fFCovertAttack);
		this.cDefense = root.findViewById(R.id.fFCovertDefense);
		this.hireButton = root.findViewById(R.id.hireFreelancersButton);
		this.mFreelancers = root.findViewById(R.id.fFMaxFreelancersValue);
		this.value = root.findViewById(R.id.fFValue);
		this.listDefaultText = root.findViewById(R.id.fFListDefaultText);

	}

	private void getData(){
		String tFunct = "getData: ";
		Log.v(TAG, tFunct);
		clearArrayList();
		api.getPlayerDetails();
		api.getFreelancerList();
	}

	@Override
	public void onDestroy() {
		super.onPause();
		String tFunct = "onDestroy: ";

		Log.v(TAG, tFunct);
		super.onDestroy();

	}

	@Override
	public void onPause() {
		super.onPause();
		String tFunct = "onPause: ";
		Log.v(TAG, tFunct);

	}

	@Override
	public void onDetach() {
		super.onDetach();
		String tFunct = "onDetach: ";
		Log.v(TAG, tFunct);

	}
	private void setDefaultUIState() {
		String tFunct = "setDefaultUIState: ";
		Log.v(TAG, tFunct);
		MainActivity.getInstance().chatPreviewCardView.setVisibility(View.GONE);
		listDefaultText.setVisibility(View.GONE);
		listView.setEmptyView(listDefaultText);

		//MainActivity.getInstance().status_layout.setVisibility(View.GONE);
		// if (MainActivity.getInstance().chatPreviewCardView.getVisibility() == View.VISIBLE) {
		//			// Its visible
		//		} else {
		//			// Either gone or invisible
		//		}
	}

	public void receivedOwnFreelancerList(ArrayList<String> player) {
		String tFunct = "receivedOwnFreelancerList: ";
		String username = player.get(0);
		String userID = player.get(1);
		String attack = player.get(2);
		String defense = player.get(3);
		String covertAttack = player.get(4);
		String covertDefense = player.get(5);
		String value = player.get(6);
		arrayList.add(new Freelance_Model(username, userID, attack, defense, covertAttack, covertDefense, value));
		adapter.notifyDataSetChanged();
		//Log.v(TAG, tFunct + player.toString());
	}

	public void receivedUserDetails(String freelancer_power, String freelancer_defense, String freelancer_covert_attack,
	                                String freelancer_covert_defense, String freelancerMax, String freelancerValue) {
		String tFunct = "receivedUserDetails: ";
		//Log.v(TAG, tFunct);
		attack.setText(freelancer_power);
		defense.setText(freelancer_defense);
		cAttack.setText(freelancer_covert_attack);
		cDefense.setText(freelancer_covert_defense);
		mFreelancers.setText(freelancerMax);
		value.setText(freelancerValue);
		//Log.v(TAG, tFunct + "Success");
	}

	public void clearArrayList(){
		arrayList.clear();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View view) {
		String tFunct = "onClick: ";
		Log.v(TAG, tFunct);
		NavController navController = MainActivity.getNavController();
		NavOptions navOptions = MainActivity.getNavOptions();
		switch (view.getId()) {
			case R.id.hireFreelancersButton:
				navController.navigate(R.id.action_navigation_freelance_to_navigation_freelance_hire, null, navOptions);
				try {
					if(receiver != null) {
						Log.v(TAG, tFunct + "unregister receiver");
						getActivity().unregisterReceiver(receiver);
					}

				} catch(IllegalArgumentException e) {

					e.printStackTrace();
				}
				break;
		}
	}

	public void removeListItem(Freelance_Model pos){
		arrayList.remove(pos);
		adapter.notifyDataSetChanged();
	}
}