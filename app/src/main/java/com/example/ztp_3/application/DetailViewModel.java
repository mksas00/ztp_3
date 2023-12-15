package com.example.ztp_3.application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ztp_3.domain.Product;

public class DetailViewModel extends ViewModel {

    private final MutableLiveData<Product> selectedProductLiveData = new MutableLiveData<>();

    public LiveData<Product> getSelectedProduct() {
        return selectedProductLiveData;
    }

    public void setSelectedProduct(Product product) {
        selectedProductLiveData.setValue(product);
    }
}