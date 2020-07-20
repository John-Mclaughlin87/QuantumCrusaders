package com.technomicron.qc.api.pojo;

		import com.google.gson.annotations.Expose;
		import com.google.gson.annotations.SerializedName;

public class GetFreelancerList {

	@SerializedName("username")
	@Expose
	private String username;

	@SerializedName("userID")
	@Expose
	private Integer userID;

	@SerializedName("attack")
	@Expose
	private Integer attack;

	@SerializedName("defense")
	@Expose
	private Integer defense;

	@SerializedName("covertAttack")
	@Expose
	private Integer covertAttack;

	@SerializedName("covertDefense")
	@Expose
	private Integer covertDefense;

	@SerializedName("value")
	@Expose
	private Integer value;

	public GetFreelancerList(String username, Integer userID, Integer attack, Integer defense, Integer covertAttack, Integer covertDefense, Integer value) {
		super();
		this.userID = userID;
		this.username = username;
		this.attack = attack;
		this.defense = defense;
		this.covertAttack = covertAttack;
		this.covertDefense = covertDefense;
		this.value = value;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUserID() {
		return userID;
	}

	public Integer getAttack() {
		return attack;
	}

	public Integer getDefense() { return defense; }

	public Integer getCovertAttack() {
		return covertAttack;
	}

	public Integer getCovertDefense() { return covertDefense; }

	public Integer getValue() { return value; }
}