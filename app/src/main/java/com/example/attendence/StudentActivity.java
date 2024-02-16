package com.example.attendence;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import java.text.DecimalFormat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class StudentActivity extends AppCompatActivity {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private double[] getStudentLocation() {
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



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Location permission granted, get student location
            double[] studentLocation = getStudentLocation();
            if (studentLocation != null) {
                // Do something with the student location
            }
        }
    }
}
