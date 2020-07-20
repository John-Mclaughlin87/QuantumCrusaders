package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPlayer {


	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("fcmID")
	@Expose
	private String fcmID;
	@SerializedName("playstyle")
	@Expose
	private int playstyle;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public PostPlayer(String username, String email, String token, int playstyle, String fcmID) {
		super();
		this.username = username;
		this.email = email;
		this.token = token;
		this.playstyle = playstyle;
		this.fcmID = fcmID;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public int getPlaystyle() {
		return playstyle;
	}

	public String getFcmID() { return fcmID; }

}