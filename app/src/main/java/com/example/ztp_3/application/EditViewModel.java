package com.example.ztp_3.application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ztp_3.domain.Product;

public class EditViewModel extends ViewModel {

    private final MutableLiveData<Product> editedProductLiveData = new MutableLiveData<>();

    public LiveData<Product> getEditedProduct() {
        return editedProductLiveData;
    }

    public void setEditedProduct(Product product) {
        editedProductLiveData.setValue(product);
    }
}