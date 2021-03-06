package com.example.otlob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.otlob.R;
import com.example.otlob.databinding.ActivityHomeBinding;
import com.example.otlob.fragment.CartFragment;
import com.example.otlob.fragment.CategoryFragment;
import com.example.otlob.fragment.OrderFragment;
import com.example.otlob.fragment.ProfileFragment;
import com.example.otlob.viewmodel.UserViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private FrameLayout container;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public ActivityHomeBinding binding;
    private UserViewModel model;

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        model = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initializeViews();
        createRequest();

        fragmentManager = getSupportFragmentManager();


        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar,
                R.string.openNavDrawer, R.string.closeNavDrawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new CategoryFragment());
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.home_toolbar);
        navigationView = findViewById(R.id.nav_menu);
    }

    public void loadFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void createRequest() {

        /** this allow to user choose any email to login by it */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.home_drawer:
                loadFragment(new CategoryFragment());
                break;
            case R.id.order_drawer:
                loadFragment(new OrderFragment());
                break;
            case R.id.menu_drawer:
                Toast.makeText(this, "Menu", Toast.LENGTH_LONG).show();
                break;
            case R.id.restaurant_drawer:
                Toast.makeText(this, "Restaurant", Toast.LENGTH_LONG).show();
                break;
            case R.id.favourite_drawer:
                Toast.makeText(this, "Favourite", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_cart_drawer:
                loadFragment(new CartFragment());
                break;
            case R.id.profile_drawer:
                loadFragment(new ProfileFragment());
                break;
            case R.id.sign_out_drawer:
                UserViewModel.getINSTANCE().signOut(googleSignInClient, this);
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        menuItem.setChecked(true);
        return true;
    }

    /**
     * this to if click to back move you to Home not close app
     */
    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}