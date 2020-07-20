package com.technomicron.qc.ui.hangar.alerts;

import androidx.lifecycle.ViewModel;

public class Alerts_Model extends ViewModel {

	String alertType;
	int userID;
	String username;
	int userID2;
	String username2;
	int alertID;
	int resources;

	public Alerts_Model(String alertType, int userID, String username, int userID2,
	                    String username2, int alertID, int resources) {
		this.alertType = alertType;
		this.userID = userID;
		this.username = username;
		this.userID2 = userID2;
		this.username2 = username2;
		this.alertID = alertID;
		this.resources = resources;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserID2() {
		return userID2;
	}

	public void setUserID2(int userID2) {
		this.userID2 = userID2;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public int getAlertID() {
		return alertID;
	}

	public void setAlertID(int alertID) {
		this.alertID = alertID;
	}

	public int getResources() {
		return resources;
	}

	public void setResources(int resources) {
		this.resources = resources;
	}

}