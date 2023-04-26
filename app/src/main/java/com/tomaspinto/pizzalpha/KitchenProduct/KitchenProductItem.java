package com.tomaspinto.pizzalpha.KitchenProduct;

public class KitchenProductItem {

    int quantity;
    String product;

    public KitchenProductItem(int quantity, String name)
    {
        this.quantity = quantity;
        this.product = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
