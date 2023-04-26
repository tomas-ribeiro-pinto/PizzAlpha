package com.tomaspinto.pizzalpha.ui.tableMenu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TableViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}