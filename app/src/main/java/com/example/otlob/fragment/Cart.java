package com.example.otlob.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otlob.R;
import com.example.otlob.databinding.FragmentCartBinding;
import com.example.otlob.viewmodel.FragmentViewModel;


public class Cart extends Fragment {

    private FragmentViewModel model;
    private FragmentCartBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ViewModelProvider(this).get(FragmentViewModel.class);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);

        View view = binding.getRoot();

        return view;
    }
}