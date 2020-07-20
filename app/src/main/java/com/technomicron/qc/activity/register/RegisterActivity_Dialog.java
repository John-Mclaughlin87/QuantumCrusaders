package com.technomicron.qc.activity.register;

		import android.app.Activity;
		import android.app.Dialog;
		import android.os.Bundle;
		import android.view.View;
		import android.view.Window;
		import android.widget.ImageButton;

		import com.technomicron.qc.App;
		import com.technomicron.qc.R;
		import com.technomicron.qc.gamedata.Shared_Prefs;

public class RegisterActivity_Dialog extends Dialog {

	public Activity activity;
	ImageButton atkButton;
	ImageButton blncButton;
	ImageButton dfnsButton;

	public RegisterActivity_Dialog(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_register);

		this.atkButton = findViewById(R.id.atkButtonRegister);
		this.blncButton = findViewById(R.id.blncButtonRegister);
		this.dfnsButton = findViewById(R.id.dfnsButtonRegister);

		setButtonListeners();
	}

	private void setButtonListeners(){

		atkButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Shared_Prefs.setInt(App.getAppContext(), "playstyle", 1);
				dismiss();

			}
		});

		blncButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Shared_Prefs.setInt(App.getAppContext(), "playstyle", 4);
				dismiss();

			}
		});

		dfnsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Shared_Prefs.setInt(App.getAppContext(), "playstyle", 7);
				dismiss();

			}
		});

	}

}