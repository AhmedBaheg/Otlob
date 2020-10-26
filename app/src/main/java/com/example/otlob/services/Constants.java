package com.example.otlob.services;

import com.google.firebase.auth.FirebaseAuth;

public class Constants {

    public static String USER = "User";

    public static String getUID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
