package com.example.otlob.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.otlob.R;

public class Theme {

    SharedPreferences sharedPreferences = null;

    private static final String DARK = "Dark";
    private static final String DARK_KEY = "DarkMode";

    public void theme(Context context, ConstraintLayout background, int lightImage, int darkImage ){

        sharedPreferences = context.getSharedPreferences(DARK, context.MODE_PRIVATE);
        final boolean booleanValue = sharedPreferences.getBoolean(DARK_KEY, false);

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            background.setBackground(ContextCompat.getDrawable(context, darkImage));
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            background.setBackground(ContextCompat.getDrawable(context, lightImage));
        }

    }

    public void theme(Context context, ConstraintLayout background, int lightImage, int darkImage, SwitchCompat switchCompat){

        sharedPreferences = context.getSharedPreferences(DARK, context.MODE_PRIVATE);
        final boolean booleanValue = sharedPreferences.getBoolean(DARK_KEY, false);

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchCompat.setChecked(true);
            background.setBackground(ContextCompat.getDrawable(context, darkImage));
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            switchCompat.setChecked(false);
            background.setBackground(ContextCompat.getDrawable(context, lightImage));
        }

    }

}
