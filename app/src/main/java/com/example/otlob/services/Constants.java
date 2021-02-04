package com.example.otlob.services;

import com.google.firebase.auth.FirebaseAuth;

public class Constants {

    public static String USER = "User";
    public static String CATEGORY = "Category";
    public static String SIZE = "Size";
    public static String CART = "Cart";
    public static String CATEGORY_NAME;
    public static String CATEGORY_ITEM_NAME;
    public static String ITEM_SIZE;
    public static String CART_KEY;
    public static String imgUrl;

    public static String getUID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
