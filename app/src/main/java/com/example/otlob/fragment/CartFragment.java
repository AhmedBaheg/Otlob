package com.example.otlob.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otlob.R;
import com.example.otlob.activity.Home;
import com.example.otlob.adapter.CartItemAdapter;
import com.example.otlob.databinding.FragmentCartBinding;
import com.example.otlob.model.MyCart;
import com.example.otlob.services.Theme;
import com.example.otlob.viewmodel.FragmentViewModel;

import java.util.List;


public class CartFragment extends Fragment {

    private FragmentViewModel model;
    private FragmentCartBinding binding;
    private CartItemAdapter adapter;

    private Theme theme = new Theme();

    int temp;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((Home) getActivity()).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

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
                adapter = new CartItemAdapter(getContext(), myCarts);
                binding.recyclerCart.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                for (MyCart cart : myCarts) {
                    temp += cart.getTotalItemPrice();
                }
                binding.tvTotalPrice.setText(String.valueOf(temp + " EGP"));
                temp = 0;

            }
        });

        binding.btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Home) getActivity()).loadFragment(new CategoryFragment());
                ((Home) getActivity()).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });

        binding.btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentViewModel.getINSTANCE().btnPurchase(binding.tvTotalPrice.getText().toString());
                ((Home) getActivity()).loadFragment(new CategoryFragment());
                ((Home) getActivity()).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });

        theme.theme(getContext(), binding.bgParent, R.drawable.cart, R.drawable.order_dark);

        return view;
    }
}