package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostVault {
	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("quantity")
	@Expose
	private Integer quantity;


	public PostVault(String token, Integer quantity) {
		super();
		this.token = token;
		this.quantity = quantity;
	}

	public String getToken() {
		return token;
	}

	public Integer getQuantity() {
		return quantity;
	}
}
