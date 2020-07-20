package com.technomicron.qc.api.pojo;

		import com.google.gson.annotations.Expose;
		import com.google.gson.annotations.SerializedName;

public class GetVaultBalance {
	@SerializedName("balance")
	@Expose
	private Integer balance;


	public GetVaultBalance(Integer balance) {
		super();
		this.balance = balance;
	}

	public Integer getBalance() {
		return balance;
	}
}