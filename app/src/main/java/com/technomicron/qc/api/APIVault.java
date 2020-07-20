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

public class APIVault extends APIMethods {

	static API api;
	Retrofit retrofit;
	static String token;
	String TAG = "APIAlerts.Java";

	public APIVault() {
		super();
		this.token = App.getToken();
		this.retrofit = new Retrofit.Builder()
				.baseUrl("https://api.technomicron.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		this.api = retrofit.create(API.class);
	}

	public String depositToVault(int quantity) {
		final String tFunc = "depositToVault: ";
		String rString = "";
		PostVault vD = new PostVault(token, quantity);
		Call<PostVault> call = api.vaultDeposit(vD);
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

	public String withdrawFromVault(int quantity) {
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

	public String getVaultBalance() {
		final String tFunc = "getVaultBalance: ";
		Call<GetVaultBalance> call = api.getVaultBalance(token);
		String content = "";
		try {
			Response<GetVaultBalance> response = call.execute();
			if (!response.isSuccessful()) {
				Log.v(TAG, tFunc + "NOT SUCCEED");
				//return response.errorBody().string();
			} else {
				Log.v(TAG, tFunc + "SUCCEEDED");
				GetVaultBalance balance = response.body();
				content = balance.getBalance().toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.v(TAG, tFunc + e.toString());
		}
		return content;
	}
}
