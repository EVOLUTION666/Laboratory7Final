package com.example.laba6.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.laba6.db.entities.OrderEntity;

import java.util.List;

//Дао интерфейс содержит все запросы, которые будет использовать БД.

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProduct(OrderEntity orderEntity);

    @Query("SELECT * FROM orderentity WHERE parent_id = :parent_id")
    OrderEntity getOrderById(int parent_id);

    @Query("SELECT * FROM orderentity")
    LiveData<List<OrderEntity>> selectAllProducts() ;

    @Update
    void updateOrder(OrderEntity orderEntity);

    @Query("DELETE FROM orderentity WHERE id = :id")
    void deleteOrder(int id);

    @Query("DELETE FROM orderentity")
    void delete();

}
