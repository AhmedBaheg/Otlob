package com.example.otlob.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.otlob.R;
import com.example.otlob.adapter.OrderAdapter;
import com.example.otlob.databinding.FragmentOrderBinding;
import com.example.otlob.model.Receipt;
import com.example.otlob.services.Theme;
import com.example.otlob.viewmodel.FragmentViewModel;

import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentViewModel model;
    private FragmentOrderBinding binding;
    private OrderAdapter adapter;

    private Theme theme = new Theme();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(FragmentViewModel.class);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        View view = binding.getRoot();

        FragmentViewModel.getINSTANCE().getItemToOrderInRecycler();

        binding.recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getContext()));


        FragmentViewModel.getMUTABLE_ORDER_RECYCLER().observe(getViewLifecycleOwner(), new Observer<List<Receipt>>() {
            @Override
            public void onChanged(List<Receipt> receipts) {
                adapter = new OrderAdapter(getContext(), receipts);
                binding.recyclerViewOrder.setAdapter(adapter);
                adapter.notifyDataSetChanged();
//                Log.i("TAG" , receipts.size()+ "");
            }
        });

        theme.theme(getContext(), binding.bgParent, R.drawable.cart, R.drawable.order_dark);

        return view;
    }
}