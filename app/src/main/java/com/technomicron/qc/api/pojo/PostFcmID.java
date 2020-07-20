package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostFcmID {


	@SerializedName("fcmID")
	@Expose
	private String fcmID;
	@SerializedName("token")
	@Expose
	private String token;


	/**
	 * No args constructor for use in serialization
	 *
	 */
	public PostFcmID(String fcmID, String token) {
		super();
		this.fcmID = fcmID;
		this.token = token;
	}

	public String getFcmID() { return fcmID; }
	public String getToken() { return token; }

}