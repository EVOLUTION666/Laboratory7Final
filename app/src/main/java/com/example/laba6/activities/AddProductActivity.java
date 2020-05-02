package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba6.R;
import com.example.laba6.db.entities.ProductEntity;
import com.example.laba6.repositories.ProductRepo;

public class AddProductActivity extends AppCompatActivity {

    private Button addNewProduct;
    private EditText name, price, description, count;

    private  ProductRepo productRepo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_product);

        productRepo = new ProductRepo(this.getApplicationContext());

        addNewProduct =  findViewById(R.id.addNewProduct);
        count =  findViewById(R.id.productCount);
        description =  findViewById(R.id.productDescription);
        price =  findViewById(R.id.productPrice);
        name =  findViewById(R.id.productName);

        addNewProduct.setOnClickListener(v -> {

            checkFields();

        });

    }

    private void checkFields() {

        if (name.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (description.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Description is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (count.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Count is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (price.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Price is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(name.getText().toString());
        productEntity.setDescription(description.getText().toString());
        productEntity.setCount( Integer.parseInt(count.getText().toString()));
        productEntity.setPrice(Integer.parseInt(price.getText().toString()));

        productRepo.insertProduct(productEntity);

        Intent intent = new Intent(AddProductActivity.this, AdminActivity.class);
        finish();
        startActivity(intent);

    }

}