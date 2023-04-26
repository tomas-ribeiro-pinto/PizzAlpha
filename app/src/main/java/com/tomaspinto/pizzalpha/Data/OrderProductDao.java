package com.tomaspinto.pizzalpha.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface OrderProductDao{
    @Insert
    void insertAll(OrderProduct... orderProducts);

    @Insert
    void insert(OrderProduct orderProduct);

    @Delete
    void delete(OrderProduct orderProduct);

    @Query("SELECT * FROM `orderproduct` WHERE o_orderId = :id")
    List<OrderProduct> getAll(int id);

    @Query("SELECT * FROM `orderproduct`")
    List<OrderProduct> getAll();
}
