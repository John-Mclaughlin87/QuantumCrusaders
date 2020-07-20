package com.technomicron.qc.ui.organization;

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

import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;

public class Organization_Fragment extends Fragment {

    private Organization_Model organizationModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setDefaultUIState();
        organizationModel =
                ViewModelProviders.of(this).get(Organization_Model.class);
        View root = inflater.inflate(R.layout.fragment_organization, container, false);
        final TextView textView = root.findViewById(R.id.text_organization);
        organizationModel.getText().observe(this, new Observer<String>() {
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