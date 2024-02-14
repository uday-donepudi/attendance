package com.example.attendence;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final HomeFragment homeFragment = new HomeFragment();
    private final DashboardFragment dashboardFragment = new DashboardFragment();
    private final NotificationsFragment notificationsFragment = new NotificationsFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final MenuFragment menuFragment = new MenuFragment();

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = homeFragment;
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = dashboardFragment;
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = notificationsFragment;
                    break;
                case R.id.navigation_profile:
                    selectedFragment = profileFragment;
                    break;
                case R.id.navigation_menu:
                default:
                    selectedFragment = menuFragment;
                    break;
            }
            loadFragment(selectedFragment);
            return true;
        });

        // Set the home fragment as default
        loadFragment(homeFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}

