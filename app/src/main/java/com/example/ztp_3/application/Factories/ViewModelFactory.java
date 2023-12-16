package com.example.ztp_3.application.Factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ztp_3.application.ViewModels.ProductAddEditViewModel;
import com.example.ztp_3.application.ViewModels.ProductDetailsViewModel;
import com.example.ztp_3.application.ViewModels.ProductViewModel;
import com.example.ztp_3.domain.ProductRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepository productRepository;

    public ViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel.class)) {
            return (T) new ProductDetailsViewModel(productRepository);
        }
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(productRepository);
        }
        if (modelClass.isAssignableFrom(ProductAddEditViewModel.class)) {
            return (T) new ProductAddEditViewModel(productRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
