package com.example.ztp_3.application.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;

public class ProductAddEditViewModel extends ViewModel {

    private final ProductRepository productRepository;

    public ProductAddEditViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void editProduct(Product product) {
        productRepository.addProduct(product);
    }
}