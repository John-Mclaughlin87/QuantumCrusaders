package com.technomicron.qc.ui.hangar.contracts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.technomicron.qc.R;
import com.technomicron.qc.activity.main.MainActivity;

public class Contracts_Fragment extends Fragment {

	private Contracts_Model model;

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		setDefaultUIState();
		model =
				ViewModelProviders.of(this).get(Contracts_Model.class);
		View root = inflater.inflate(R.layout.fragment_hangar_contracts, container, false);
		final TextView textView = root.findViewById(R.id.text_hangar_contracts);
		model.getText().observe(this, new Observer<String>() {
			@Override
			public void onChanged(@Nullable String s) {
				textView.setText(s);
			}
		});
		return root;
	}
	private void setDefaultUIState(){
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().chatPreviewCardView.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);
	}
}