package com.technomicron.qc.ui.hangar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import com.technomicron.qc.App;
import com.technomicron.qc.activity.main.MainActivity;
import com.technomicron.qc.R;
import com.technomicron.qc.gamedata.Shared_Prefs;

public class Hangar_Fragment extends Fragment implements OnClickListener {

    TextView usernameView;
    Button fleetYardsButton;
    Button combatButton;
    Button profileButton;
    Button contractsButton;
    Button vaultButton;
    Button alertsButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainActivity.getInstance().listViewMain.setVisibility(View.VISIBLE);
        MainActivity.getInstance().chatPreviewCardView.setVisibility(View.VISIBLE);
        MainActivity.getInstance().editTextMain.setVisibility(View.GONE);
        MainActivity.getInstance().editTextMain.setEnabled(false);
        MainActivity.getInstance().editTextMain.setText("");
        MainActivity.getInstance().status_layout.setVisibility(View.VISIBLE);

        View root = inflater.inflate(R.layout.fragment_hangar, container, false);

        this.usernameView = root.findViewById(R.id.username);
        this.fleetYardsButton = root.findViewById(R.id.fleetyards_button);
        this.combatButton = root.findViewById(R.id.combat_button);
        this.profileButton = root.findViewById(R.id.fHProfileButton);
        this.contractsButton = root.findViewById(R.id.fHContractsButton);
        this.vaultButton = root.findViewById(R.id.fHVaultButton);
        this.alertsButton = root.findViewById(R.id.fHAlertsButton);
        fleetYardsButton.setOnClickListener(this);
        combatButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        contractsButton.setOnClickListener(this);
        vaultButton.setOnClickListener(this);
        alertsButton.setOnClickListener(this);

        String username = Shared_Prefs.getString(App.getAppContext(), "username");
        usernameView.setText(username);

        return root;
    }

    @Override
    public void onClick(View view) {
        NavController navController = MainActivity.getNavController();
        NavOptions navOptions = MainActivity.getNavOptions();
        switch (view.getId()) {
            case R.id.fleetyards_button:
                navController.navigate(R.id.action_navigation_hangar_to_navigation_hangar_fleetyards, null, navOptions);
                break;
            case R.id.combat_button:
                navController.navigate(R.id.action_navigation_hangar_to_navigation_hangar_combat, null, navOptions);
                break;
            case R.id.fHProfileButton:
                navController.navigate(R.id.action_navigation_hangar_to_navigation_profile, null, navOptions);
                break;
            case R.id.fHContractsButton:
                navController.navigate(R.id.action_navigation_hangar_to_navigation_hangar_contracts, null, navOptions);
                break;
            case R.id.fHVaultButton:
                Hangar_Vault_Dialog dialog = new Hangar_Vault_Dialog(getActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog.setCancelable(false);
                //dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                break;
            case R.id.fHAlertsButton:
                navController.navigate(R.id.action_navigation_hangar_to_navigation_alerts, null, navOptions);
                break;
        }
    }
}