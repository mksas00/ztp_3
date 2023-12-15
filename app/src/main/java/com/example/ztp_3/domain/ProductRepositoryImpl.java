package com.example.ztp_3.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ztp_3.infrastructure.ApiClient;
import com.example.ztp_3.infrastructure.ProductApiService;
import java.io.IOException;
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
}