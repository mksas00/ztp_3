package com.example.ztp_3.infrastructure;

import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiService {

    @GET("products")
    Call<List<Product>> getProducts();
}
