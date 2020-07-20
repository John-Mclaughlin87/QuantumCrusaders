package com.technomicron.qc.api.pojo;

		import com.google.gson.annotations.Expose;
		import com.google.gson.annotations.SerializedName;

public class CombatAttack {

	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("userid")
	@Expose
	private int userid;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CombatAttack(String token, int userid) {
		super();
		this.token = token;
		this.userid = userid;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int  getUserID() {
		return userid;
	}

	public void setUserID(int userid) {
		this.userid = userid;
	}
}