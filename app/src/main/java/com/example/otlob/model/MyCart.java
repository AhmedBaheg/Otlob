package com.example.otlob.model;

public class MyCart {
    private String orderName, size, imgUrl, id;
    private int itemPrice, piece, totalItemPrice;

    public MyCart(String orderName, String size, String imgUrl, String id, int itemPrice, int piece, int totalItemPrice) {
        this.orderName = orderName;
        this.size = size;
        this.imgUrl = imgUrl;
        this.id = id;
        this.itemPrice = itemPrice;
        this.piece = piece;
        this.totalItemPrice = totalItemPrice;
    }

    public MyCart() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
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
