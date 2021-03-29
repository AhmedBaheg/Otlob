package com.example.otlob.model;

public class Receipt {

    private String key, totalOrderPrice;

    public Receipt(String key, String totalOrderPrice) {
        this.key = key;
        this.totalOrderPrice = totalOrderPrice;
    }

    public Receipt() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(String totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }
}
