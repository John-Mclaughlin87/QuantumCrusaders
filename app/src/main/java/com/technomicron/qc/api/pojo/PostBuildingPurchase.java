package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostBuildingPurchase {

	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("buildingLocation")
	@Expose
	private String buildingLocation;
	@SerializedName("buildingNumber")
	@Expose
	private int buildingNumber;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public PostBuildingPurchase(String token, String buildingLocation, int buildingNumber) {
		super();
		this.token = token;
		this.buildingLocation = buildingLocation;
		this.buildingNumber = buildingNumber;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String  getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(String buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

}