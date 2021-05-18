package com.example.otlob.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.otlob.R;
import com.example.otlob.databinding.FragmentProfileBinding;
import com.example.otlob.services.Theme;
import com.example.otlob.viewmodel.FragmentViewModel;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private FragmentViewModel model;
    private FragmentProfileBinding binding;

    private SharedPreferences sharedPreferences = null;
    private Theme theme = new Theme();

    private static final String DARK = "Dark";
    private static final String DARK_KEY = "DarkMode";
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ViewModelProvider(this).get(FragmentViewModel.class);

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View view = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences(DARK, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        theme.theme(getContext(), binding.bgParent, R.drawable.profile, R.drawable.profile_dark, binding.Switch);

        binding.Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    binding.Switch.setChecked(true);
                    binding.bgParent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.profile_dark));
                    editor.putBoolean(DARK_KEY, true);

                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    binding.Switch.setChecked(false);
                    binding.bgParent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.profile));
                    editor.putBoolean(DARK_KEY, false);

                }
                editor.commit();
            }

        });

        return view;
    }

}