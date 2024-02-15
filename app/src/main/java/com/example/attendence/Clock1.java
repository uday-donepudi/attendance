package com.example.attendence;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class Clock1 extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    private static final int DESIRED_ACCURACY_METERS = 10;

    private Button clockButton;
    private Button homeButton; // Added reference for the home button
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock1);

        // Initialize buttons
        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton);

        // Initialize fused location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Set click listener for the clock button
        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Clock2 activity
                Intent intent = new Intent(Clock1.this, Clock2.class);
                startActivity(intent);

                // Start location updates
                startLocationUpdates();
            }
        });

        // Set click listener for the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });
    }

    // Method to start location updates
    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Update interval in milliseconds

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location.getAccuracy() <= DESIRED_ACCURACY_METERS) {
                        // GPS accuracy is within desired range, proceed with sending notification
                        sendNotificationToStudentActivity();
                        stopLocationUpdates(); // Stop location updates once desired accuracy is achieved
                        return;
                    }
                }
            }
        };

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    // Method to stop location updates
    private void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    // Method to navigate to the home activity
    private void navigateToHome() {
        Intent intent = new Intent(Clock1.this, HomeFragment.class);
        startActivity(intent);
    }

    // Method to send notification to student activity
    private void sendNotificationToStudentActivity() {
        // Placeholder method for sending notification to student activity
        Toast.makeText(this, "Sending notification to student activity", Toast.LENGTH_SHORT).show();
        // You should implement the logic to send notification to student activity here
    }

    // Override onRequestPermissionsResult to handle permission requests
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, start location updates
                startLocationUpdates();
            } else {
                // Location permission denied, show a message or handle accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Override onResume to start location updates when the activity resumes
    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    // Override onPause to stop location updates when the activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }
}
