package com.technomicron.qc.api;
		import android.content.Intent;
		import android.util.Log;

		import com.technomicron.qc.App;
		import com.technomicron.qc.api.pojo.CombatAttack;
		import com.technomicron.qc.api.pojo.GetAlertList;
		import com.technomicron.qc.api.pojo.GetCombatList;

		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.List;

		import retrofit2.Call;
		import retrofit2.Callback;
		import retrofit2.Response;
		import retrofit2.Retrofit;
		import retrofit2.converter.gson.GsonConverterFactory;

public class APIAlerts extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIAlerts.Java";

	public APIAlerts() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public void getAlertList () {
		final String tFunc = "getAlertList: ";
		Call<List<GetAlertList>> call = api.getAlertList(token);
		final Intent intent = new Intent("RECEIVED_ALERT_LIST");

		call.enqueue(new Callback<List<GetAlertList>>() {
			@Override
			public void onResponse(Call<List<GetAlertList>> call, Response<List<GetAlertList>> response) {
				if (!response.isSuccessful()) {
					Log.v(TAG, tFunc + "NOT SUCCEED" + response.toString());
					return;
				}

				Log.v(TAG, tFunc + "SUCCEED");
				List<GetAlertList> players = response.body();

				ArrayList<String> temp = new ArrayList<String>();
				for (GetAlertList player : players){
					temp.add(player.getAlert_id().toString());
					temp.add(player.getAlertType());
					temp.add(player.getUser_id().toString());
					temp.add(player.getUsername());
					temp.add(player.getUser_id2().toString());
					temp.add(player.getUsername2());
					temp.add(player.getResources().toString());
					intent.putStringArrayListExtra("alert", temp);
					Log.v(TAG, temp.toString());
					App.getAppContext().sendBroadcast(intent);
					temp.clear();
				}

			}

			@Override
			public void onFailure(Call<List<GetAlertList>> call, Throwable t) {
				Log.v(TAG, tFunc + "FAILED");
			}
		});
	}

}
