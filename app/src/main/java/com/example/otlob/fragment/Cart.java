package com.example.otlob.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otlob.R;
import com.example.otlob.adapter.CartAdapter;
import com.example.otlob.databinding.FragmentCartBinding;
import com.example.otlob.model.MyCart;
import com.example.otlob.viewmodel.FragmentViewModel;

import java.util.List;


public class Cart extends Fragment {

    private FragmentViewModel model;
    private FragmentCartBinding binding;
    private CartAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ViewModelProvider(this).get(FragmentViewModel.class);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        View view = binding.getRoot();


        FragmentViewModel.getINSTANCE().getItemToMyCartInRecycler();
        binding.recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        FragmentViewModel.getMUTABLE_CART_RECYCLER().observe(getViewLifecycleOwner(), new Observer<List<MyCart>>() {
            @Override
            public void onChanged(List<MyCart> myCarts) {
                adapter = new CartAdapter(getContext(), myCarts);
                binding.recyclerCart.setAdapter(adapter);
            }
        });

        return view;
    }
}