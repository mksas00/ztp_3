package com.example.ztp_3.application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ztp_3.domain.ProductRepository;

public class ProductViewModelFactory implements ViewModelProvider.Factory {

    private final ProductRepository productRepository;

    public ProductViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(productRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}