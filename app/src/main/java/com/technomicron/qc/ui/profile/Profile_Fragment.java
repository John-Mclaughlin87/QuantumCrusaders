package com.technomicron.qc.ui.profile;

		import android.os.Bundle;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;

		import androidx.annotation.NonNull;
		import androidx.fragment.app.Fragment;

		import com.technomicron.qc.activity.main.MainActivity;
		import com.technomicron.qc.R;

public class Profile_Fragment extends Fragment {

	private Profile_Model model;

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		setDefaultUIState();
		//model = ViewModelProviders.of(this).get(Profile_Model.class);
		View root = inflater.inflate(R.layout.fragment_profile, container, false);
		//final TextView textView = root.findViewById(R.id.text_profile);
		//model.getText().observe(this, new Observer<String>() {
		//	@Override
		//	public void onChanged(@Nullable String s) {
		//		textView.setText(s);
		//	}
		//});
		return root;
	}
	private void setDefaultUIState(){
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().chatPreviewCardView.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.GONE);
	}
}