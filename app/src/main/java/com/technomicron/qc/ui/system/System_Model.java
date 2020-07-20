package com.technomicron.qc.ui.system;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class System_Model extends ViewModel {

    private MutableLiveData<String> mText;

    public System_Model() {
        mText = new MutableLiveData<>();
        mText.setValue("This is system fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}