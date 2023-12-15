package com.example.ztp_3.domain;

import androidx.lifecycle.LiveData;

import com.example.ztp_3.domain.Product;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {

    LiveData<List<Product>> getProducts();
}