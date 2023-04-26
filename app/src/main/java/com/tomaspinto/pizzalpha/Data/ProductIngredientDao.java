package com.tomaspinto.pizzalpha.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface ProductIngredientDao {
    @Insert
    void insertAll(ProductIngredient... productIngredients);

    @Insert
    void insert(ProductIngredient productIngredient);

    @Delete
    void delete(ProductIngredient productIngredient);

    @Query("SELECT * FROM `productingredient`")
    List<ProductIngredient> getAll();

    @Query("SELECT * FROM `productingredient` WHERE p_productId = :id")
    List<ProductIngredient> getAll(int id);
}
