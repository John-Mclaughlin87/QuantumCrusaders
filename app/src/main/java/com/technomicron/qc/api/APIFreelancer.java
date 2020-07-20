package com.technomicron.qc.api;

		import android.content.Intent;
		import android.util.Log;

		import com.technomicron.qc.App;
		import com.technomicron.qc.api.pojo.GetFreelancerList;
		import com.technomicron.qc.api.pojo.HireFreelancer;

		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.List;

		import retrofit2.Call;
		import retrofit2.Callback;
		import retrofit2.Response;
		import retrofit2.Retrofit;
		import retrofit2.converter.gson.GsonConverterFactory;

public class APIFreelancer extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIFreelancer.Java";

	public APIFreelancer() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public void getFreelancerList() {
		final String tFunc = "getFreelancerList: ";
		Call<List<GetFreelancerList>> call = api.getFreelancerList(token);
		final Intent intent = new Intent("RECEIVED_OWN_FREELANCERS");

		call.enqueue(new Callback<List<GetFreelancerList>>() {
			@Override
			public void onResponse(Call<List<GetFreelancerList>> call, Response<List<GetFreelancerList>> response) {
				if (!response.isSuccessful()) {
					try {
						Log.v(TAG, tFunc + "NOT SUCCEED" + response.errorBody().string());
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}

				Log.v(TAG, tFunc + "SUCCEED");
				List<GetFreelancerList> players = response.body();


				ArrayList<String> temp = new ArrayList<String>();
				for (GetFreelancerList player : players)
				{
					String attack = formatStringComma(player.getAttack());
					String defense = formatStringComma(player.getDefense());
					String cAttack = formatStringComma(player.getCovertAttack());
					String cDefense = formatStringComma(player.getCovertDefense());
					String value = formatStringComma(player.getValue());

					temp.add(player.getUsername());
					temp.add(player.getUserID().toString());
					temp.add(attack);
					temp.add(defense);
					temp.add(cAttack);
					temp.add(cDefense);
					temp.add(value);
					intent.putStringArrayListExtra("freelancers_owned", temp);
					//Log.v(TAG, temp.toString());
					App.getAppContext().sendBroadcast(intent);
					temp.clear();
				}
			}

			@Override
			public void onFailure(Call<List<GetFreelancerList>> call, Throwable t) {

				Log.v(TAG, tFunc + "FAILED");
			}
		});

	}

	public void getHireableFreelancerList() {
		final String tFunc = "getHireableFreelancerList";
		Call<List<GetFreelancerList>> call = api.getHireableFreelancerList(token);
		final Intent intent = new Intent("RECEIVED_HIREABLE_FREELANCERS");

		call.enqueue(new Callback<List<GetFreelancerList>>() {
			@Override
			public void onResponse(Call<List<GetFreelancerList>> call, Response<List<GetFreelancerList>> response) {
				if (!response.isSuccessful()) {
					try {
						Log.v(TAG, tFunc + "NOT SUCCEED" + response.errorBody().string());
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}

				Log.v(TAG, tFunc + "SUCCEED");
				List<GetFreelancerList> players = response.body();

				ArrayList<String> temp = new ArrayList<String>();
				for (GetFreelancerList player : players)
				{
					String attack = formatStringComma(player.getAttack());
					String defense = formatStringComma(player.getDefense());
					String cAttack = formatStringComma(player.getCovertAttack());
					String cDefense = formatStringComma(player.getCovertDefense());
					String value = formatStringComma(player.getValue());

					temp.add(player.getUsername());
					temp.add(player.getUserID().toString());
					temp.add(attack);
					temp.add(defense);
					temp.add(cAttack);
					temp.add(cDefense);
					temp.add(value);
					intent.putStringArrayListExtra("freelancers_hireable", temp);
					//Log.v(TAG, temp.toString());
					App.getAppContext().sendBroadcast(intent);
					temp.clear();
				}
			}

			@Override
			public void onFailure(Call<List<GetFreelancerList>> call, Throwable t) {
				Log.v(TAG, tFunc + "FAILED");
			}
		});

	}

	public String HireFreelancer(int userID) {
		final String tFunc = "hireFreelancer: ";
		String rString = "";
		HireFreelancer hF = new HireFreelancer(token, userID);
		Call<HireFreelancer> call = api.HireFreelancer(hF);
		try {

			Response<HireFreelancer> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
				Log.v(TAG, tFunc + rString);
			}
			if (response.isSuccessful()) {
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + rString);
		return rString;
	}

	public String dischargeFreelancer(int userID) {
		final String tFunc = "hireFreelancer: ";
		String rString = "";
		HireFreelancer hF = new HireFreelancer(token, userID);
		Call<HireFreelancer> call = api.dischargeFreelancer(hF);
		try {

			Response<HireFreelancer> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
				Log.v(TAG, tFunc + rString);
			}
			if (response.isSuccessful()) {
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//String fString = rString.replaceAll("[^0-9]","");
		Log.v(TAG, tFunc + rString);
		return rString;
	}

}
