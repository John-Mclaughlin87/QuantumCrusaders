package com.technomicron.qc.api;

		import android.util.Log;

		import com.technomicron.qc.App;
		import com.technomicron.qc.api.pojo.CombatAttack;

		import java.io.IOException;

		import retrofit2.Call;
		import retrofit2.Response;
		import retrofit2.Retrofit;
		import retrofit2.converter.gson.GsonConverterFactory;

public class APICombat extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APICombat.Java";

	public APICombat() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public String CombatAttack (int userid) {
		final String tFunc = "CombatAttack: ";
		CombatAttack cA = new CombatAttack(token, userid);
		Call<CombatAttack> call = api.CombatAttack(cA);
		String rString = "";
		try {

			Response<CombatAttack> response = call.execute();
			if (!response.isSuccessful()) {
				rString = response.errorBody().string();
			}
			if (response.isSuccessful()) {

				rString = response.message();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.v(TAG, tFunc + rString);
		return rString;
	}

}
