package com.example.attendence;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Clock1 extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    private LocationManager locationManager;
    private Button clockButton;
    private Button homeButton;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("location");

        // Set onClickListener for the button to add data

        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Clock1.this,Clock2.class);
                startActivity(i);
                double[] coordinates = getTeacherLocation();

                // Create a map to hold the data
                Map<String, Object> data = new HashMap<>();
                data.put("latitude", coordinates[0]);
                data.put("longitude", coordinates[1]);

                // Add data to Realtime Database

                databaseReference.push().setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Data added successfully
                                Toast.makeText(Clock1.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error occurred while adding data
                                Toast.makeText(Clock1.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                startLocationUpdates();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        double[] coordinates = getTeacherLocation();
        // You can now use these coordinates to update the Firestore database or perform any other actions.
    }

    double[] getTeacherLocation() {
        double[] coordinates = new double[2]; // Array to store latitude and longitude

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Get last known location from GPS provider
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation == null) {
                // If GPS provider is unavailable, try network provider
                lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (lastKnownLocation != null) {
                // Extract latitude and longitude from the location object
                coordinates[0] = lastKnownLocation.getLatitude();
                coordinates[1] = lastKnownLocation.getLongitude();
            } else {
                // Handle the case when last known location is not available
                coordinates[0] = 0.0; // Default latitude
                coordinates[1] = 0.0; // Default longitude
            }
        }

        // Format latitude and longitude values to have up to 5 digits after the decimal point
        DecimalFormat df = new DecimalFormat("#.#####");
        coordinates[0] = Double.parseDouble(df.format(coordinates[0]));
        coordinates[1] = Double.parseDouble(df.format(coordinates[1]));

        return coordinates;
    }

    private void navigateToHome() {
        Intent intent = new Intent(Clock1.this, HomeFragment.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
