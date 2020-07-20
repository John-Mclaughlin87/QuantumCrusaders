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

public class Chat_Fragment_Contacts extends Fragment {

    ListView contactsListView;
    View chatView;
    Chat_Adapter contactsAdapter;
    Chat_ArrayList_Preview<Chat_Model> qcContactsChatModel;
    NavController navController;
    BottomNavigationView chatNavView;
    NavOptions navOptions;

    public static Chat_Fragment_Contacts instance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onCreateView");
        this.initVars(inflater, container);
        this.setAdapters();
        this.setListViewListeners();

        NavigationUI.setupWithNavController(chatNavView, navController);
        setDefaultUIState();
        return chatView;
    }

    private void setDefaultUIState(){
        MainActivity.getInstance().status_layout.setVisibility(View.GONE);
        MainActivity.getInstance().listViewMain.setVisibility(View.GONE);
        MainActivity.getInstance().editTextMain.setVisibility(View.VISIBLE);
        MainActivity.getInstance().editTextMain.setEnabled(true);
        MainActivity.getInstance().editTextMain.setText("");
    }

    public static Chat_Fragment_Contacts getInstance() {
        if (instance == null) {
            instance = new Chat_Fragment_Contacts();
        }
        return instance;
    }

    private void initVars(LayoutInflater inflater, ViewGroup container){
        Log.v("~~QCCHAT_CONTACFRAGMENT","in initVars");
        this.instance = this;
        this.chatView = inflater.inflate(R.layout.chat_fragment_contacts, container, false);
        this.contactsListView = chatView.findViewById(R.id.text_chat_contacts);
        this.contactsAdapter = MainActivity.getMainMethods().getContactsAdapter();
        this.navController = MainActivity.getMainMethods().getNavController();
        this.chatNavView = chatView.findViewById(R.id.top_nav_view);
        this.navOptions = MainActivity.getMainMethods().getNavOptions();

    }

    private void setAdapters(){
        Log.v("~~QCCHAT_CONTACFRAGMENT","in setAdapters");
        contactsListView.setAdapter(contactsAdapter);
    }

    private void setListViewListeners(){
        Log.v("~~QCCHAT_CONTACFRAGMENT","in setListViewListeners");
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                qcContactsChatModel = MainActivity.getMainMethods().getQCContactsChatModel();
                Chat_Model dataModel = qcContactsChatModel.get(position);
                Snackbar.make(view, dataModel.getChannel() + dataModel.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onDestroy");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("~~QCCHAT_CONTACFRAGMENT","in onViewCreated");
    }
}