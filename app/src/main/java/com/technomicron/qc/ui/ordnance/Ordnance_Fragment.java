package com.technomicron.qc.ui.ordnance;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technomicron.qc.App;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;
import com.technomicron.qc.api.APIMethods;
import com.technomicron.qc.api.APIOrdnance;

import java.util.ArrayList;

public class Ordnance_Fragment extends Fragment {

    String TAG = "Ordnance_Fragment: ";
    ListView ordnanceListView;
    View ordnanceView;

    Ordnance_Adapter ordnanceAdapter;
    Ordnance_ArrayList<Ordnance_Model> qcOrdnanceArrayList;

    APIOrdnance api;

    public static Ordnance_Fragment instance;
    Ordnance_Receiver ordnance_Receiver;
    IntentFilter ordnanceIntentFilter;

    public static Ordnance_Fragment getInstance() {
        if (instance == null) {
            instance = new Ordnance_Fragment();
        }
        return instance;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String tFunct = "onDestroy: unregister receiver";
        Log.v(TAG, tFunct);
        getActivity().unregisterReceiver(ordnance_Receiver);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        swapEditTextForChatPreview();

        String tFunct = "onCreateView: register receiver ";
        Log.v(TAG, tFunct);
        this.instance = this;
        this.ordnance_Receiver = new Ordnance_Receiver();
        this.ordnanceIntentFilter = new IntentFilter();
        ordnanceIntentFilter.addAction("RECEIVED_USER_ORDNANCE");
        getActivity().registerReceiver(ordnance_Receiver, ordnanceIntentFilter);

        this.api = new APIOrdnance();
        this.updateOrdnanceDetails();

        this.ordnanceView = inflater.inflate(R.layout.fragment_ordnance, container, false);

        this.ordnanceListView = ordnanceView.findViewById(R.id.ordnance_list);

        this.qcOrdnanceArrayList = new Ordnance_ArrayList<Ordnance_Model>();
        this.ordnanceAdapter = new Ordnance_Adapter(qcOrdnanceArrayList, App.getAppContext());

        ordnanceListView.setAdapter(ordnanceAdapter);
        ordnanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return ordnanceView;
    }

    private void swapEditTextForChatPreview(){

        setDefaultUIState();
    }

    private void setDefaultUIState(){
        MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
        MainActivity.getInstance().chatPreviewCardView.setVisibility(View.VISIBLE);
        MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
        MainActivity.getInstance().editTextMain.setEnabled(false);
        MainActivity.getInstance().editTextMain.setText("");
        MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);
    }
    public void receivedOrdnanceDetails(ArrayList<String> ordnance){
        Log.v("--------------ORDNANCE", ordnance.toString());
        String title = ordnance.get(0);
        String price = ordnance.get(1);
        String aBoost = ordnance.get(2);
        String dBoost = ordnance.get(3);
        String qty = ordnance.get(4);
        qcOrdnanceArrayList.add(new Ordnance_Model(title, price, aBoost, dBoost, qty));
        ordnanceAdapter.notifyDataSetChanged();
    }

    public void updateOrdnanceDetails(){

        api.getPlayerOrdnance();
    }
}