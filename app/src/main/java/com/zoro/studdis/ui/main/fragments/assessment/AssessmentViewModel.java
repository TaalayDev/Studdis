package com.zoro.studdis.ui.main.fragments.assessment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssessmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AssessmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is assessment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}