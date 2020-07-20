
package com.technomicron.qc.ui.ordnance;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.api.APIOrdnance;

import java.util.ArrayList;

public class Ordnance_Adapter extends ArrayAdapter<Ordnance_Model> implements View.OnClickListener {
	Context mContext;
	APIOrdnance api;

	// View lookup cache
	private static class ViewHolder {
		TextView titleView;
		TextView priceView;
		TextView attack_boostView;
		TextView defence_boostView;
		TextView quantity_ownedView;
		ImageView ordnanceImage;
		EditText purchaseQuantityView;
	}

	public Ordnance_Adapter(ArrayList<Ordnance_Model> data, Context context) {
		super(context, R.layout.item_ordnance_list, data);
		this.mContext = context;
		this.api = new APIOrdnance();

	}

	@Override
	public void onClick(View v) {
	}

	//why am i here, why is this defined again later, we must know one day
	int lastPosition = -1;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Ordnance_Model qcOrdnanceViewModel = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder; // view lookup cache stored in tag

		if (convertView == null) {

			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_ordnance_list, parent, false);

			viewHolder.titleView = (TextView) convertView.findViewById(R.id.ordnance_title);
			viewHolder.priceView = (TextView) convertView.findViewById(R.id.ordnance_price);
			viewHolder.attack_boostView = (TextView) convertView.findViewById(R.id.ordnance_attack_boost_value);
			viewHolder.defence_boostView = (TextView) convertView.findViewById(R.id.ordnance_defence_boost_value);
			viewHolder.quantity_ownedView = (TextView) convertView.findViewById(R.id.ordnance_quantity_owned_value);
			viewHolder.ordnanceImage = (ImageView) convertView.findViewById(R.id.ordnance_image);
			viewHolder.purchaseQuantityView = (EditText) convertView.findViewById(R.id.ordnance_purchase_quantity);


			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}


		String titleText = qcOrdnanceViewModel.getTitle();
		String priceValue = qcOrdnanceViewModel.getPrice();
		String attack_boostValue = qcOrdnanceViewModel.getAttack_boost();
		String defence_boostValue = qcOrdnanceViewModel.getDefence_boost();
		String quantity_owned_value = qcOrdnanceViewModel.getQuantity_owned();
		//this is where we can Span the text and do cool stuff like change text colors
		//SpannableString senderSpanText = new SpannableString(senderPlainText);
		//ForegroundColorSpan foregroundSpan2 = new ForegroundColorSpan(Color.WHITE);
		//senderSpanText.setSpan(foregroundSpan2, 0, senderSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		//SpannableString messageSpanText = new SpannableString(messagePlainText);
		final ViewHolder vHolder = viewHolder;
		final Button ordnance_purchase_button = (Button) convertView.findViewById(R.id.ordnance_purchase_button);
		ordnance_purchase_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (vHolder.purchaseQuantityView.getText().toString().matches("")) {
					App.getInstance().showToastMessage("Quantity Required");
				} else {

					final Integer purchaseQuantity = Integer.parseInt(vHolder.purchaseQuantityView.getText().toString());
					final int pos = position;

					new AsyncTask<Void, Void, Void>() {
						String mResponse;

						@Override
						protected Void doInBackground(Void... voids) {
							mResponse = api.playerOrdnancePurchase(purchaseQuantity, pos + 1);
							return null;
						}

						@Override
						protected void onPostExecute(Void aVoid) {
							vHolder.purchaseQuantityView.setText(null);
							if (mResponse.matches("1001")) {
								App.getInstance().showToastMessage("Purchase quantity exceeds 255 max");
							} else if (mResponse.matches("1002")) {
								App.getInstance().showToastMessage("You're too broke");
							} else if (mResponse.matches("1003")) {
								App.getInstance().showToastMessage("Unknown Error");
							} else {
								Ordnance_Fragment.getInstance().updateOrdnanceDetails();
							}
							api.getPlayerDetails();
							super.onPostExecute(aVoid);
						}
					}.execute();
				}
			}
		});

		viewHolder.titleView.setText(titleText);
		viewHolder.priceView.setText(priceValue);
		viewHolder.attack_boostView.setText(attack_boostValue);
		viewHolder.defence_boostView.setText(defence_boostValue);
		viewHolder.quantity_ownedView.setText(quantity_owned_value);
		viewHolder.ordnanceImage.setImageResource(R.drawable.rocket_bomb);
		// Return the completed view to render on screen
		return convertView;
	}
}
