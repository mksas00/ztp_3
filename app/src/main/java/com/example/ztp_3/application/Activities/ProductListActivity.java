package com.example.ztp_3.application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ztp_3.R;
import com.example.ztp_3.application.Adapters.ProductAdapter;
import com.example.ztp_3.application.Factories.ViewModelFactory;
import com.example.ztp_3.application.ViewModels.ProductViewModel;
import com.example.ztp_3.domain.ProductRepository;
import com.example.ztp_3.domain.ProductRepositoryImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
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

        ViewModelFactory factory = new ViewModelFactory(productRepository);

        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, products -> {
            adapter.setProductList(products);
            adapter.notifyDataSetChanged();
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, ProductAddEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        productViewModel.refreshProducts();
    }
}