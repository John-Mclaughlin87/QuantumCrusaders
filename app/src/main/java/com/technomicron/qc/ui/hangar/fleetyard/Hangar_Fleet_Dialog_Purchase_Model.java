package com.technomicron.qc.ui.hangar.fleetyard;

import androidx.lifecycle.ViewModel;

public class Hangar_Fleet_Dialog_Purchase_Model extends ViewModel {

	String bName;
	String bType;
	String tier;
	String level;
	String uName;
	String uNumber;
	String attack;
	String defense;
	String cAttack;
	String cDefense;
	String cost;

	public Hangar_Fleet_Dialog_Purchase_Model(String bName, String bType, String tier, String level, String uName, String uNumber,
	                                          String attack, String defense, String cAttack, String cDefense, String cost ) {
		this.bName = bName;
		this.bType = bType;
		this.tier = tier;
		this.level = level;
		this.uName = uName;
		this.uNumber = uNumber;
		this.attack = attack;
		this.defense = defense;
		this.cAttack = cAttack;
		this.cDefense = cDefense;
		this.cost = cost;
	}

	public String getbName() {
		return bName;
	}

	public String getbType() {
		return bType;
	}

	public String getTier() {
		return tier;
	}

	public String getLevel() {
		return level;
	}

	public String getuName() {
		return uName;
	}

	public String getuNumber() {
		return uNumber;
	}

	public String getAttack() {
		return attack;
	}

	public String getDefense() {
		return defense;
	}

	public String getcAttack() {
		return cAttack;
	}

	public String getcDefense() {
		return cDefense;
	}

	public String getCost() {
		return cost;
	}


}