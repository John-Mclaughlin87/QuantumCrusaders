package com.technomicron.qc.ui.hangar.combat;

import androidx.lifecycle.ViewModel;

public class Hangar_Fragment_Combat_Model extends ViewModel {

	String username;
	String userID;
	String orgName;
	String riskFactor;

	public Hangar_Fragment_Combat_Model(String username, String userID, String orgName, String riskFactor ) {
		this.userID = userID;
		this.username = username;
		this.orgName = orgName;
		this.riskFactor = riskFactor;
	}


	public String getUsername() {
		return username;
	}

	public String getUserID() {
		return userID;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getRiskFactor() {
		return riskFactor;
	}

}