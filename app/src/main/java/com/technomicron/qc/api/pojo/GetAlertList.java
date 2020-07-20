
package com.technomicron.qc.api.pojo;

		import com.google.gson.annotations.Expose;
		import com.google.gson.annotations.SerializedName;

public class GetAlertList {

	@SerializedName("alert_id")
	@Expose
	private Integer alert_id;
	@SerializedName("alertType")
	@Expose
	private String alertType;
	@SerializedName("user_id")
	@Expose
	private Integer user_id;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("user_id2")
	@Expose
	private Integer user_id2;
	@SerializedName("username2")
	@Expose
	private String username2;
	@SerializedName("resources")
	@Expose
	private Integer resources;

	public GetAlertList(Integer alert_id, String alertType, Integer user_id, String username, Integer user_id2, String username2, Integer resources) {
		super();
		this.alert_id = alert_id;
		this.alertType = alertType;
		this.user_id = user_id;
		this.username = username;
		this.user_id2 = user_id2;
		this.username2 = username2;
		this.resources = resources;
	}

	public Integer getAlert_id() {
		return alert_id;
	}

	public String getAlertType() {
		return alertType;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUser_id2() {
		return user_id2;
	}

	public String getUsername2() {
		return username2;
	}

	public Integer getResources() {
		return resources;
	}
}