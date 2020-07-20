package com.technomicron.qc.api;

import android.content.Intent;
import android.util.Log;

import com.technomicron.qc.App;
import com.technomicron.qc.api.pojo.GetOrdnance;
import com.technomicron.qc.api.pojo.PostBuildingPurchase;
import com.technomicron.qc.api.pojo.PostBuildingSell;
import com.technomicron.qc.api.pojo.PostBuildingUpgrade;
import com.technomicron.qc.api.pojo.PostOrdnance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIOrdnance extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIOrdnance.Java";

	public APIOrdnance() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public void getPlayerOrdnance() {
		final String tFunc = "getPlayerOrdnance: ";
		Call<List<GetOrdnance>> call = api.getPlayerOrdnance(token);
		final Intent intent = new Intent("RECEIVED_USER_ORDNANCE");

		call.enqueue(new Callback<List<GetOrdnance>>() {
			private int counter = 0;
			@Override
			public void onResponse(Call<List<GetOrdnance>> call, Response<List<GetOrdnance>> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED");
					return;
				}

				Log.v(TAG, tFunc + "SUCCEED");
				List<GetOrdnance> ordnances = response.body();



				for (GetOrdnance ordnance : ordnances)
				{
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(ordnance.getTitle());
					temp.add(ordnance.getPrice().toString());
					temp.add(ordnance.getAttackBoost().toString());
					temp.add(ordnance.getDefenseBoost().toString());
					temp.add(ordnance.getOrdnanceQuantity().toString());
					intent.putStringArrayListExtra("ordnance" + counter, temp);
					counter++;
				}

				App.getAppContext().sendBroadcast(intent);
			}

			@Override
			public void onFailure(Call<List<GetOrdnance>> call, Throwable t) {

				Log.v(TAG, tFunc + t.toString());
			}
		});

	}

	public String playerOrdnancePurchase(int quantity, int oid) {
		final String tFunc = "playerOrdnancePurchase: ";
		String rString = "";
		PostOrdnance pOP = new PostOrdnance(token, oid, quantity);
		Call<PostOrdnance> call = api.playerOrdnancePurchase(pOP);
		try {

			Response<PostOrdnance> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
			}
			if (response.isSuccessful()) {
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + fString);
		return fString;
	}

}
