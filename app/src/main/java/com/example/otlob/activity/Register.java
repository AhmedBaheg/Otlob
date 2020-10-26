package com.example.otlob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.otlob.R;
import com.example.otlob.databinding.ActivityRegisterBinding;
import com.example.otlob.services.Validation;
import com.example.otlob.viewmodel.UserViewModel;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    public UserViewModel model;

    private String str_FullName, str_Email, str_Password, str_PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        model = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(Register.this, R.layout.activity_register);


        binding.btnRegisterNow.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        str_FullName = binding.edFullName.getEditText().getText().toString();
        str_Email = binding.edEmail.getEditText().getText().toString();
        str_Password = binding.edPassword.getEditText().getText().toString();
        str_PhoneNumber = binding.edPhoneNumber.getEditText().getText().toString();

        if (!Validation.validationFullName(str_FullName , binding.edFullName)){
            binding.edFullName.requestFocus();
        }else if (!Validation.validationEmail(str_Email , binding.edEmail)){
            binding.edEmail.requestFocus();
        }else if (!Validation.validationPassword(str_Password , binding.edPassword)){
            binding.edPassword.requestFocus();
        }else if (!Validation.validationPhoneNumber(str_PhoneNumber , binding.edPhoneNumber)){
            binding.edPhoneNumber.requestFocus();
        }else {
            UserViewModel.getINSTANCE().register(str_FullName, str_Email, str_Password, str_PhoneNumber, this);
            finish();
        }
    }
}