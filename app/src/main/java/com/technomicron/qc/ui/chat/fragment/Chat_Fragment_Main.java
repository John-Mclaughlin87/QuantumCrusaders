package com.technomicron.qc.ui.chat.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;
import com.technomicron.qc.ui.chat.adaptar.Chat_Adapter;
import com.technomicron.qc.ui.chat.list.Chat_ArrayList_Preview;
import com.technomicron.qc.ui.chat.model.Chat_Model;


public class Chat_Fragment_Main extends Fragment {

    ListView systemListView;
    View chatView;
    Chat_Adapter systemAdapter;
    Chat_ArrayList_Preview<Chat_Model> qcSystemChatModel;
    NavController navController;
    BottomNavigationView chatNavView;
    NavOptions navOptions;

    public static Chat_Fragment_Main instance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onCreateView");
        this.initVars(inflater, container);
        this.setAdapters();
        this.setListViewListeners();
        setDefaultUIState();
        return chatView;
    }

    private void setDefaultUIState(){
        MainActivity.getInstance().status_layout.setVisibility(View.GONE);
        NavigationUI.setupWithNavController(chatNavView, navController);
        MainActivity.getInstance().listViewMain.setVisibility(View.GONE);
        MainActivity.getInstance().editTextMain.setVisibility(View.VISIBLE);
        MainActivity.getInstance().editTextMain.setEnabled(true);
        MainActivity.getInstance().editTextMain.setText("");
    }
    public static Chat_Fragment_Main getInstance() {
        if (instance == null) {
            instance = new Chat_Fragment_Main();
        }
        return instance;
    }

    private void initVars(LayoutInflater inflater, ViewGroup container){
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in initVars");
        this.instance = this;
        this.chatView = inflater.inflate(R.layout.chat_fragment_system, container, false);
        this.systemListView = chatView.findViewById(R.id.text_chat_system);
        this.systemAdapter = MainActivity.getMainMethods().getSystemAdapter();
        this.navController = MainActivity.getMainMethods().getNavController();
        this.chatNavView = chatView.findViewById(R.id.top_nav_view);
        this.navOptions = MainActivity.getMainMethods().getNavOptions();

    }

    private void setAdapters(){
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in setAdapters");
        systemListView.setAdapter(systemAdapter);
    }

    private void setListViewListeners(){
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in setListViewListeners");
        systemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                qcSystemChatModel = MainActivity.getMainMethods().getQCSystemChatModel();
                Chat_Model dataModel = qcSystemChatModel.get(position);
                Snackbar.make(view, dataModel.getChannel() + dataModel.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(">>>>QCCHAT_MAINFRAGMENT","in onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onDestroy");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("~~QCCHAT_SYSTEMFRAGMENT","in onViewCreated");
    }
}
