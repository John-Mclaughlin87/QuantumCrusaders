package com.technomicron.qc.api;
		import android.util.Log;

		import com.technomicron.qc.App;
		import com.technomicron.qc.api.pojo.GetVaultBalance;
		import com.technomicron.qc.api.pojo.PostVault;

		import java.io.IOException;

		import retrofit2.Call;
		import retrofit2.Response;
		import retrofit2.Retrofit;
		import retrofit2.converter.gson.GsonConverterFactory;

public class APIProfile extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIAlerts.Java";

	public APIProfile() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public String getUserProfile(int quantity) {
		final String tFunc = "withdrawFromVault: ";
		String rString = "";
		PostVault vD = new PostVault(token, quantity);
		Call<PostVault> call = api.vaultWithdraw(vD);
		try {

			Response<PostVault> response = call.execute();
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
