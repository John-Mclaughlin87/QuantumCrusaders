package com.technomicron.qc.ui.hangar.combat;

		import android.content.Context;
		import android.os.AsyncTask;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;
		import android.widget.ArrayAdapter;
		import android.widget.Button;
		import android.widget.TextView;

		import com.technomicron.qc.App;
		import com.technomicron.qc.R;
		import com.technomicron.qc.api.APICombat;
		import com.technomicron.qc.api.APIMethods;

		import java.util.ArrayList;

public class Hangar_Fragment_Combat_Adapter extends ArrayAdapter<Hangar_Fragment_Combat_Model> implements View.OnClickListener{

	Context mContext;
	APICombat api;
	Hangar_Fragment_Combat_Model model;

	private static class ViewHolder {
		TextView cIUsername;
		TextView cIUserID;
		TextView cIRiskFactor;
		TextView cIOrgName;
	}

	public Hangar_Fragment_Combat_Adapter(ArrayList<Hangar_Fragment_Combat_Model> data, Context context) {
		super(context, R.layout.item_hangar_combat, data);
		this.mContext = context;
		this.api = new APICombat();

	}

	@Override
	public void onClick(View v) {
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		this.model = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder;

		final View result;

		if (convertView == null) {

			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_hangar_combat, parent, false);

			viewHolder.cIUsername = (TextView) convertView.findViewById(R.id.cIUsername);
			viewHolder.cIUserID = (TextView) convertView.findViewById(R.id.cIUserID);
			viewHolder.cIRiskFactor = (TextView) convertView.findViewById(R.id.cIRiskFactor);
			viewHolder.cIOrgName = (TextView) convertView.findViewById(R.id.cIOrgName);

			result=convertView;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			result=convertView;
		}

		viewHolder.cIUsername.setText(model.getUsername());
		viewHolder.cIUserID.setText(model.getUserID());
		String rFString = riskToString(model.getRiskFactor());
		viewHolder.cIRiskFactor.setText(rFString);
		viewHolder.cIOrgName.setText(model.getOrgName());
		final ViewHolder vHolder = viewHolder;

		final Button iHCAttackButton = (Button) convertView.findViewById(R.id.iHCAttackButton);
		iHCAttackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (vHolder.cIUserID.getText().toString().matches("")) {
					App.getInstance().showToastMessage("User to attack required");
				} else {

					final Integer cIUserIDFin = Integer.parseInt(vHolder.cIUserID.getText().toString());

					new AsyncTask<Void, Void, Void>() {
						String mResponse;

						@Override
						protected Void doInBackground(Void... voids) {
							mResponse = api.CombatAttack(cIUserIDFin);
							return null;
						}

						@Override
						protected void onPostExecute(Void aVoid) {
							App.getInstance().showToastMessage(mResponse);
							super.onPostExecute(aVoid);
						}
					}.execute();
				}
			}
		});

		return result;
	}

	private String riskToString(String rF){
		String rFString = "";
		if(rF.matches("1")){
			rFString = "Low Risk";
		} else if (rF.matches("2")){
			rFString = "Medium Risk";
		} else if (rF.matches("3")){
			rFString = "High Risk";
		}
		return rFString;
	}
}