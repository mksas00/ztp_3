package com.example.ztp_3.infrastructure;

import com.example.ztp_3.domain.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApiService {

    @GET("products")
    Call<List<Product>> getProducts();
    @GET("products/{productId}")
    Call<Product> getProductDetails(@Path("productId") String productId);
    @DELETE("products/{productId}")
    Call<Void> deleteProduct(@Path("productId") String productId);
    @POST("products")
    Call<Void> addProduct(@Body Product product);
    @PUT("products")
    Call<Void> editProduct(@Body Product product);


}
