package com.example.otlob.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.otlob.R;
import com.example.otlob.databinding.FragmentProfileBinding;
import com.example.otlob.model.UserProfile;
import com.example.otlob.services.Theme;
import com.example.otlob.viewmodel.FragmentViewModel;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private FragmentViewModel model;
    public FragmentProfileBinding binding;

    private SharedPreferences sharedPreferences = null;
    private Theme theme = new Theme();

    private static final String DARK = "Dark";
    private static final String DARK_KEY = "DarkMode";

    private Uri uri;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentViewModel.getINSTANCE().returnData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ViewModelProvider(this).get(FragmentViewModel.class);

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View view = binding.getRoot();

        FragmentViewModel.getINSTANCE().returnData();

        binding.imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setAspectRatio(1, 1)
                        .setAutoZoomEnabled(true)
                        .start(getContext(), ProfileFragment.this);
            }
        });

        FragmentViewModel.getMUTABLE_RETURN_DATA().observe(getViewLifecycleOwner(), new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                binding.profileName.setText(userProfile.getFullName());
                binding.profileEmail.setText(userProfile.getEmail());
                binding.profilePhone.setText(userProfile.getPhoneNumber());
                Picasso.get().load(userProfile.getImgUrl())
                        .placeholder(R.drawable.default_pic_user)
                        .error(R.drawable.default_pic_user)
                        .into(binding.imgUserProfile);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switchTheme();



    }

    private void switchTheme() {
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == Activity.RESULT_OK) {

                if (result != null){

                    uri = result.getUri();

                    FragmentViewModel.getINSTANCE().uploadImgUserProfileOnDB(getContext(), uri);

                }

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }

    }
}