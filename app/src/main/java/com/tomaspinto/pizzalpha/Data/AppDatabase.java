package com.tomaspinto.pizzalpha.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ingredient.class, Order.class, OrderProduct.class, Product.class, ProductIngredient.class, Table.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract IngredientDao ingredientDao();
    public abstract OrderDao orderDao();
    public abstract ProductDao productDao();
    public abstract OrderProductDao orderProductDao();
    public abstract ProductIngredientDao productIngredientDao();
    public abstract TableDao tableDao();

    private static volatile AppDatabase appDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
