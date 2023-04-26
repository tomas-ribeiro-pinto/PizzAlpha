package com.tomaspinto.pizzalpha.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertAll(Order... orders);

    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM `order`")
    List<Order> getAll();

    @Query("SELECT * FROM `order` WHERE orderId = :id")
    Order getOrder(int id);

    @Query("UPDATE `order` SET is_cooked=:is_cooked WHERE orderId = :id")
    void updateOrder(boolean is_cooked, int id);
}
