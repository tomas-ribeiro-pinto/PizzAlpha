package com.tomaspinto.pizzalpha.Slip;

public class SlipItem {

    int quantity;
    String product;
    double price;

    public SlipItem(int quantity, String name, double price)
    {
        this.quantity = quantity;
        this.product = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
