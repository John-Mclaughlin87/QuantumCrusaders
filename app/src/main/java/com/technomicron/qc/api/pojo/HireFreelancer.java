package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HireFreelancer {

	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("userid")
	@Expose
	private Integer userID;


	public HireFreelancer(String token, Integer userID) {
		super();
		this.token = token;
		this.userID = userID;
	}

	public String getToken() {
		return token;
	}

	public Integer getUserID() {
		return userID;
	}


}