package com.example.ztp_3.application.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private final ProductRepository productRepository;

    public ProductViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public LiveData<List<Product>> getProducts() {
        loadProducts();
        return products;
    }

    private void loadProducts() {
        productRepository.getProducts().observeForever(productList -> {
            products.setValue(productList);
        });
    }

    public void refreshProducts(){
        loadProducts();
    }
}

