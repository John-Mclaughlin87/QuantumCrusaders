package com.technomicron.qc.ui.hangar.alerts;

import java.util.ArrayList;

public class Alerts_ArrayList<Hangar_Fragment_Combat_Model> extends ArrayList<Hangar_Fragment_Combat_Model> {
	@Override
	public boolean add(Hangar_Fragment_Combat_Model e) {
		super.add(e);
		return false;
	}
	@Override
	public void add(int index, Hangar_Fragment_Combat_Model element) {

		super.add(index, element);
	}
}