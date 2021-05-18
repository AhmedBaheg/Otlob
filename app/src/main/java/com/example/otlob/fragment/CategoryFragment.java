package com.example.otlob.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otlob.R;
import com.example.otlob.activity.Home;
import com.example.otlob.services.Constants;
import com.example.otlob.services.Theme;


public class CategoryFragment extends Fragment {

    private ImageView img_pizza;
    private TextView pizza;
    private ConstraintLayout bg_Parent;

    private Theme theme = new Theme();

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
        pizza = view.findViewById(R.id.pizza);
        bg_Parent = view.findViewById(R.id.bg_parent);

        img_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Home) getActivity()).loadFragment(new PizzaFragment());
                Constants.CATEGORY_NAME = pizza.getText().toString();
            }
        });

        theme.theme(getContext(), bg_Parent, R.drawable.category, R.drawable.category_dark);

        return view;
    }

}