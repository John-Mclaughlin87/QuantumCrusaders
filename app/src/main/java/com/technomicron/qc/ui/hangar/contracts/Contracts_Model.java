package com.technomicron.qc.ui.hangar.contracts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Contracts_Model extends ViewModel {

	private MutableLiveData<String> mText;

	public Contracts_Model() {
		mText = new MutableLiveData<>();
		mText.setValue("This is Contracts fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}