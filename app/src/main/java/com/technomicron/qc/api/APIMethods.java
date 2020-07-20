package com.technomicron.qc.api;

import android.content.Intent;
import android.util.Log;

import com.technomicron.qc.App;
import com.technomicron.qc.api.pojo.GetCombatList;
import com.technomicron.qc.api.pojo.PostFcmID;
import com.technomicron.qc.api.pojo.PostPlayer;
import com.technomicron.qc.api.pojo.GetPlayerDetails;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIMethods.Java";


	public APIMethods() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}


	public void createPlayer(String qcNewUserUsername, String qcNewUserEmail) {
		final String tFunc = "createPlayer: ";
		int playstyle = Shared_Prefs.getInt(App.getAppContext(), "playstyle");
		String fcmID = Shared_Prefs.getString(App.getAppContext(), "fcmID");
		PostPlayer postPlayer = new PostPlayer(qcNewUserUsername, qcNewUserEmail, token, playstyle, fcmID);
		Call<PostPlayer> call = api.createPlayer(postPlayer);
		try {
			Response<PostPlayer> response = call.execute();
			if (!response.isSuccessful()) {
				Log.v(TAG, tFunc + response.errorBody().string());
				Shared_Prefs.removePref(App.getAppContext(), "user_exists");
				Shared_Prefs.removePref(App.getAppContext(), "username");
				return;
			}
			if (response.isSuccessful()) {
				Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", true);
				Shared_Prefs.setString(App.getAppContext(), "username", qcNewUserUsername);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateFcmID(String fcmID) {
		final String tFunc = "updateFcmID: ";
		PostFcmID data = new PostFcmID(fcmID, token);
		Call<PostFcmID> call = api.updateFcmID(data);
		call.enqueue(new Callback<PostFcmID>() {
			@Override
			public void onResponse(Call<PostFcmID> call, Response<PostFcmID> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED");
					Log.v(TAG, tFunc + "NOT SUCCEED" + ": " + Integer.toString(response.code()));

				}
				if (response.isSuccessful()) {
					PostFcmID postFcmID = response.body();
					Log.v(TAG, tFunc + "SUCCEEDED" + ": " + postFcmID);

				}
			}

			@Override
			public void onFailure(Call<PostFcmID> call, Throwable t) {
				Log.v(TAG, tFunc +  t.toString());
			}
		});
	}

	public void getPlayer() {
		final String tFunc = "getPlayer: ";
		Call<PostPlayer> call = api.getPlayer(token);
		call.enqueue(new Callback<PostPlayer>() {
			@Override
			public void onResponse(Call<PostPlayer> call, Response<PostPlayer> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED");
					Log.v(TAG, tFunc + Integer.toString(response.code()));
					Shared_Prefs.removeAllPrefs(App.getAppContext());
					Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", false);

				}
				if (response.isSuccessful()) {
					Log.v(TAG, tFunc + "SUCCEEDED");
					PostPlayer postPlayer = response.body();
					Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", true);
					Shared_Prefs.setString(App.getAppContext(), "username", postPlayer.getUsername());

				}
			}

			@Override
			public void onFailure(Call<PostPlayer> call, Throwable t) {
				Log.v(TAG, tFunc +  t.toString());
				Shared_Prefs.removeAllPrefs(App.getAppContext());
				Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", false);
			}
		});
	}

	public void getPlayerDetails() {
		final String tFunc = "getPlayerDetails: ";
		Call<GetPlayerDetails> call = api.getPlayerDetails(token);
		call.enqueue(new Callback<GetPlayerDetails>() {
			@Override
			public void onResponse(Call<GetPlayerDetails> call, Response<GetPlayerDetails> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED");
					return;
				} else {
					Log.v(TAG, tFunc + "SUCCEEDED");
					GetPlayerDetails getPlayerDetails = response.body();
					Intent intent = new Intent("RECEIVED_USER_DETAILS");

					String igc = formatStringComma(getPlayerDetails.getIgc());
					String attack_power = formatStringComma(getPlayerDetails.getAttack_power());
					String defense_power = formatStringComma(getPlayerDetails.getDefense_power());
					String covert_attack_power = formatStringComma(getPlayerDetails.getCovert_attack_power());
					String covert_defense_power = formatStringComma(getPlayerDetails.getCovert_defense_power());
					String freelancer_power = formatStringComma(getPlayerDetails.getFreelancer_power());
					String freelancer_defense = formatStringComma(getPlayerDetails.getFreelancer_defense());
					String freelancer_covert_attack = formatStringComma(getPlayerDetails.getFreelancer_covert_attack());
					String freelancer_covert_defense = formatStringComma(getPlayerDetails.getFreelancer_covert_defense());
					String freelancerValue = formatStringComma(getPlayerDetails.getFreelancersValue());
					String hireCost = formatStringComma(getPlayerDetails.getHireCost());

					intent.putExtra("igc", igc);
					intent.putExtra("pigc", getPlayerDetails.getPigc().toString());
					intent.putExtra("cigc", getPlayerDetails.getCigc().toString());
					intent.putExtra("attack_power", attack_power);
					intent.putExtra("defense_power", defense_power);
					intent.putExtra("covert_attack_power", covert_attack_power);
					intent.putExtra("covert_defense_power", covert_defense_power);
					intent.putExtra("freelancer", getPlayerDetails.getFreelancers().toString());
					intent.putExtra("freelancerMax", getPlayerDetails.getFreelancersMax().toString());
					intent.putExtra("freelancer_power", freelancer_power);
					intent.putExtra("freelancer_defense", freelancer_defense);
					intent.putExtra("freelancer_covert_attack", freelancer_covert_attack);
					intent.putExtra("freelancer_covert_defense", freelancer_covert_defense);
					intent.putExtra("freelancerValue", freelancerValue);
					intent.putExtra("fleetC", getPlayerDetails.getFleetC().toString());
					intent.putExtra("fleetT", getPlayerDetails.getFleetT().toString());
					intent.putExtra("roguesC", getPlayerDetails.getRoguesC().toString());
					intent.putExtra("roguesT", getPlayerDetails.getRoguesT().toString());
					intent.putExtra("orgName", getPlayerDetails.getOrgName());
					intent.putExtra("owner", getPlayerDetails.getOwner().toString());
					intent.putExtra("hireCost", hireCost);
					App.getAppContext().sendBroadcast(intent);
					if(getPlayerDetails.getFreelancers() > 0){
						Shared_Prefs.setBoolean(App.getAppContext(), "freelancers_owned", true);
					} else {
						Shared_Prefs.setBoolean(App.getAppContext(), "freelancers_owned", false);
					}
				}

			}

			@Override
			public void onFailure(Call<GetPlayerDetails> call, Throwable t) {

				Log.v(TAG, tFunc + t.toString());
			}
		});
	}

	public String getPlayerSync() {
		final String tFunc = "getPlayerSync: ";
		Call<PostPlayer> call = api.getPlayerSync(token);
		String content = "";
		try {
			Response<PostPlayer> response = call.execute();
			if (!response.isSuccessful()) {
				Log.v(TAG, tFunc + "NOT SUCCEED");
				Shared_Prefs.removePref(App.getAppContext(), "username");
				Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", false);
				return response.errorBody().string();
			} else {
				Log.v(TAG, tFunc + "SUCCEEDED");
				PostPlayer postPlayer = response.body();
				content += "USERNAME: " + postPlayer.getUsername();
				content += "EMAIL: " + postPlayer.getEmail();
				content += "TOKEN: " + postPlayer.getToken();
				Shared_Prefs.setBoolean(App.getAppContext(), "user_exists", true);
				Shared_Prefs.setString(App.getAppContext(), "username", postPlayer.getUsername());
				return content;
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.v(TAG, tFunc + e.toString());
		}

		return content;
	}

	public void getCombatList() {
		final String tFunc = "getCombatList: ";
		Call<List<GetCombatList>> call = api.getCombatList(token);
		final Intent intent = new Intent("RECEIVED_COMBAT_LIST");

		call.enqueue(new Callback<List<GetCombatList>>() {
			@Override
			public void onResponse(Call<List<GetCombatList>> call, Response<List<GetCombatList>> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED");
					return;
				}

				Log.v(TAG, tFunc + "SUCCEED");
				List<GetCombatList> players = response.body();

				ArrayList<String> temp = new ArrayList<String>();
				for (GetCombatList player : players){
					temp.add(player.getUsername());
					temp.add(player.getUserID().toString());
					temp.add(player.getOrgName());
					temp.add(player.getRiskFactor().toString());
					intent.putStringArrayListExtra("player", temp);
					Log.v(TAG, temp.toString());
					App.getAppContext().sendBroadcast(intent);
					temp.clear();
				}

			}

			@Override
			public void onFailure(Call<List<GetCombatList>> call, Throwable t) {
				Log.v(TAG, tFunc + "FAILED");
			}
		});
	}

	protected String formatStringComma(int i){
		String fString = String.format("%,d", i);
		return fString;
	}
}
