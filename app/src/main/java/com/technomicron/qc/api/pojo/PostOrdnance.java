package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOrdnance {

	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("oid")
	@Expose
	private Integer oid;
	@SerializedName("quantity")
	@Expose
	private Integer quantity;


	public PostOrdnance(String token, Integer oid, Integer quantity) {
		super();
		this.token = token;
		this.oid = oid;
		this.quantity = quantity;
	}

	public String getToken() {
		return token;
	}

	public Integer getOid() {
		return oid;
	}

	public Integer getQuantity() {
		return quantity;
	}

}