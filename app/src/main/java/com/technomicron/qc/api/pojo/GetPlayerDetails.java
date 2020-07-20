package com.technomicron.qc.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPlayerDetails {

	@SerializedName("igc")
	@Expose
	private Integer igc;
	@SerializedName("pigc")
	@Expose
	private Integer pigc;
	@SerializedName("cigc")
	@Expose
	private Integer cigc;
	@SerializedName("attack_power")
	@Expose
	private Integer attack_power;
	@SerializedName("defense_power")
	@Expose
	private Integer defense_power;
	@SerializedName("covert_attack_power")
	@Expose
	private Integer covert_attack_power;
	@SerializedName("covert_defense_power")
	@Expose
	private Integer covert_defense_power;
	@SerializedName("freelancers")
	@Expose
	private Integer freelancers;
	@SerializedName("freelancersMax")
	@Expose
	private Integer freelancersMax;
	@SerializedName("freelancer_power")
	@Expose
	private Integer freelancer_power;
	@SerializedName("freelancer_defense")
	@Expose
	private Integer freelancer_defense;
	@SerializedName("freelancer_covert_attack")
	@Expose
	private Integer freelancer_covert_attack;
	@SerializedName("freelancer_covert_defense")
	@Expose
	private Integer freelancer_covert_defense;
	@SerializedName("freelancersValue")
	@Expose
	private Integer freelancersValue;
	@SerializedName("fleetC")
	@Expose
	private Integer fleetC;
	@SerializedName("fleetT")
	@Expose
	private Integer fleetT;
	@SerializedName("roguesC")
	@Expose
	private Integer roguesC;
	@SerializedName("roguesT")
	@Expose
	private Integer roguesT;
	@SerializedName("orgName")
	@Expose
	private String orgName;
	@SerializedName("owner")
	@Expose
	private Integer owner;
	@SerializedName("hireCost")
	@Expose
	private Integer hireCost;

	public Integer getIgc() {
		return igc;
	}

	public Integer getPigc() {
		return pigc;
	}

	public Integer getCigc() {
		return cigc;
	}

	public Integer getAttack_power() {
		return attack_power;
	}

	public Integer getDefense_power() {
		return defense_power;
	}

	public Integer getCovert_attack_power() {
		return covert_attack_power;
	}

	public Integer getCovert_defense_power() {
		return covert_defense_power;
	}

	public Integer getFreelancers() {
		return freelancers;
	}

	public Integer getFreelancersMax() {
		return freelancersMax;
	}

	public Integer getFreelancer_power() {
		return freelancer_power;
	}

	public Integer getFreelancer_defense() {
		return freelancer_defense;
	}

	public Integer getFreelancer_covert_attack() {
		return freelancer_covert_attack;
	}

	public Integer getFreelancer_covert_defense() {
		return freelancer_covert_defense;
	}

	public Integer getFreelancersValue() {
		return freelancersValue;
	}

	public Integer getFleetC() {
		return fleetC;
	}

	public Integer getFleetT() {
		return fleetT;
	}

	public Integer getRoguesC() {
		return roguesC;
	}

	public Integer getRoguesT() {
		return roguesT;
	}

	public String getOrgName() {
		return orgName;
	}

	public Integer getOwner() {
		return owner;
	}

	public Integer getHireCost() {
		return hireCost;
	}

	public GetPlayerDetails(Integer igc, Integer pigc, Integer cigc, Integer attack_power, Integer defense_power, Integer covert_attack_power, Integer covert_defense_power,
	                        Integer freelancers, Integer freelancersMax, Integer freelancer_power, Integer freelancer_defense, Integer freelancer_covert_attack, Integer freelancer_covert_defense,
	                        Integer freelancersValue, Integer fleetC, Integer fleetT, Integer roguesC, Integer roguesT, String orgName, Integer owner, Integer hireCost) {
		super();
		this.igc = igc;
		this.pigc = pigc;
		this.cigc = cigc;
		this.attack_power = attack_power;
		this.defense_power = defense_power;
		this.covert_attack_power = covert_attack_power;
		this.covert_defense_power = covert_defense_power;
		this.freelancers = freelancers;
		this.freelancersMax = freelancersMax;
		this.freelancer_power = freelancer_power;
		this.freelancer_defense = freelancer_defense;
		this.freelancer_covert_attack = freelancer_covert_attack;
		this.freelancer_covert_defense = freelancer_covert_defense;
		this.freelancersValue = freelancersValue;
		this.fleetC = fleetC;
		this.fleetT = fleetT;
		this.roguesC = roguesC;
		this.roguesT = roguesT;
		this.orgName = orgName;
		this.owner = owner;
		this.hireCost = hireCost;
	}



}