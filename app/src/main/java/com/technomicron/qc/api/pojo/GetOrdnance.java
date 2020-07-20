package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrdnance {

	@SerializedName("quantity")
	@Expose
	private Integer ordnanceQuantity;
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("price")
	@Expose
	private Integer price;
	@SerializedName("attack_boost")
	@Expose
	private Integer attackBoost;
	@SerializedName("defense_boost")
	@Expose
	private Integer defenseBoost;

	public GetOrdnance(Integer ordnanceQuantity, String title, Integer price, Integer attackBoost, Integer defenseBoost) {
		super();
		this.ordnanceQuantity = ordnanceQuantity;
		this.title = title;
		this.price = price;
		this.attackBoost = attackBoost;
		this.defenseBoost = defenseBoost;
	}

	public Integer getOrdnanceQuantity() {
		return ordnanceQuantity;
	}

	public String getTitle() {
		return title;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getAttackBoost() {
		return attackBoost;
	}

	public Integer getDefenseBoost() {
		return defenseBoost;
	}

}