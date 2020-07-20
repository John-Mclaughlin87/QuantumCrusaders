package com.technomicron.qc.activity.register;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.gamedata.Shared_Prefs;

public class RegisterActivity extends Activity {
	APIMethods apiMethods;
	SharedPreferences qcShPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.qcShPrefs = getApplicationContext().getSharedPreferences("qcShPrefs", 0); // 0 - for private mode
		// Start home activity
		this.apiMethods = new APIMethods();
		setContentView(R.layout.activity_register);
		openDialog();
		final EditText qcNewUserUsername = (EditText) findViewById(R.id.qcNewUserUsername);
		final EditText qcNewUserEmail = (EditText) findViewById(R.id.qcNewUserEmail);

		Button qcNewUserCreateAcctBtn = (Button) findViewById(R.id.qcNewUserCreateAcctBtn);
		qcNewUserCreateAcctBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final String newUserName = qcNewUserUsername.getText().toString();
				final String newUserEmail = qcNewUserEmail.getText().toString();

				new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground( Void... voids ) {

						apiMethods.createPlayer(newUserName, newUserEmail);

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						checkIfWeCanSwitchActivity();
					}
				}.execute();
			}
		});

	}

	private void checkIfWeCanSwitchActivity(){

		if(Shared_Prefs.containsPref(App.getAppContext(), "user_exists")){
			if(Shared_Prefs.getBoolean(App.getAppContext(),"user_exists") == true){
				startActivity(new Intent(RegisterActivity.this, MainActivity.class));
				finish();
			} else {
				Toast.makeText(App.getAppContext(), "Username or Email Exists", Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(App.getAppContext(), "Username and Email Required", Toast.LENGTH_LONG).show();
		}

	}

	private void openDialog() {
		RegisterActivity_Dialog dialog = new RegisterActivity_Dialog(this);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
}
