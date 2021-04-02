package com.example.otlob.model;

import java.util.ArrayList;

public class Receipt {

    private String key, totalOrderPrice, orderName, size, imgUrl;
    private int idOrder, piece, totalItemPrice;


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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public int getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(int totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
