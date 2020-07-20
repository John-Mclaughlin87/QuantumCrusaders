package com.technomicron.qc.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.technomicron.qc.App;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.gamedata.Shared_Prefs;
import com.technomicron.qc.ui.chat.adaptar.Chat_Adapter;
import com.technomicron.qc.service.QCService_Binder;
import com.technomicron.qc.ui.chat.list.Chat_ArrayList_Preview;
import com.technomicron.qc.ui.chat.model.Chat_Model;
import com.technomicron.qc.service.QCService;
import com.technomicron.qc.ui.chat.adaptar.Chat_Adapter_Preview;
import com.technomicron.qc.ui.chat.model.Chat_Model_Preview;


public class MainActivity extends AppCompatActivity {


	protected static QCService QCService;
	protected boolean isBind = false;
	protected static MainActivity_Receiver mainActivity_Receiver;
	protected static Intent serviceIntent;
	protected static IntentFilter mainActivityIntentFilter;

	public static EditText editTextMain;
	public static CardView chatPreviewCardView;
	public static TextView igc_value;
	public static TextView pigc_value;
	public static TextView fleetMax;
	public static TextView fleetCurrent;
	public static TextView covertMax;
	public static TextView covertCurrent;
	public static TextView freelancersTV;
	public static ProgressBar fleetProgress;
	public static ProgressBar covertProgress;

	protected static NavController navController;
	protected static BottomNavigationView navView;
	protected static NavOptions navOptions;

	protected static Chat_ArrayList_Preview<Chat_Model> qcSystemChatModel;
	protected static Chat_ArrayList_Preview<Chat_Model> qcOrganizationChatModel;
	protected static Chat_ArrayList_Preview<Chat_Model> qcFreelancersChatModel;
	protected static Chat_ArrayList_Preview<Chat_Model> qcContactsChatModel;
	protected static Chat_ArrayList_Preview<Chat_Model_Preview> qcChatPreviewChatModel;

	protected static Chat_Adapter systemAdapter;
	protected static Chat_Adapter organizationAdapter;
	protected static Chat_Adapter freelancersAdapter;
	protected static Chat_Adapter contactsAdapter;
	protected static Chat_Adapter_Preview qcChatPreviewAdapter;

	public static ListView listViewMain;
	public static CardView status_layout;

	protected static APIMethods apiMethods;
	protected static MainMethods mainMethods;

	protected static MainActivity instance;

	public static MainActivity getInstance() {
		if (instance == null) {
			instance = new MainActivity();
		}
		return instance;
	}

	public static MainMethods getMainMethods() {
		return mainMethods;
	}
	public static NavOptions getNavOptions() {return navOptions;}
	public static NavController getNavController() {return navController;}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("---------MainActivity", "in onCreate");
		setContentView(R.layout.activity_main);
		getFcmID();
		initVars();
		apiMethods.getPlayer();
		apiMethods.getPlayerDetails();
		NavigationUI.setupWithNavController(navView, navController);

		listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				navController.navigate(mainMethods.chatPreviewDestination(position), null, navOptions);
			}
		});

		final ImageButton chatButton = (ImageButton) findViewById(R.id.chatButton);
		chatButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mainMethods.chatButton();
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v("---------MainActivity", "in onStart");
		startService(serviceIntent);
		mainMethods.startReceiver();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("---------MainActivity", "in onDestroy");
		QCService.disconnectChat();

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v("---------MainActivity", "in onDestroy");
		stopService(serviceIntent);
		if (isBind) {
			unbindService(mConnection);
			isBind = false;
		}
		if(mainActivity_Receiver != null ){
			unregisterReceiver(mainActivity_Receiver);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!isBind) {
			bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
			isBind = true;
		}
			registerReceiver(mainActivity_Receiver, mainActivityIntentFilter);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v("---------MainActivity", "in onStop");

	}

	private void initVars() {
		this.instance = this;
		this.serviceIntent = new Intent(App.getAppContext(), QCService.class);
		this.mainActivity_Receiver = new MainActivity_Receiver();
		this.mainActivityIntentFilter = new IntentFilter();
		this.qcSystemChatModel = new Chat_ArrayList_Preview<>();
		this.qcOrganizationChatModel = new Chat_ArrayList_Preview<>();
		this.qcFreelancersChatModel = new Chat_ArrayList_Preview<>();
		this.qcContactsChatModel = new Chat_ArrayList_Preview<>();
		this.qcChatPreviewChatModel = new Chat_ArrayList_Preview<>();
		this.qcChatPreviewAdapter = new Chat_Adapter_Preview(qcChatPreviewChatModel, App.getAppContext());
		this.systemAdapter = new Chat_Adapter(qcSystemChatModel, App.getAppContext());
		this.organizationAdapter = new Chat_Adapter(qcOrganizationChatModel, App.getAppContext());
		this.freelancersAdapter = new Chat_Adapter(qcFreelancersChatModel, App.getAppContext());
		this.contactsAdapter = new Chat_Adapter(qcContactsChatModel, App.getAppContext());
		this.navOptions = new NavOptions.Builder()
				//.setPopUpTo(R.id.navigation_chat, true)
				//.setEnterAnim(R.anim.up_from_bottom)
				//.setExitAnim(R.anim.down_from_top)
				//.setPopEnterAnim(R.anim.up_from_bottom)
				//.setPopExitAnim(R.anim.down_from_top)
				.build();
		this.navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		this.navView = findViewById(R.id.nav_view);
		this.listViewMain = (ListView) findViewById(R.id.listViewMain);
		this.status_layout = (CardView) findViewById(R.id.status_layout);
		listViewMain.setAdapter(qcChatPreviewAdapter);


		this.editTextMain = (EditText) findViewById(R.id.editTextMain);
		this.igc_value = (TextView) findViewById(R.id.igc_value);
		this.pigc_value = (TextView) findViewById(R.id.pigc_value);
		this.fleetMax = (TextView) findViewById(R.id.ships_min_max);
		this.covertMax = (TextView) findViewById(R.id.scouts_min_max);
		this.fleetProgress = (ProgressBar) findViewById(R.id.ships_progressBar);
		this.covertProgress = (ProgressBar) findViewById(R.id.scouts_progressBar);
		this.fleetCurrent = (TextView) findViewById(R.id.fleet_min_max2);
		this.covertCurrent = (TextView) findViewById(R.id.scouts_min_max2);
		this.chatPreviewCardView = (CardView) findViewById(R.id.chatPreviewCardView);
		this.freelancersTV = (TextView) findViewById(R.id.freelancer_value);
		this.apiMethods = new APIMethods();
		this.mainMethods = new MainMethods();
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			Log.v("-----------MainActivity", "in ServiceConnection onServiceConnected");
			QCService_Binder qcBinder = (QCService_Binder) iBinder;
			QCService = qcBinder.getService();
			isBind = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			Log.v("-----------MainActivity", "in ServiceConnection onServiceDisconnected");
			isBind = false;
		}

	};

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
					Shared_Prefs.setString(App.getAppContext(), "fcmID", fcmID);
					// Log and toast
					Log.d("getInstanceId succeeded", fcmID);
					updateFcmID();
				}
			});
	}

	private void updateFcmID(){

		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground( Void... voids ) {


				apiMethods.updateFcmID(Shared_Prefs.getString(getApplicationContext(), "fcmID"));
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {

			}
		}.execute();
	}

}
