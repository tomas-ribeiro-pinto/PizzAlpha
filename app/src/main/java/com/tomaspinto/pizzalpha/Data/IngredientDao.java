package com.tomaspinto.pizzalpha.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface IngredientDao {
    @Insert
    void insertAll(Ingredient... ingredients);

    @Insert
    void insert(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT * FROM `ingredient`")
    List<Ingredient> getAll();

    @Query("SELECT * FROM `ingredient` WHERE ingredientId = :id")
    Ingredient get(int id);
}
