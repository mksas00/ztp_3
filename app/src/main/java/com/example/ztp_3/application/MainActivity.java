package com.example.ztp_3.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ztp_3.R;
import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;
import com.example.ztp_3.domain.ProductRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ProductAdapter adapter = new ProductAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductRepository productRepository = new ProductRepositoryImpl();

        ProductViewModelFactory factory = new ProductViewModelFactory(productRepository);

        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, products -> {
            adapter.setProductList(products);
            adapter.notifyDataSetChanged();
        });
    }
}