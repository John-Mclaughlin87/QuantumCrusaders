package com.technomicron.qc.ui.hangar.fleetyard;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIBuildings;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.gamedata.BuildingDetails;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.util.ArrayList;

public class Hangar_Fleet_Dialog_Upgrade extends Dialog {

	public Activity activity;
	APIBuildings api;
	Button upgradeButton;
	Button sellButton;
	String buttonName;
	ImageView image;
	BuildingDetails bd;
	ArrayList<String> details;
	TextView bName;
	TextView bType;
	TextView tier;
	TextView level;
	TextView uName;
	TextView uNumber;
	TextView attack;
	TextView defense;
	TextView cAttack;
	TextView cDefense;
	TextView cost;


	public Hangar_Fleet_Dialog_Upgrade(Activity activity, String buttonName) {
		super(activity);
		this.activity = activity;
		this.buttonName = buttonName;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_fleetyards_upgrade);
		this.api = new APIBuildings();
		this.upgradeButton = findViewById(R.id.upgradeButton);
		this.sellButton = findViewById(R.id.sellButton);
		this.bd = new BuildingDetails();
		this.image = findViewById(R.id.currentBuilding);
		this.bName = findViewById(R.id.buildingName);
		this.bType = findViewById(R.id.buildingType);
		this.tier = findViewById(R.id.tierValue);
		this.level = findViewById(R.id.levelValue);
		this.uName = findViewById(R.id.unameValue);
		this.uNumber = findViewById(R.id.unumberValue);
		this.attack = findViewById(R.id.attackValue);
		this.defense = findViewById(R.id.defenseValue);
		this.cAttack = findViewById(R.id.covertAttackValue);
		this.cDefense = findViewById(R.id.covertDefenseValue);
		this.cost = findViewById(R.id.costValue);
		getButtonImage(Shared_Prefs.getInt(App.getAppContext(), buttonName));
		upgradeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new AsyncTask<Void, Void, Void>() {
					String rString = "";

					@Override
					protected Void doInBackground(Void... voids) {

						rString = api.playerBuildingUpgrade(buttonName);
						Log.v("dialog-purchase-----", buttonName);

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						App.getInstance().showToastMessage(rString);
						if(rString.matches("Upgrade Successful")){

						}api.getPlayerDetails();
						dismiss();
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});
		sellButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new AsyncTask<Void, Void, Void>() {
					String rString = "";

					@Override
					protected Void doInBackground(Void... voids) {

						rString = api .playerBuildingSell(buttonName);
						Log.v("dialog-sell------------", buttonName);

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						App.getInstance().showToastMessage(rString);
						if(rString.matches("Sale Successful")){
							api.getPlayerDetails();
						}api.getPlayerDetails();
						dismiss();
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});

	}


	private void getButtonImage(int building) {
		switch (building) {
			case 0: {
				return;
			}
			case 1: {
				image.setImageResource(R.drawable.c11);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 2: {
				image.setImageResource(R.drawable.c12);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 3: {
				image.setImageResource(R.drawable.c13);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 4: {
				image.setImageResource(R.drawable.c11);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 5: {
				image.setImageResource(R.drawable.c12);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 6: {
				image.setImageResource(R.drawable.c13);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 7: {
				image.setImageResource(R.drawable.c11);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 8: {
				image.setImageResource(R.drawable.c12);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 9: {
				image.setImageResource(R.drawable.c13);
				this.details = bd.getBuildingDetails(building);
				sellButton.setVisibility(View.GONE);
				break;
			}
			case 10: {
				image.setImageResource(R.drawable.a11);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 11: {
				image.setImageResource(R.drawable.a12);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 12: {
				image.setImageResource(R.drawable.a13);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 13: {
				image.setImageResource(R.drawable.b11);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 14: {
				image.setImageResource(R.drawable.b12);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 15: {
				image.setImageResource(R.drawable.b13);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 16: {
				image.setImageResource(R.drawable.d11);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 17: {
				image.setImageResource(R.drawable.d12);
				this.details = bd.getBuildingDetails(building);
				break;
			}
			case 18: {
				image.setImageResource(R.drawable.d13);
				this.details = bd.getBuildingDetails(building);
				break;
			}
		}
		bName.setText(details.get(0));
		bType.setText(details.get(1));
		tier.setText(details.get(2));
		level.setText(details.get(3));
		if (details.get(4) == null) {
			uName.setText("N/A");
		} else {
			uName.setText(details.get(4));
		}
		if (details.get(5) == null) {
			uNumber.setText("N/A");
		} else {
			uNumber.setText(details.get(5));
		}
		attack.setText(details.get(6));
		defense.setText(details.get(7));
		cAttack.setText(details.get(8));
		cDefense.setText(details.get(9));
		cost.setText(details.get(10));
	}
}