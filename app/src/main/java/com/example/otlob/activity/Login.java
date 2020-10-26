package com.example.otlob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.otlob.R;
import com.example.otlob.databinding.ActivityLoginBinding;
import com.example.otlob.viewmodel.UserViewModel;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        model = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_login);



    }
}