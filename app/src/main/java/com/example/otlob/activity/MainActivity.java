package com.example.otlob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.otlob.R;
import com.example.otlob.databinding.ActivityMainBinding;
import com.example.otlob.viewmodel.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        binding.btnSignIn.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register :
                startActivity(new Intent(com.example.otlob.activity.MainActivity.this , Register.class));
                finish();
                break;

            case R.id.btn_sign_in :
                startActivity(new Intent(com.example.otlob.activity.MainActivity.this , Login.class));
                finish();
                break;
        }
    }
}