package com.example.retrofit.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.retrofit.R;
import com.example.retrofit.databinding.ActivityMainBinding;
import com.example.retrofit.ui.fragment.character.CharacterFragment;
import com.example.retrofit.ui.fragment.episode.EpisodeFragment;
import com.example.retrofit.ui.fragment.location.LocationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import org.jetbrains.annotations.NotNull;

import static com.example.retrofit.R.id.nav_host_fragment;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpNavigation();
        setUpBottomNav();


    }

    private void setUpBottomNav() {
        binding.bottomNav.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) item -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.character:
                    transaction.replace(nav_host_fragment, new CharacterFragment()).commit();
                    return true;
                case R.id.episode:
                    transaction.replace(nav_host_fragment, new EpisodeFragment()).commit();
                    return true;
                case R.id.location:
                    transaction.replace(nav_host_fragment, new LocationFragment()).commit();
                    return true;

            }
            return false;

        });
    }


    private void setUpNavigation() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(nav_host_fragment);
        navController = navHostFragment.getNavController();
    }


}