package com.technomicron.qc.activity.splash;

		import android.content.Context;
		import android.content.Intent;
		import android.os.AsyncTask;
		import android.os.Bundle;
		import android.util.Log;

		import androidx.annotation.NonNull;
		import androidx.appcompat.app.AppCompatActivity;

		import com.google.android.gms.tasks.OnCompleteListener;
		import com.google.android.gms.tasks.Task;
		import com.google.firebase.iid.FirebaseInstanceId;
		import com.google.firebase.iid.InstanceIdResult;
		import com.technomicron.qc.App;
		import com.technomicron.qc.activity.main.MainActivity;
		import com.technomicron.qc.activity.register.RegisterActivity;
		import com.technomicron.qc.api.APIMethods;
		import com.technomicron.qc.gamedata.Shared_Prefs;

public class SplashActivity extends AppCompatActivity {

	APIMethods apiMethods;
	Context appContext = App.getAppContext();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.apiMethods = new APIMethods();

		new  AsyncTask<Void, Void, Void>() {
			String returnString;
			@Override
			protected Void doInBackground( Void... voids ) {
				Shared_Prefs.setBoolean(appContext, "user_exists", false);
				this.returnString = apiMethods.getPlayerSync();
				if(Shared_Prefs.containsPref(appContext, "user_exists")){
					if(Shared_Prefs.getBoolean(appContext,"user_exists") == true){
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
						finish();
					} else {
						startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
						finish();
					}
				} else {
					startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
					finish();
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
				getFcmID();
			}
		}.execute();

		super.onCreate(savedInstanceState);
	}

	private void getFcmID(){
		FirebaseInstanceId.getInstance().getInstanceId()
				.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
					@Override
					public void onComplete(@NonNull Task<InstanceIdResult> task) {
						if (!task.isSuccessful()) {
							Log.w("getInstanceId failed", "getInstanceId failed", task.getException());
							return;
						}

						// Get new Instance ID token
						String fcmID = task.getResult().getToken();
						apiMethods.updateFcmID(fcmID);
						Shared_Prefs.setString(App.getAppContext(), "fcmID", fcmID);
						// Log and toast
						Log.d("getInstanceId succeeded", fcmID);

					}
				});

	}
}
