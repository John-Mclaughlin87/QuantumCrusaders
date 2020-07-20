package com.technomicron.qc.ui.hangar.fleetyard;


import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;
		import android.view.animation.Animation;
		import android.view.animation.AnimationUtils;
		import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIBuildings;
import com.technomicron.qc.api.APIMethods;

import java.util.ArrayList;

public class Hangar_Fleet_Dialog_Purchase_Adapter extends ArrayAdapter<Hangar_Fleet_Dialog_Purchase_Model> implements View.OnClickListener{
	Context mContext;
	APIBuildings api;
	String buildingLocation;
	Dialog dialog;
	String TAG = "Hangar_Fleet_Dialog_Purchase_Adapter";

	// View lookup cache
	private static class ViewHolder {
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
		Button fleetPurchaseButton;
	}

	public Hangar_Fleet_Dialog_Purchase_Adapter(Dialog dialog, ArrayList<Hangar_Fleet_Dialog_Purchase_Model> data, Context context, String buildingLocation) {
		super(context, R.layout.item_fleetyards_purchase_dialog, data);
		this.mContext = context;
		this.api = new APIBuildings();
		this.buildingLocation = buildingLocation;
		this.dialog = dialog;

	}

	@Override
	public void onClick(View v) {
	}

	private int lastPosition = -1;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Hangar_Fleet_Dialog_Purchase_Model model = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder; // view lookup cache stored in tag

		final View result;

		if (convertView == null) {

			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_fleetyards_purchase_dialog, parent, false);
			viewHolder.bName = (TextView) convertView.findViewById(R.id.buildingName);
			viewHolder.bType = (TextView) convertView.findViewById(R.id.buildingType);
			viewHolder.tier = (TextView) convertView.findViewById(R.id.tierValue);
			viewHolder.level = (TextView) convertView.findViewById(R.id.levelValue);
			viewHolder.uName = (TextView) convertView.findViewById(R.id.unameValue);
			viewHolder.uNumber = (TextView) convertView.findViewById(R.id.unumberValue);
			viewHolder.attack = (TextView) convertView.findViewById(R.id.attackValue);
			viewHolder.defense = (TextView) convertView.findViewById(R.id.defenseValue);
			viewHolder.cAttack = (TextView) convertView.findViewById(R.id.covertAttackValue);
			viewHolder.cDefense = (TextView) convertView.findViewById(R.id.covertDefenseValue);
			viewHolder.cost = (TextView) convertView.findViewById(R.id.costValue);
			viewHolder.fleetPurchaseButton = (Button) convertView.findViewById(R.id.fleetPurchaseButton);

			result=convertView;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			result=convertView;
		}

		//Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
		//result.startAnimation(animation);
		lastPosition = position;

		viewHolder.bName.setText(model.bName);
		viewHolder.bType.setText(model.bType);
		viewHolder.tier.setText(model.tier);
		viewHolder.level.setText(model.level);
		viewHolder.uName.setText(model.uName);
		viewHolder.uNumber.setText(model.uNumber);
		viewHolder.attack.setText(model.attack);
		viewHolder.defense.setText(model.defense);
		viewHolder.cAttack.setText(model.cAttack);
		viewHolder.cDefense.setText(model.cDefense);
		viewHolder.cost.setText(model.cost);

		int buildingNumber = 10;
		if(position == 0){
			buildingNumber = 10;
		}
		if(position == 1){
			buildingNumber = 13;
		}
		if(position == 2){
			buildingNumber = 16;
		}
		final int finalBuildingNumber = buildingNumber;
		viewHolder.fleetPurchaseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.v(TAG,buildingLocation +":"+finalBuildingNumber);


				new AsyncTask<Void, Void, Void>() {
					String rString = "";

					@Override
					protected Void doInBackground(Void... voids) {
						rString = api.playerBuildingPurchase(buildingLocation, finalBuildingNumber);
						Log.v(TAG, buildingLocation);

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						App.getInstance().showToastMessage(rString);
						dialog.dismiss();
						api.getPlayerDetails();
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});
		// Return the completed view to render on screen
		return convertView;
	}
}