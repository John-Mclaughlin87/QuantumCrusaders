package com.technomicron.qc.ui.hangar.fleetyard;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.gamedata.BuildingDetails;

import java.util.ArrayList;

public class Hangar_Fleet_Dialog_Purchase extends Dialog {

	public Activity activity;
	ListView listView;
	Hangar_Fleet_Dialog_Purchase_Adapter adapter;
	ArrayList<String> bDeetsList;
	Hangar_Fleet_Dialog_Purchase_ArrayList arrayList;
	String buildingLocation;


	public Hangar_Fleet_Dialog_Purchase(Activity activity, String buildingLocation) {
		super(activity);
		this.buildingLocation = buildingLocation;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_fleetyards_purchase);

		this.listView = findViewById(R.id.fleetyards_purchase_listview);

		this.arrayList = new Hangar_Fleet_Dialog_Purchase_ArrayList<Hangar_Fleet_Dialog_Purchase_Model>();
		this.adapter = new Hangar_Fleet_Dialog_Purchase_Adapter(this, arrayList, App.getAppContext(), buildingLocation);

		BuildingDetails bDeets = new BuildingDetails();
		bDeetsList = bDeets.getBuildingDetails(10);
		arrayList.add(new Hangar_Fleet_Dialog_Purchase_Model(
				bDeetsList.get(0),
				bDeetsList.get(1),
				bDeetsList.get(2),
				bDeetsList.get(3),
				bDeetsList.get(4),
				bDeetsList.get(5),
				bDeetsList.get(6),
				bDeetsList.get(7),
				bDeetsList.get(8),
				bDeetsList.get(9),
				bDeetsList.get(10)
		));

		bDeetsList = bDeets.getBuildingDetails(13);
		arrayList.add(new Hangar_Fleet_Dialog_Purchase_Model(
				bDeetsList.get(0),
				bDeetsList.get(1),
				bDeetsList.get(2),
				bDeetsList.get(3),
				bDeetsList.get(4),
				bDeetsList.get(5),
				bDeetsList.get(6),
				bDeetsList.get(7),
				bDeetsList.get(8),
				bDeetsList.get(9),
				bDeetsList.get(10)
		));

		bDeetsList = bDeets.getBuildingDetails(16);
		arrayList.add(new Hangar_Fleet_Dialog_Purchase_Model(
				bDeetsList.get(0),
				bDeetsList.get(1),
				bDeetsList.get(2),
				bDeetsList.get(3),
				bDeetsList.get(4),
				bDeetsList.get(5),
				bDeetsList.get(6),
				bDeetsList.get(7),
				bDeetsList.get(8),
				bDeetsList.get(9),
				bDeetsList.get(10)
		));

		listView.setAdapter(adapter);

	}

}