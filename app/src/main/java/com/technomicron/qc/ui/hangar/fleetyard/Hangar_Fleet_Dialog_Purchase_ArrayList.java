package com.technomicron.qc.ui.hangar.fleetyard;

import java.util.ArrayList;

public class Hangar_Fleet_Dialog_Purchase_ArrayList<Hangar_Fleet_Dialog_Purchase_Model> extends ArrayList<Hangar_Fleet_Dialog_Purchase_Model> {
	@Override
	public boolean add(Hangar_Fleet_Dialog_Purchase_Model e) {
		super.add(e);
		return false;
	}
	@Override
	public void add(int index, Hangar_Fleet_Dialog_Purchase_Model element) {
		super.add(index, element);
	}
}