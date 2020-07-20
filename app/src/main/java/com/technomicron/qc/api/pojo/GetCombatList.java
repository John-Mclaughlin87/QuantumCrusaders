
package com.technomicron.qc.api.pojo;

		import com.google.gson.annotations.Expose;
		import com.google.gson.annotations.SerializedName;

public class GetCombatList {

	@SerializedName("username")
	@Expose
	private String username;

	@SerializedName("userID")
	@Expose
	private Integer userID;

	@SerializedName("orgName")
	@Expose
	private String orgName;

	@SerializedName("riskFactor")
	@Expose
	private Integer riskFactor;

	public GetCombatList(String username, Integer userID, String orgName, Integer riskFactor) {
		super();
		this.username = username;
		this.userID = userID;
		this.orgName = orgName;
		this.riskFactor = riskFactor;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUserID() {
		return userID;
	}

	public String getOrgName() {
		return orgName;
	}

	public Integer getRiskFactor() {
		return riskFactor;
	}

}