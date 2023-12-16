package com.example.ztp_3.application.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ztp_3.R;
import com.example.ztp_3.application.Activities.ProductDetailsActivity;
import com.example.ztp_3.domain.Product;

import java.util.List;
import java.util.function.Consumer;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void removeProduct(int position) {
        if (productList != null && position >= 0 && position < productList.size()) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productIdTextView;
        private final TextView productNameTextView;
        private final TextView productPriceTextView;
        private final TextView productCategoryTextView;
        private final TextView productQuantityTextView;
        private final Context context;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productIdTextView = itemView.findViewById(R.id.productId);
            productNameTextView = itemView.findViewById(R.id.productName);
            productPriceTextView = itemView.findViewById(R.id.productPrice);
            productCategoryTextView = itemView.findViewById(R.id.productCategory);
            productQuantityTextView = itemView.findViewById(R.id.productQuantity);
            context = itemView.getContext();

            itemView.setOnClickListener(v -> {

                String productId = productIdTextView.getText().toString();

                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productId", productId);
                context.startActivity(intent);
            });
        }

        public void bind(Product product) {
            productIdTextView.setText(product.getId());
            productNameTextView.setText(product.getName());
            productPriceTextView.setText(Double.toString(product.getPrice()));
            productCategoryTextView.setText(product.getCategory());
            productQuantityTextView.setText(Integer.toString(product.getQuantity()));
        }
    }
}