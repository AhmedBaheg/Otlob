package com.example.otlob.fragment;


import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otlob.R;
import com.example.otlob.adapter.CategoryItemAdapter;
import com.example.otlob.databinding.FragmentPizzaBinding;
import com.example.otlob.model.CategoryItem;
import com.example.otlob.viewmodel.FragmentViewModel;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class PizzaFragment extends Fragment {

    private FragmentViewModel model;
    private FragmentPizzaBinding binding;

    private CategoryItemAdapter adapter;

    public PizzaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_pizza, container, false);


        model = new ViewModelProvider(this).get(FragmentViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza, container, false);
        View view = binding.getRoot();

        setSliderViews();
        FragmentViewModel.getINSTANCE().getItemInRecycler();

        binding.sliderLayout.setIndicatorAnimation(SliderLayout.Animations.SWAP); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FragmentViewModel.getMUTABLE_RECYCLER().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                adapter = new CategoryItemAdapter(getContext(), categoryItems, PizzaFragment.this);
                binding.recyclerView.setAdapter(adapter);
            }
        });


        return view;
    }

    public void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.pizza_slide1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.pizza_slide2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.pizza_slide3);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.pizza_slide4);
                    break;

                case 4:
                    sliderView.setImageDrawable(R.drawable.pizza_slide5);
                    break;

                case 5:
                    sliderView.setImageDrawable(R.drawable.pizza_slide6);
                    break;
            }


//            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                @Override
//                public void onSliderClick(SliderView sliderView) {
//                    Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                }
//            });

            //at last add this view in your layout :
            binding.sliderLayout.addSliderView(sliderView);
        }
    }

}