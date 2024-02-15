package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Clock1 extends AppCompatActivity {

    private Button clockButton;
    private Button homeButton; // Added reference for the home button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock1);

        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton); // Initialize the home button

        // Set click listener for the clock button
        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Clock2 activity
                startActivity(new Intent(Clock1.this, Clock2.class));
            }
        });

        // Set click listener for the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page (MainActivity)
                startActivity(new Intent(Clock1.this, HomeFragment.class));
            }
        });
    }

    // Override onBackPressed to navigate to the home page (MainActivity)
    @Override
    public void onBackPressed() {
        // Navigate to the home page (MainActivity)
        startActivity(new Intent(this, HomeFragment.class));
    }
}