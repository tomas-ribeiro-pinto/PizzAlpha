package com.tomaspinto.pizzalpha.ui.kitchenMenu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KitchenViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public KitchenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}