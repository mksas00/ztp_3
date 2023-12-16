package com.example.ztp_3.application.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ztp_3.R;
import com.example.ztp_3.application.Factories.ViewModelFactory;
import com.example.ztp_3.application.ViewModels.ProductDetailsViewModel;
import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;
import com.example.ztp_3.domain.ProductRepositoryImpl;

public class ProductDetailsActivity extends AppCompatActivity {

    private ProductDetailsViewModel productDetailsViewModel;
    TextView productIdTextView;
    TextView productNameTextView;
    TextView productDescriptionTextView;
    TextView productPriceTextView;
    TextView productWeightTextView;
    TextView productCategoryTextView;
    TextView productQuantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        productIdTextView = findViewById(R.id.detailsProductId);
        productNameTextView = findViewById(R.id.detailsProductName);
        productDescriptionTextView = findViewById(R.id.detailsProductDescription);
        productPriceTextView = findViewById(R.id.detailsProductPrice);
        productWeightTextView = findViewById(R.id.detailsProductWeight);
        productCategoryTextView = findViewById(R.id.detailsProductCategory);
        productQuantityTextView = findViewById(R.id.detailsProductQuantity);

        String productId = getIntent().getStringExtra("productId");

        ProductRepository productRepository = new ProductRepositoryImpl();
        ViewModelFactory factory = new ViewModelFactory(productRepository);
        productDetailsViewModel = new ViewModelProvider(this, factory).get(ProductDetailsViewModel.class);

        productDetailsViewModel.getProductDetails(productId).observe(this, product -> {
            if (product != null) {
                displayProductDetails(product);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDetailsViewModel.deleteProduct(productId);
                finish();
            }
        });

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productId = productIdTextView.getText().toString();
                String productName = productNameTextView.getText().toString();
                String productDescription = productDescriptionTextView.getText().toString();
                String productPrice = productPriceTextView.getText().toString();
                String productWeight = productPriceTextView.getText().toString();
                String productCategory = productCategoryTextView.getText().toString();
                String productQuantity = productQuantityTextView.getText().toString();

                Intent editIntent = new Intent(ProductDetailsActivity.this, ProductAddEditActivity.class);
                editIntent.putExtra("productId", productId);
                editIntent.putExtra("productName", productName);
                editIntent.putExtra("productDescription", productDescription);
                editIntent.putExtra("productPrice", productPrice);
                editIntent.putExtra("productWeight", productWeight);
                editIntent.putExtra("productCategory", productCategory);
                editIntent.putExtra("productQuantity", productQuantity);

                startActivity(editIntent);
            }
        });
    }

    private void displayProductDetails(Product product) {
        productIdTextView.setText(String.valueOf(product.getId()));
        productNameTextView.setText(product.getName());
        productDescriptionTextView.setText(product.getDescription());
        productPriceTextView.setText(String.valueOf(product.getPrice()));
        productWeightTextView.setText(String.valueOf(product.getWeight()));
        productCategoryTextView.setText(product.getCategory());
        productQuantityTextView.setText(String.valueOf(product.getQuantity()));
    }
}