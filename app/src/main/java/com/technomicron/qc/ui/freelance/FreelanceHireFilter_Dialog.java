package com.technomicron.qc.ui.freelance;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.gamedata.Shared_Prefs;

public class FreelanceHireFilter_Dialog extends Dialog {

	public Activity activity;

	public FreelanceHireFilter_Dialog(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_freelancer_hire_filter);


		setButtonListeners();
	}

	private void setButtonListeners(){



	}

}