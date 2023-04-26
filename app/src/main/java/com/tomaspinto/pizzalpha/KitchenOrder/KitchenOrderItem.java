package com.tomaspinto.pizzalpha.KitchenOrder;

public class KitchenOrderItem {

    int id;
    String seconds;
    String table;

    public KitchenOrderItem(int id, String date, String price)
    {
        this.id = id;
        this.seconds = date;
        this.table = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
