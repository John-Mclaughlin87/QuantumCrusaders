package com.technomicron.qc.ui.freelance;

		import android.content.Context;

		import android.os.AsyncTask;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;
		import android.widget.ArrayAdapter;
		import android.widget.Button;
		import android.widget.TextView;

		import androidx.navigation.NavController;
		import androidx.navigation.NavOptions;

		import com.technomicron.qc.App;
		import com.technomicron.qc.R;
		import com.technomicron.qc.activity.main.MainActivity;
		import com.technomicron.qc.api.APIFreelancer;
		import java.util.ArrayList;

public class Freelance_Adapter extends ArrayAdapter<Freelance_Model>{

	Context mContext;
	APIFreelancer api;
	Freelance_Model model;

	public static class ViewHolder {
		TextView username;
		TextView userID;
		TextView attack;
		TextView defense;
		TextView covertAttack;
		TextView covertDefense;
		TextView value;

		Button profileButton;
		Button dischargeButton;
	}

	public Freelance_Adapter(ArrayList<Freelance_Model> data, Context context) {
		super(context, R.layout.item_freelancer_owned, data);
		this.mContext = context;
		this.api = new APIFreelancer();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		this.model = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder;

		final View result;

		if (convertView == null) {

			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_freelancer_owned, parent, false);

			viewHolder.username = (TextView) convertView.findViewById(R.id.iFOUsername);
			viewHolder.userID = (TextView) convertView.findViewById(R.id.iFOUserID);
			viewHolder.attack = (TextView) convertView.findViewById(R.id.iFOAttack);
			viewHolder.defense = (TextView) convertView.findViewById(R.id.iFODefense);
			viewHolder.covertAttack = (TextView) convertView.findViewById(R.id.iFOCovertAttack);
			viewHolder.covertDefense = (TextView) convertView.findViewById(R.id.iFOCovertDefense);
			viewHolder.value = (TextView) convertView.findViewById(R.id.iFOValue);

			viewHolder.profileButton = (Button) convertView.findViewById(R.id.iFOProfileButton);
			viewHolder.dischargeButton = (Button) convertView.findViewById(R.id.iFODischargeButton);

			result=convertView;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			result=convertView;
		}

		viewHolder.username.setText(model.getUsername());
		viewHolder.userID.setText(model.getUserID());
		viewHolder.attack.setText(model.getAttack());
		viewHolder.defense.setText(model.getDefense());
		viewHolder.covertAttack.setText(model.getCovertAttack());
		viewHolder.covertDefense.setText(model.getCovertDefense());
		viewHolder.value.setText(model.getValue());

		final Freelance_Model pos = getItem(position);
		final ViewHolder fViewHolder = viewHolder;
		viewHolder.profileButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NavController navController = MainActivity.getNavController();
				NavOptions navOptions = MainActivity.getNavOptions();
				final int userID = Integer.parseInt(fViewHolder.userID.getText().toString());
				navController.navigate(R.id.action_navigation_freelance_to_navigation_profile, null, navOptions);

			}
		});
		viewHolder.dischargeButton.setOnClickListener(new View.OnClickListener() {
			final int userID = Integer.parseInt(fViewHolder.userID.getText().toString());
			public void onClick(View v) {
				new AsyncTask<Void, Void, Void>() {
					String mResponse;

					@Override
					protected Void doInBackground(Void... voids) {
						mResponse = api.dischargeFreelancer(userID);
						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						if (mResponse.matches("1001")) {
							App.getInstance().showToastMessage("Unknown Error.");
						} else if (mResponse.matches("1002")) {
							App.getInstance().showToastMessage("Upgrade your command center to own more freelancers.");
						} else if (mResponse.matches("1003")) {
							App.getInstance().showToastMessage("You're too broke!");
						} else if (mResponse.matches("1004")) {
							App.getInstance().showToastMessage("Not sure how you got this error tbh.");
						} else if (mResponse.matches("1005")) {
							App.getInstance().showToastMessage("You already own this freelancer.");
						} else {
							Freelance_Fragment.getInstance().removeListItem(pos);
						}
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});


		return result;
	}

}