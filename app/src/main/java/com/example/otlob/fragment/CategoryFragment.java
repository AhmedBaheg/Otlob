package com.example.otlob.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otlob.R;
import com.example.otlob.activity.Home;
import com.example.otlob.services.Constants;


public class CategoryFragment extends Fragment {

    private ImageView img_pizza;
    TextView pizza;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        img_pizza = view.findViewById(R.id.img_pizza);
        pizza = view.findViewById(R.id.pizza);

        img_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Home) getActivity()).loadFragment(new PizzaFragment());
                Constants.CATEGORY_NAME = pizza.getText().toString();
            }
        });

        return view;
    }

}