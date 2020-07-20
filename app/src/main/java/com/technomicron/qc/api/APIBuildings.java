package com.technomicron.qc.api;

import android.util.Log;

import com.technomicron.qc.App;
import com.technomicron.qc.api.pojo.GetPlayerBuildings;
import com.technomicron.qc.api.pojo.PostBuildingPurchase;
import com.technomicron.qc.api.pojo.PostBuildingSell;
import com.technomicron.qc.api.pojo.PostBuildingUpgrade;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIBuildings extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIBuildings.Java";

	public APIBuildings() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public void getPlayerBuildings() {
		final String tFunc = "getPlayerBuildings: ";
		Call<GetPlayerBuildings> call = api.getPlayerBuildings(token);

		try {
			Response<GetPlayerBuildings> response = call.execute();

			if (!response.isSuccessful()) {
				Log.v(TAG, tFunc + "NOT SUCCEED");

			} else {
				Log.v(TAG, tFunc + "SUCCEEDED");
				GetPlayerBuildings building = response.body();
				Shared_Prefs.setInt(App.getAppContext(), "COMMAND", building.getCommand());
				Shared_Prefs.setInt(App.getAppContext(), "F1", building.getF1());
				Shared_Prefs.setInt(App.getAppContext(), "F2", building.getF2());
				Shared_Prefs.setInt(App.getAppContext(), "F3", building.getF3());
				Shared_Prefs.setInt(App.getAppContext(), "F4", building.getF4());
				Shared_Prefs.setInt(App.getAppContext(), "F5", building.getF5());
				Shared_Prefs.setInt(App.getAppContext(), "F6", building.getF6());
				Shared_Prefs.setInt(App.getAppContext(), "F7", building.getF7());
				Shared_Prefs.setInt(App.getAppContext(), "F8", building.getF8());
				Shared_Prefs.setInt(App.getAppContext(), "F9", building.getF9());
				Shared_Prefs.setInt(App.getAppContext(), "F10", building.getF10());
				Shared_Prefs.setInt(App.getAppContext(), "F11", building.getF11());
				Shared_Prefs.setInt(App.getAppContext(), "F12", building.getF12());
				Shared_Prefs.setInt(App.getAppContext(), "F13", building.getF13());
				Shared_Prefs.setInt(App.getAppContext(), "F14", building.getF14());
				Shared_Prefs.setInt(App.getAppContext(), "F15", building.getF15());
				Shared_Prefs.setInt(App.getAppContext(), "F16", building.getF16());
				Shared_Prefs.setInt(App.getAppContext(), "F17", building.getF17());
				Shared_Prefs.setInt(App.getAppContext(), "F18", building.getF18());
				Shared_Prefs.setInt(App.getAppContext(), "F19", building.getF19());
				Shared_Prefs.setInt(App.getAppContext(), "F20", building.getF20());
				Shared_Prefs.setInt(App.getAppContext(), "F21", building.getF21());
				Shared_Prefs.setInt(App.getAppContext(), "F22", building.getF22());
				Shared_Prefs.setInt(App.getAppContext(), "F23", building.getF23());
				Shared_Prefs.setInt(App.getAppContext(), "F24", building.getF24());
			}
		} catch (IOException e) {
			Log.v(TAG, tFunc + e.toString());
			e.printStackTrace();
		}
	}

	public String playerBuildingUpgrade (String buildingLocation) {
		final String tFunc = "playerBuildingUpgrade: ";
		String rString = "";
		String tmpPBS = buildingLocation.toLowerCase();
		PostBuildingUpgrade pBU;
		if(tmpPBS.matches("command")){
			pBU = new PostBuildingUpgrade(token, tmpPBS);

			Log.v(TAG, tFunc + tmpPBS);
		} else {
			StringBuilder sb = new StringBuilder(tmpPBS);
			sb.insert(1, '_');
			Log.v(TAG, tFunc + sb.toString());
			pBU = new PostBuildingUpgrade(token, sb.toString());
		}
		Call<PostBuildingUpgrade> call = api.playerBuildingUpgrade(pBU);
		try {

			Response<PostBuildingUpgrade> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
			}
			if (response.isSuccessful()) {

				rString = "Upgrade Successful";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + fString);
		return rString;
	}
	public String playerBuildingSell (String buildingLocation) {
		final String tFunc = "playerBuildingSell: ";
		String rString = "";
		String tmpPBS = buildingLocation.toLowerCase();
		PostBuildingSell pBS;
		if(tmpPBS.matches("command")){
			pBS = new PostBuildingSell(token, tmpPBS);

			Log.v(TAG, tFunc + tmpPBS);
		} else {
			StringBuilder sb = new StringBuilder(tmpPBS);
			sb.insert(1, '_');
			Log.v(TAG, tFunc + sb.toString());
			pBS = new PostBuildingSell(token, sb.toString());
		}
		Call<PostBuildingSell> call = api.playerBuildingSell(pBS);
		try {

			Response<PostBuildingSell> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
			}
			if (response.isSuccessful()) {

				rString = "Sale Successful";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + fString);
		return rString;
	}

	public String playerBuildingPurchase (String buildingLocation, int buildingNumber) {
		final String tFunc = "playerBuildingPurchase: ";
		String rString = "";
		String tmpPBS = buildingLocation.toLowerCase();
		PostBuildingPurchase pBP;
		if(tmpPBS.matches("command")){
			pBP = new PostBuildingPurchase(token, tmpPBS, buildingNumber);

			Log.v(TAG, tFunc + tmpPBS);
		} else {
			StringBuilder sb = new StringBuilder(tmpPBS);
			sb.insert(1, '_');
			Log.v(TAG, tFunc + sb.toString());
			pBP = new PostBuildingPurchase(token, sb.toString(), buildingNumber);
		}
		Call<PostBuildingPurchase> call = api.playerBuildingPurchase(pBP);
		try {

			Response<PostBuildingPurchase> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
			}
			if (response.isSuccessful()) {

				rString = "Purchase Successful";
			}

		} catch (IOException e) {
			Log.v(TAG, tFunc + e.toString());
		}
		String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + fString);
		return rString;
	}
}
