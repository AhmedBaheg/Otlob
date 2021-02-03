package com.example.otlob.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otlob.R;
import com.example.otlob.activity.Home;
import com.example.otlob.databinding.FragmentItemBinding;
import com.example.otlob.model.CategoryItem;
import com.example.otlob.services.Constants;
import com.example.otlob.viewmodel.FragmentViewModel;
import com.squareup.picasso.Picasso;

public class Item extends Fragment {

    private int count;

    private FragmentViewModel model;
    private FragmentItemBinding binding;

    public Item() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(FragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);

//        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item, container, false);
        View view = binding.getRoot();

        returnData();

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = binding.tvSize.getText().toString();

                if (!temp.equalsIgnoreCase("Choose Size")) {
                    ((Home) getActivity()).loadFragment(new Cart());
                } else {
                    Toast.makeText(getContext(), "Please choose your favourite size", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void returnData() {

        FragmentViewModel.getINSTANCE().getItem();

        FragmentViewModel.getMUTABLE_ITEM().observe(getViewLifecycleOwner(), new Observer<CategoryItem>() {
            @Override
            public void onChanged(CategoryItem categoryItem) {
                binding.tvItemName.setText(categoryItem.getName());
                Picasso.get()
                        .load(categoryItem.getImgUrl())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(binding.imgItem);
            }
        });

        binding.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSizeItemRight();
                FragmentViewModel.getINSTANCE().getItemSize();
            }
        });

        binding.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSizeItemLeft();
                FragmentViewModel.getINSTANCE().getItemSize();
            }
        });

        FragmentViewModel.getMUTABLE_SIZE().observe(getViewLifecycleOwner(), new Observer<CategoryItem>() {
            @Override
            public void onChanged(CategoryItem categoryItem) {
                binding.tvItemPrice.setText(categoryItem.getPrice() + " EGP");
            }
        });

    }

    private void getSizeItemRight() {

        switch (count) {
            case 0:
                binding.tvSize.setText("Medium");
                Constants.ITEM_SIZE = binding.tvSize.getText().toString();
                count++;
                break;
            case 1:
                binding.tvSize.setText("Large");
                Constants.ITEM_SIZE = binding.tvSize.getText().toString();
                count++;
                break;
            case 2:
                binding.tvSize.setText("Compo");
                Constants.ITEM_SIZE = binding.tvSize.getText().toString();
                count++;
                break;
            default:
                Toast.makeText(getContext(), "This is the largest size", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void getSizeItemLeft() {

        switch (count) {
            case 3:
                binding.tvSize.setText("Large");
                Constants.ITEM_SIZE = binding.tvSize.getText().toString();
                count--;
                break;
            case 2:
                binding.tvSize.setText("Medium");
                Constants.ITEM_SIZE = binding.tvSize.getText().toString();
                count--;
                break;
            default:
                Toast.makeText(getContext(), "This is the smallest size", Toast.LENGTH_LONG).show();
                break;
        }

    }

}
