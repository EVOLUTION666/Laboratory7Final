package com.example.laba6.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laba6.db.entities.ProductEntity;

import java.util.List;

//Дао интерфейс содержит все запросы, которые будет использовать БД.

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProduct(ProductEntity productEntity);

    @Query("SELECT * FROM productEntity")
    LiveData<List<ProductEntity>> selectAllProducts() ;

    @Query("SELECT * FROM productEntity WHERE id = :id")
    ProductEntity getProduct(int id);

    @Query("DELETE FROM productEntity WHERE id = :id")
    void deleteProduct(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProduct(ProductEntity productEntity);
}
