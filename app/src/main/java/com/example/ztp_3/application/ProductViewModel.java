package com.example.ztp_3.application;

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
        // Load or fetch products from your data source (e.g., database, network)
        // For simplicity, let's assume you have a method loadProducts() to load products.
        loadProducts();
        return products;
    }

    private void loadProducts() {
        productRepository.getProducts().observeForever(new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                if (productList != null) {
                    products.setValue(productList);
                } else {
                    products.setValue(null);
                }
            }
        });
    }
}

