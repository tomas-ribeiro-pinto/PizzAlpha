package com.tomaspinto.pizzalpha.MenuProduct;

public class MenuProductItem {

    String product;

    public MenuProductItem(String name, double price)
    {
        this.product = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
