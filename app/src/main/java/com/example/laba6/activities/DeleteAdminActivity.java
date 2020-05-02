package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba6.R;
import com.example.laba6.db.entities.ProductEntity;
import com.example.laba6.repositories.ProductRepo;

public class DeleteAdminActivity extends AppCompatActivity {
    private EditText name, description, count, price;

    private  ProductRepo productRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_admin);

         productRepo = new ProductRepo(this.getApplicationContext());

        name = findViewById(R.id.deleteName);
        description = findViewById(R.id.deleteDescription);
        count = findViewById(R.id.deleteCount);
        price = findViewById(R.id.deletePrice);

        Button updateBtn = findViewById(R.id.updateAdmin);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);

        productRepo.getProductById(id).observe(this, productEntity -> {
            name.setText(productEntity.getName());
            description.setText(productEntity.getDescription());
            count.setText(String.valueOf(productEntity.getCount()));
            price.setText(String.valueOf(productEntity.getPrice()));
        });

        updateBtn.setOnClickListener(v -> {
            checkFields(id);
        });

    }


    private void checkFields(int id){

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);


        if (count.getText().toString().isEmpty()) {
            Toast.makeText(this, "Count is empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productEntity.setCount(Integer.parseInt(count.getText().toString()));
        }

        if (price.getText().toString().isEmpty()) {
            Toast.makeText(this, "Price is empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productEntity.setPrice(Integer.parseInt(price.getText().toString()));
        }

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productEntity.setName(name.getText().toString());
        }

        if (description.getText().toString().isEmpty()) {
            Toast.makeText(this, "Description is empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productEntity.setDescription(description.getText().toString());
        }

        if (!name.getText().toString().isEmpty() && !description.getText().toString().isEmpty() && !count.getText().toString().isEmpty() && !price.getText().toString().isEmpty()) {

            Toast.makeText(this.getApplicationContext(), "Please, wait!", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                productRepo.updateProduct(productEntity);

                finish();

            }, 5000);
        }
    }


}