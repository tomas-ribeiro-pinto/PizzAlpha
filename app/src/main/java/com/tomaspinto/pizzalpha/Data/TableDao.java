package com.tomaspinto.pizzalpha.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface TableDao {
    @Insert
    void insertAll(Table... tables);

    @Insert
    void insert(Table table);

    @Delete
    void delete(Table table);

    @Query("SELECT * FROM `table`")
    List<Table> getAll();

    @Query("SELECT * FROM `order` WHERE orderId = :id")
    Table getTable(int id);
}
