package com.example.ztp_3.application.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.ztp_3.R;
import com.example.ztp_3.application.Factories.ViewModelFactory;
import com.example.ztp_3.application.ViewModels.ProductAddEditViewModel;
import com.example.ztp_3.domain.Product;
import com.example.ztp_3.domain.ProductRepository;
import com.example.ztp_3.domain.ProductRepositoryImpl;

public class ProductAddEditActivity extends AppCompatActivity {

    private EditText productNameEditText, productDescriptionEditText, productPriceEditText,
            productWeightEditText, productCategoryEditText, productQuantityEditText;
    private TextView productIdTextView;

    private ProductAddEditViewModel productAddEditViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add_edit);

        ProductRepository productRepository = new ProductRepositoryImpl();
        ViewModelFactory factory = new ViewModelFactory(productRepository);
        productAddEditViewModel = new ViewModelProvider(this, factory).get(ProductAddEditViewModel.class);

        productIdTextView = findViewById(R.id.addEditProdctId);
        productNameEditText = findViewById(R.id.addEditProductName);
        productDescriptionEditText = findViewById(R.id.addEditProductDescription);
        productPriceEditText = findViewById(R.id.addEditProductPrice);
        productWeightEditText = findViewById(R.id.addEditProductWeight);
        productCategoryEditText = findViewById(R.id.addEditProductCategory);
        productQuantityEditText = findViewById(R.id.addEditProductQuantity);

        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra("productId")!= null) {
            String productId = intent.getStringExtra("productId");
            String productName = intent.getStringExtra("productName");
            String productDescription = intent.getStringExtra("productDescription");
            double productPrice = Double.parseDouble(intent.getStringExtra("productPrice"));
            double productWeight = Double.parseDouble(intent.getStringExtra("productWeight"));
            String productCategory = intent.getStringExtra("productCategory");
            int productQuantity = Integer.parseInt(intent.getStringExtra("productQuantity"));

            productIdTextView.setText(productId);
            productNameEditText.setText(productName);
            productDescriptionEditText.setText(productDescription);
            productPriceEditText.setText(String.valueOf(productPrice));
            productWeightEditText.setText(String.valueOf(productWeight));
            productCategoryEditText.setText(productCategory);
            productQuantityEditText.setText(String.valueOf(productQuantity));
        }
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productId;
                if(productIdTextView.getText().toString().equals("")){
                    productId = null;
                }else {
                    productId = productIdTextView.getText().toString();
                }
                String productName = productNameEditText.getText().toString();
                String productDescription = productDescriptionEditText.getText().toString();
                double productPrice = Double.parseDouble(productPriceEditText.getText().toString());
                double productWeight = Double.parseDouble(productWeightEditText.getText().toString());
                String productCategory = productCategoryEditText.getText().toString();
                int productQuantity = Integer.parseInt(productQuantityEditText.getText().toString());

                Product newProduct = new Product(productId, productName, productDescription, productPrice,
                        productWeight, productCategory, productQuantity);

                if(productId == null) {
                    productAddEditViewModel.addProduct(newProduct);
                }
                else {
                    productAddEditViewModel.editProduct(newProduct);
                }
                finish();
            }
        });
    }
}
