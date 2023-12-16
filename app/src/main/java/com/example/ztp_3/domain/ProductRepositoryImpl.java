package com.example.ztp_3.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ztp_3.infrastructure.ApiClient;
import com.example.ztp_3.infrastructure.ProductApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;


public class ProductRepositoryImpl implements ProductRepository {

    private ProductApiService productApiService;
    private static final String TAG = ProductRepositoryImpl.class.getSimpleName();


    public ProductRepositoryImpl() {
        productApiService = ApiClient.getProductApiService();
    }

    @Override
    public LiveData<List<Product>> getProducts(){
        final MutableLiveData<List<Product>> data = new MutableLiveData<>();
        productApiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.body()!=null){
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Error fetching products", t);
                data.setValue(null);
            }
        });

        return data;
    }

    @Override
    public LiveData<Product> getProductDetails(String productId) {
        final MutableLiveData<Product> data = new MutableLiveData<>();
        productApiService.getProductDetails(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.body()!=null){
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, "Error fetching product details", t);
                data.setValue(null);
            }
        });

        return data;
    }

    public void deleteProduct(String productId) {
        productApiService.deleteProduct(productId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.e(TAG, "Product deleted successfully");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error fetching product details", t);
            }
        });
    }

    @Override
    public void addProduct(Product product) {

        Call<Void> call = productApiService.addProduct(product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "Product added successfully");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error adding product", t);
            }
        });
    }

    @Override
    public void editProduct(Product product) {

        Call<Void> call = productApiService.editProduct(product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "Product edited successfully");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error editing product", t);
            }
        });
    }
}