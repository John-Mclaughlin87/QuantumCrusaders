package com.technomicron.qc.ui.organization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Organization_Model extends ViewModel {

    private MutableLiveData<String> mText;

    public Organization_Model() {
        mText = new MutableLiveData<>();
        mText.setValue("This is organization fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}