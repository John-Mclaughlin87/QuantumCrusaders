package com.technomicron.qc.ui.hangar.fleetyard;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.api.APIBuildings;
import com.technomicron.qc.gamedata.Shared_Prefs;

import java.util.ArrayList;

public class Hangar_Fragment_Fleet extends Fragment implements DialogInterface.OnDismissListener {

	public static Hangar_Fragment_Fleet instance;
	View view;
	ImageButton COMMAND;
	ImageButton F1;
	ImageButton F2;
	ImageButton F3;
	ImageButton F4;
	ImageButton F5;
	ImageButton F6;
	ImageButton F7;
	ImageButton F8;
	ImageButton F9;
	ImageButton F10;
	ImageButton F11;
	ImageButton F12;
	ImageButton F13;
	ImageButton F14;
	ImageButton F15;
	ImageButton F16;
	ImageButton F17;
	ImageButton F18;
	ImageButton F19;
	ImageButton F20;
	ImageButton F21;
	ImageButton F22;
	ImageButton F23;
	ImageButton F24;
	ArrayList<ImageButton> imageButtons;

	public static Hangar_Fragment_Fleet getInstance() {
		if (instance == null) {
			instance = new Hangar_Fragment_Fleet();
		}
		return instance;
	}

	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
		MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
		MainActivity.getInstance().editTextMain.setEnabled(false);
		MainActivity.getInstance().editTextMain.setText("");
		MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);

		this.view = inflater.inflate(R.layout.fragment_hangar_fleetyards, container, false);


		centerScroll();
		initButtons();
		updateButtons();


		return view;
	}

	private void centerScroll() {

		final HorizontalScrollView hScrollView = view.findViewById(R.id.scrollView);
		final ScrollView scrollView = view.findViewById(R.id.scrollView2);
		final CardView cardView = view.findViewById(R.id.fleetyards_CardView);

		ViewTreeObserver vto = hScrollView.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			public void onGlobalLayout() {
				int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
				int scrollWidth = hScrollView.getChildAt(0).getWidth();

				int screenHeight = cardView.getHeight();
				int scrollHeight = scrollView.getChildAt(0).getHeight();

				int scrollToW = (scrollWidth - screenWidth) / 2;
				int scrollToH = (scrollHeight - screenHeight) / 2;

				hScrollView.scrollTo(scrollToW, 0);
				scrollView.scrollTo(0, scrollToH);

				hScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});

	}

	private void initButtons() {

		this.COMMAND = view.findViewById(R.id.COMMAND);
		this.F1 = view.findViewById(R.id.F1);
		this.F2 = view.findViewById(R.id.F2);
		this.F3 = view.findViewById(R.id.F3);
		this.F4 = view.findViewById(R.id.F4);
		this.F5 = view.findViewById(R.id.F5);
		this.F6 = view.findViewById(R.id.F6);
		this.F7 = view.findViewById(R.id.F7);
		this.F8 = view.findViewById(R.id.F8);
		this.F9 = view.findViewById(R.id.F9);
		this.F10 = view.findViewById(R.id.F10);
		this.F11 = view.findViewById(R.id.F11);
		this.F12 = view.findViewById(R.id.F12);
		this.F13 = view.findViewById(R.id.F13);
		this.F14 = view.findViewById(R.id.F14);
		this.F15 = view.findViewById(R.id.F15);
		this.F16 = view.findViewById(R.id.F16);
		this.F17 = view.findViewById(R.id.F17);
		this.F18 = view.findViewById(R.id.F18);
		this.F19 = view.findViewById(R.id.F19);
		this.F20 = view.findViewById(R.id.F20);
		this.F21 = view.findViewById(R.id.F21);
		this.F22 = view.findViewById(R.id.F22);
		this.F23 = view.findViewById(R.id.F23);
		this.F24 = view.findViewById(R.id.F24);

		this.imageButtons = new ArrayList<ImageButton>() {
			{
				add(COMMAND);
				add(F1);
				add(F2);
				add(F3);
				add(F4);
				add(F5);
				add(F6);
				add(F7);
				add(F8);
				add(F9);
				add(F10);
				add(F11);
				add(F12);
				add(F13);
				add(F14);
				add(F15);
				add(F16);
				add(F17);
				add(F18);
				add(F19);
				add(F20);
				add(F21);
				add(F22);
				add(F23);
				add(F24);
			}
		};
	}

	public void updateButtons(){
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				APIBuildings api = new APIBuildings();
				api.getPlayerBuildings();
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
				setButtonListener();
				setButtonImage();
				Log.v("-----getUserBuildings", "wtf");
			}
		}.execute();
	}

	private void setButtonImage() {
		for (ImageButton button : imageButtons) {
			int image = getButtonImage(Shared_Prefs.getInt(App.getAppContext(), getResources().getResourceEntryName(button.getId())));
			button.setImageResource(image);
		}
		if (Shared_Prefs.getInt(App.getAppContext(), "COMMAND") == 0) {
			COMMAND.setImageResource(R.drawable.c11);
		}
	}

	private int getButtonImage(int building) {

		int image = 0;
		switch (building) {
			case 0: {
				break;
			}
			case 1: {
				image = R.drawable.c11;
				return image;
			}
			case 2: {
				image = R.drawable.c12;
				return image;
			}
			case 3: {
				image = R.drawable.c13;
				return image;
			}
			case 4: {
				image = R.drawable.c11;
				return image;
			}
			case 5: {
				image = R.drawable.c12;
				return image;
			}
			case 6: {
				image = R.drawable.c13;
				return image;
			}
			case 7: {
				image = R.drawable.c11;
				return image;
			}
			case 8: {
				image = R.drawable.c12;
				return image;
			}
			case 9: {
				image = R.drawable.c13;
				return image;
			}
			case 10: {
				image = R.drawable.a11;
				return image;
			}
			case 11: {
				image = R.drawable.a12;
				return image;
			}
			case 12: {
				image = R.drawable.a13;
				return image;
			}
			case 13: {
				image = R.drawable.b11;
				return image;
			}
			case 14: {
				image = R.drawable.b12;
				return image;
			}
			case 15: {
				image = R.drawable.b13;
				return image;
			}
			case 16: {
				image = R.drawable.d11;
				return image;
			}
			case 17: {
				image = R.drawable.d12;
				return image;
			}
			case 18: {
				image = R.drawable.d13;
				return image;
			}

		}
		return image;
	}

	private void setButtonListener() {
		for (final ImageButton button : imageButtons) {
			if (Shared_Prefs.getInt(App.getAppContext(), getResources().getResourceEntryName(button.getId())) == 0) {
				button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Log.v("HFF--------------------", getResources().getResourceEntryName(button.getId()));
						openPurchaseDialog(getResources().getResourceEntryName(button.getId()));
					}
				});
			} else {
				button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						openUpgradeDialog(getResources().getResourceEntryName(button.getId()));
					}
				});
			}
		}
	}

	private void openUpgradeDialog(String button) {
		Hangar_Fleet_Dialog_Upgrade dialog = new Hangar_Fleet_Dialog_Upgrade(getActivity(), button);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setOnDismissListener(this);
		dialog.show();
	}

	private void openPurchaseDialog(String button) {

		Hangar_Fleet_Dialog_Purchase dialog = new Hangar_Fleet_Dialog_Purchase(getActivity(), button);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setOnDismissListener(this);
		dialog.show();
	}

	@Override
	public void onDismiss(DialogInterface dialogInterface) {
		updateButtons();
	}
}
