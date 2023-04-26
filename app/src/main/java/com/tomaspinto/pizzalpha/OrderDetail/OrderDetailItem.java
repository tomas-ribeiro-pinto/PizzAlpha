package com.tomaspinto.pizzalpha.OrderDetail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDetailItem {

    int id;
    Date date;
    double price;

    public OrderDetailItem(int quantity, Date date, double price)
    {
        this.id = quantity;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
