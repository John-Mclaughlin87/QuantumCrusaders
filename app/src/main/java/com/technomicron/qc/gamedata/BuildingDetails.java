package com.technomicron.qc.gamedata;

import java.util.ArrayList;

public class BuildingDetails {

	public ArrayList<String> getBuildingDetails(int building){
		ArrayList<String> buildingDetails;
		buildingDetails = new ArrayList<>();
		switch (building){
			case 1:{
				buildingDetails.add("Command");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1200");
				buildingDetails.add("800");
				buildingDetails.add("1200");
				buildingDetails.add("800");
				buildingDetails.add("10000");
			}
			case 2:{
				buildingDetails.add("Command");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1600");
				buildingDetails.add("1200");
				buildingDetails.add("1600");
				buildingDetails.add("1600");
				buildingDetails.add("10000");
			}
			case 3:{
				buildingDetails.add("Command");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("2000");
				buildingDetails.add("1600");
				buildingDetails.add("2000");
				buildingDetails.add("1600");
				buildingDetails.add("10000");
			}
			case 4:{
				buildingDetails.add("Command");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1000");
				buildingDetails.add("1000");
				buildingDetails.add("1000");
				buildingDetails.add("1000");
				buildingDetails.add("10000");
			}
			case 5:{
				buildingDetails.add("Command");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1400");
				buildingDetails.add("1400");
				buildingDetails.add("1400");
				buildingDetails.add("1400");
				buildingDetails.add("10000");
			}
			case 6:{
				buildingDetails.add("Command");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1800");
				buildingDetails.add("1800");
				buildingDetails.add("1800");
				buildingDetails.add("1800");
				buildingDetails.add("10000");
			}
			case 7:{
				buildingDetails.add("Command");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("800");
				buildingDetails.add("1200");
				buildingDetails.add("800");
				buildingDetails.add("1200");
				buildingDetails.add("10000");
			}
			case 8:{
				buildingDetails.add("Command");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1200");
				buildingDetails.add("1600");
				buildingDetails.add("1200");
				buildingDetails.add("1600");
				buildingDetails.add("10000");
			}
			case 9:{
				buildingDetails.add("Command");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add(null);
				buildingDetails.add(null);
				buildingDetails.add("1600");
				buildingDetails.add("2000");
				buildingDetails.add("1600");
				buildingDetails.add("2000");
				buildingDetails.add("10000");
			}
			case 10:{
				buildingDetails.add("Offensive Array");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add("Cutlass");
				buildingDetails.add("60");
				buildingDetails.add("120");
				buildingDetails.add("80");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 11: {
				buildingDetails.add("Offensive Array");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add("Cutlass");
				buildingDetails.add("100");
				buildingDetails.add("160");
				buildingDetails.add("100");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 12: {
				buildingDetails.add("Offensive Array");
				buildingDetails.add("Attack");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add("Cutlass");
				buildingDetails.add("140");
				buildingDetails.add("200");
				buildingDetails.add("120");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 13: {
				buildingDetails.add("Balanced Array");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add("Challenger");
				buildingDetails.add("60");
				buildingDetails.add("100");
				buildingDetails.add("100");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 14: {
				buildingDetails.add("Balanced Array");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add("Challenger");
				buildingDetails.add("100");
				buildingDetails.add("130");
				buildingDetails.add("130");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 15: {
				buildingDetails.add("Balanced Array");
				buildingDetails.add("Balanced");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add("Challenger");
				buildingDetails.add("140");
				buildingDetails.add("160");
				buildingDetails.add("160");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 16: {
				buildingDetails.add("Defensive Array");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("1");
				buildingDetails.add("Aegis");
				buildingDetails.add("60");
				buildingDetails.add("80");
				buildingDetails.add("120");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 17: {
				buildingDetails.add("Defensive Array");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("2");
				buildingDetails.add("Aegis");
				buildingDetails.add("100");
				buildingDetails.add("100");
				buildingDetails.add("160");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
			case 18: {
				buildingDetails.add("Defensive Array");
				buildingDetails.add("Defense");
				buildingDetails.add("1");
				buildingDetails.add("3");
				buildingDetails.add("Aegis");
				buildingDetails.add("140");
				buildingDetails.add("120");
				buildingDetails.add("200");
				buildingDetails.add("0");
				buildingDetails.add("0");
				buildingDetails.add("10000");
			}
		}
		return buildingDetails;
	}
}
