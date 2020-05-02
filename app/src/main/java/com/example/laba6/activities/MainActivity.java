package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;

import com.example.laba6.R;
import com.example.laba6.adapters.MainAdapter;

import com.example.laba6.db.entities.OrderEntity;
import com.example.laba6.db.entities.ProductEntity;
import com.example.laba6.repositories.OrderRepo;
import com.example.laba6.repositories.ProductRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnProductClickListener {
    private Button btnCart;
    private Button btnAdmin;
    private Button addToCart;
    private ViewPager2 viewPager2;

    private ProductRepo productRepo ;
    private OrderRepo orderRepo;

    private int currPositionViewPager = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRepo = new ProductRepo(this.getApplicationContext());
        orderRepo = new OrderRepo((this.getApplicationContext()));

        viewPager2 = findViewById(R.id.main_activity_view_pager);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                currPositionViewPager = position;
            }
        });

        productRepo.getProductList().observe(this, (productList) -> {
                   MainAdapter mainAdapter = new MainAdapter(productList, this);
                   viewPager2.setAdapter(mainAdapter);
                   viewPager2.setCurrentItem(currPositionViewPager, false);
        });


        addListenerOnButton();
    }


    public void addListenerOnButton() {

        btnCart = findViewById(R.id.goToCart);
        btnAdmin = findViewById(R.id.goToAdminPanel);

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void addCartClickListener(int id) {

        OrderEntity orderEntity = new OrderEntity();

        productRepo.getProductById(id).observe(this, productEntity -> {

            if(productEntity.getCount() <= 1){
                productRepo.deleteProduct(productEntity.getId());

                addProductToOrder(productEntity, orderEntity);

            }else {
                productEntity.setCount(productEntity.getCount() -1);

                orderEntity.setParent_id(productEntity.getId());
                orderEntity.setCount(1);
                orderEntity.setDescription(productEntity.getDescription());
                orderEntity.setName(productEntity.getName());
                orderEntity.setPrice(productEntity.getPrice());

                productRepo.updateProduct(productEntity);

                addProductToOrder(productEntity, orderEntity);
            }

        });

    }

    private void addProductToOrder(ProductEntity productEntity, OrderEntity orderEntity){
        orderRepo.getOrderById(productEntity.getId()).observe(this, orderEntity1 -> {

            if(orderEntity1 == null){
                orderRepo.insertOrder(orderEntity);
            }else {
                orderEntity1.setCount(orderEntity1.getCount() +1);
                orderRepo.updateOrder(orderEntity1);
            }
        });
    }

}
