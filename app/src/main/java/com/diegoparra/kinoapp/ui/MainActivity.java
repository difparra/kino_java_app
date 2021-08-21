package com.diegoparra.kinoapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.TextView;

import com.diegoparra.kinoapp.R;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Function;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.viewmodel.MoviesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavView = findViewById(R.id.nav_view_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_main);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavView, navController);

    }


}