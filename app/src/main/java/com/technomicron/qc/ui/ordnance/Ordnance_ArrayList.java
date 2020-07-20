package com.technomicron.qc.ui.ordnance;

import java.util.ArrayList;

public class Ordnance_ArrayList<QCOrdnance_ViewModel> extends ArrayList<QCOrdnance_ViewModel> {
	@Override
	public boolean add(QCOrdnance_ViewModel e) {
		if (this.size() < 5) {
			super.add(e);
		} else {
			this.remove(0);
			super.add(e);
		}
		return false;
	}
	@Override
	public void add(int index, QCOrdnance_ViewModel element) {
		if (this.size() < 5
		) {
			super.add(index, element);
		}
	}

	@Override
	public void clear() {
		super.clear();
	}
}

