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

public class Chat_Fragment_Freelancer extends Fragment {

    ListView freelancerListView;
    View chatView;
    Chat_Adapter freelancerAdapter;
    Chat_ArrayList_Preview<Chat_Model> qcFreelancerChatModel;
    NavController navController;
    BottomNavigationView chatNavView;
    NavOptions navOptions;

    public static Chat_Fragment_Freelancer instance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onCreateView");
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
    public static Chat_Fragment_Freelancer getInstance() {
        if (instance == null) {
            instance = new Chat_Fragment_Freelancer();
        }
        return instance;
    }

    private void initVars(LayoutInflater inflater, ViewGroup container){
        Log.v("~~QCCHAT_FREELAFRAGMENT","in initVars");
        this.instance = this;
        this.chatView = inflater.inflate(R.layout.chat_fragment_freelancer, container, false);
        this.freelancerListView = chatView.findViewById(R.id.text_chat_freelancers);
        this.freelancerAdapter = MainActivity.getMainMethods().getFreelancersAdapter();
        this.navController = MainActivity.getMainMethods().getNavController();
        this.chatNavView = chatView.findViewById(R.id.top_nav_view);
        this.navOptions = MainActivity.getMainMethods().getNavOptions();

    }

    private void setAdapters(){
        Log.v("~~QCCHAT_FREELAFRAGMENT","in setAdapters");
        freelancerListView.setAdapter(freelancerAdapter);
    }

    private void setListViewListeners(){
        Log.v("~~QCCHAT_FREELAFRAGMENT","in setListViewListeners");
        freelancerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                qcFreelancerChatModel = MainActivity.getMainMethods().getQCFreelancersChatModel();
                Chat_Model dataModel = qcFreelancerChatModel.get(position);
                Snackbar.make(view, dataModel.getChannel() + dataModel.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onDestroy");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("~~QCCHAT_FREELAFRAGMENT","in onViewCreated");
    }
}
