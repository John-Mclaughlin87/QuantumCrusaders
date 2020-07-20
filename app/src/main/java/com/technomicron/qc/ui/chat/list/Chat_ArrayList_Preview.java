package com.technomicron.qc.ui.chat.list;

import java.util.ArrayList;

public class Chat_ArrayList_Preview<QCChatModel> extends ArrayList<QCChatModel> {
	@Override
	public boolean add(QCChatModel e) {
		if (this.size() < 10) {
			super.add(e);
		} else {
			this.remove(0);
			super.add(e);
		}
		return false;
	}
	@Override
	public void add(int index, QCChatModel element) {
		if (this.size() < 10) {
			super.add(index, element);
		}
	}
}

