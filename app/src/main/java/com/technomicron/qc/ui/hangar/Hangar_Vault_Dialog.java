package com.technomicron.qc.ui.hangar;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIVault;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Hangar_Vault_Dialog extends Dialog {

	private static String TAG = "Hangar_Vault_Dialog.Java";
	public Activity activity;
	private static Button withdrawButton;
	private static Button depositButton;
	private static TextView vaultBalance;
	private APIVault api;


	public Hangar_Vault_Dialog(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_vault);
		this.withdrawButton = findViewById(R.id.dVWithdrawButton);
		this.depositButton = findViewById(R.id.dVDepositButton);
		this.vaultBalance = findViewById(R.id.dVVaultBalance);
		this.api = new APIVault();

		final EditText editText = findViewById(R.id.dVEditText);

		editText.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void afterTextChanged(Editable s) {

				editText.removeTextChangedListener(this);

				try {
					String originalString = s.toString();

					Long i;
					if (originalString.contains(",")) {
						originalString = originalString.replaceAll(",", "");
					}
					i = Long.parseLong(originalString);

					String formattedString = String.format(Locale.US,"%,d", i);

					//setting text after format to EditText
					editText.setText(formattedString);
					editText.setSelection(editText.getText().length());
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}

				editText.addTextChangedListener(this);
			}
		});

		setButtonListeners();
		updateVaultBalance();
	}

	private void setButtonListeners(){
		withdrawButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final int resources = getEditTextToInt();
				new AsyncTask<Void, Void, Void>() {
					String rString = "";


					@Override
					protected Void doInBackground(Void... voids) {
						rString = api.withdrawFromVault(resources);
						Log.v(TAG, TAG );

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						api.getPlayerDetails();
						updateVaultBalance();
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});
		depositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final int resources = getEditTextToInt();
				new AsyncTask<Void, Void, Void>() {
					String rString = "";

					@Override
					protected Void doInBackground(Void... voids) {

						rString = api.depositToVault(resources);
						Log.v(TAG, TAG );

						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid) {
						api.getPlayerDetails();
						updateVaultBalance();
						super.onPostExecute(aVoid);
					}
				}.execute();
			}
		});
	}

	private int getEditTextToInt(){
		EditText editText = findViewById(R.id.dVEditText);
		String editTextText = editText.getText().toString();
		String plainEditTextText = editTextText.replace(",", "");
		int editTextValue = 0;
		if(!editTextText.matches("")){
			editTextValue = Integer.parseInt(plainEditTextText);
		}
		return editTextValue;
	}

	private void updateVaultBalance(){
		new AsyncTask<Void, Void, Void>() {
			String rString = "";

			@Override
			protected Void doInBackground(Void... voids) {

				rString = api.getVaultBalance();

				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {

				int i = Integer.parseInt(rString);
				rString = String.format("%,d", i);
				vaultBalance.setText(rString);
				super.onPostExecute(aVoid);
			}
		}.execute();
	}
}