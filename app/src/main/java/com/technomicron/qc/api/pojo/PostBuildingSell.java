package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostBuildingSell {

	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("buildingLocation")
	@Expose
	private String buildingLocation;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public PostBuildingSell(String token, String buildingLocation) {
		super();
		this.token = token;
		this.buildingLocation = buildingLocation;
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
}