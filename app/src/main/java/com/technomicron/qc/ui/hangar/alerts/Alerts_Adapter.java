package com.technomicron.qc.ui.hangar.alerts;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APICombat;
import com.technomicron.qc.ui.hangar.combat.Hangar_Fragment_Combat_Model;

import java.util.ArrayList;

public class Alerts_Adapter extends ArrayAdapter<Alerts_Model> implements View.OnClickListener{

	Context mContext;
	Alerts_Model model;

	private static class ViewHolder {
		TextView alertID;
		TextView userID;
		TextView userID2;
		TextView alertMessage;

	}

	public Alerts_Adapter(ArrayList<Alerts_Model> data, Context context) {
		super(context, R.layout.item_hangar_alert, data);
		this.mContext = context;

	}

	@Override
	public void onClick(View v) {
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		this.model = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder;


		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_hangar_alert, parent, false);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.alertID = (TextView) convertView.findViewById(R.id.iHAAlertID);
		viewHolder.userID = (TextView) convertView.findViewById(R.id.iHAUserID);
		viewHolder.userID2 = (TextView) convertView.findViewById(R.id.iHAUserID2);
		viewHolder.alertMessage = (TextView) convertView.findViewById(R.id.iHAMessageText);

		String alertString = buildAlertText();
		viewHolder.alertMessage.setText(alertString);
		return convertView;
	}

	private String buildAlertText(){
		String alertString = "";
		String alertType = model.getAlertType();
		switch (alertType){
			case "attackFail": {
				alertString = model.getUsername() + " attacked you. You lost " + model.getResources() + " resources.";
				break;
			}
			case "attackSuccess": {
				alertString = model.getUsername() + " attacked you. You Successfully defended.";
				break;
			}
			case "hired": {
				alertString = model.getUsername() + " hired you. You made " + model.getResources() + " resources.";
				break;
			}
			case "hiredFrom": {
				alertString = model.getUsername() + " hired you from " + model.getUsername2() + ". You made " + model.getResources() + " resources.";
				break;
			}
			case "freelancerSold": {
				alertString = model.getUsername() + " hired "  + model.getUsername2() +  " from you. You made " + model.getResources() + " resources.";
				break;
			}
			case "discharged": {
				alertString = "Your owner " + model.getUsername() + " discharged your from service.";
				break;
			}
		}

		return alertString;
	}
}