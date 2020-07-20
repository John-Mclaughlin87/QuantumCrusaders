package com.technomicron.qc.ui.profile;

		import androidx.lifecycle.LiveData;
		import androidx.lifecycle.MutableLiveData;
		import androidx.lifecycle.ViewModel;

public class Profile_Model extends ViewModel {

	private MutableLiveData<String> mText;

	public Profile_Model() {
		mText = new MutableLiveData<>();
		mText.setValue("This is Profile fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}