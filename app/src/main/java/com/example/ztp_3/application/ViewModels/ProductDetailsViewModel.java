package com.example.ztp_3.application.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;

import java.util.List;

public class ProductDetailsViewModel extends ViewModel {
    private MutableLiveData<Product> productDetailsLiveData = new MutableLiveData<>();
    private final ProductRepository productRepository;

    public ProductDetailsViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void loadProductDetails(String productId) {
        productRepository.getProductDetails(productId).observeForever(product -> {
            productDetailsLiveData.setValue(product);
        });
    }

    public void deleteProduct(String productId) {
        productRepository.deleteProduct(productId);
    }

    // Getter method to expose LiveData to the activity
    public LiveData<Product> getProductDetails(String productId) {
        loadProductDetails(productId);
        return this.productDetailsLiveData;

    }
}