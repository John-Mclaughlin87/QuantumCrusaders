package com.technomicron.qc.ui.freelance;

import java.util.ArrayList;

public class Freelance_ArrayList<Freelance_Model> extends ArrayList<Freelance_Model> {
	@Override
	public boolean add(Freelance_Model e) {
		super.add(e);
		return false;
	}
	@Override
	public void add(int index, Freelance_Model element) {

		super.add(index, element);
	}
}