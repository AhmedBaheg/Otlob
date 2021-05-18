package com.example.otlob.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.otlob.R;
import com.example.otlob.adapter.OrderAdapter;
import com.example.otlob.adapter.SubOrderAdapter;
import com.example.otlob.databinding.FragmentOrderBinding;
import com.example.otlob.model.Receipt;
import com.example.otlob.viewmodel.FragmentViewModel;

import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentViewModel model;
    private FragmentOrderBinding binding;
    private OrderAdapter adapter;


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

        return view;
    }
}