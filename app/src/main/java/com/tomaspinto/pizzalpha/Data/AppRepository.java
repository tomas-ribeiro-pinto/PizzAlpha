package com.tomaspinto.pizzalpha.Data;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class AppRepository {
    AppDatabase appDatabase;
    IngredientDao ingredientDao;
    OrderDao orderDao;
    ProductDao productDao;
    OrderProductDao orderProductDao;
    ProductIngredientDao productIngredientDao;
    TableDao tableDao;
    private List<Order> orderList;
    private List<Product> productList;
    private List<Ingredient> ingredientList;
    private List<OrderProduct> orderProductList;
    private List<ProductIngredient> productIngredientList;
    private List<Table> tableList;

    public AppRepository(AppDatabase db) {
        appDatabase = db;
        orderDao = appDatabase.orderDao();
        productDao = appDatabase.productDao();
        ingredientDao = appDatabase.ingredientDao();
        orderProductDao = appDatabase.orderProductDao();
        productIngredientDao = appDatabase.productIngredientDao();
        tableDao = appDatabase.tableDao();

        orderList = orderDao.getAll();
        ingredientList = ingredientDao.getAll();
        productList = productDao.getAll();
        tableList = tableDao.getAll();
        orderProductList = orderProductDao.getAll();
        productIngredientList = productIngredientDao.getAll();
    }

    public void insert(Order order) {
        appDatabase.orderDao().insert(order);
    }
    public void insert(Ingredient ingredient) {
        appDatabase.ingredientDao().insert(ingredient);
    }
    public void insert(Product product) {
        appDatabase.productDao().insert(product);
    }
    public void insert(OrderProduct orderProduct) {
        appDatabase.orderProductDao().insert(orderProduct);
    }
    public void insert(ProductIngredient productIngredient) {
        appDatabase.productIngredientDao().insert(productIngredient);
    }

    public void insert(Table table) {
        appDatabase.tableDao().insert(table);
    }

    public List<Order> getOrderList() {
        orderList = orderDao.getAll();
        return orderList;
    }

    public List<Product> getProductList() {
        productList = productDao.getAll();
        return productList;
    }

    public List<Ingredient> getIngredientList() {
        ingredientList = ingredientDao.getAll();
        return ingredientList;
    }

    public List<ProductIngredient> getProductIngredientList() {
        productIngredientList = productIngredientDao.getAll();
        return productIngredientList;
    }

    public List<OrderProduct> getOrderProductList() {
        orderProductList = orderProductDao.getAll();
        return orderProductList;
    }

    public List<Table> getTableList() {
        tableList = tableDao.getAll();
        return tableList;
    }

    public Table getTable(int id) {
        return tableList.get(id-1);
    }

    public Product getProduct(int id) {
        return productList.get(id-1);
    }
}