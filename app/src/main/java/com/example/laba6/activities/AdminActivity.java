package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;

import com.example.laba6.R;
import com.example.laba6.adapters.MainAdapter;
import com.example.laba6.adapters.SecondAdapter;
import com.example.laba6.repositories.ProductRepo;



public class AdminActivity extends AppCompatActivity implements SecondAdapter.OnProductClickListener {
    private ViewPager2 viewPager2;
    private int currPositionViewPager = 0;
    Button addProdButton;

    ProductRepo productRepo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        productRepo = new ProductRepo(this.getApplicationContext());

        viewPager2 = findViewById(R.id.admin_activity_view_pager);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                currPositionViewPager = position;
            }
        });

        productRepo.getProductList().observe(this, productList -> {
            SecondAdapter secondAdapter = new SecondAdapter(productList, this);
            viewPager2.setAdapter(secondAdapter);
            viewPager2.setCurrentItem(currPositionViewPager, false);
        });

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        addProdButton = findViewById(R.id.addProdBtn);

        addProdButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddProductActivity.class);
            finish();
            startActivity(intent);
        });

    }

    @Override
    public void onProductDeleteCLick(int id) {
        productRepo.deleteProduct(id);
    }

    @Override
    public void onProductEditClick(int id) {
        Log.v("APP", "clicked at: " + id);
        Intent intent = new Intent(AdminActivity.this, DeleteAdminActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
