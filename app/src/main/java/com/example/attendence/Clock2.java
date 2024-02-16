package com.example.attendence;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.DecimalFormat;

public class Clock2 extends AppCompatActivity {

    private Button clockButton;
    private Button homeButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 5 * 60 * 1000; // 5 minutes
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock2);

        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        updateTimerText();

        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                    clockButton.setText("Start");
                } else {
                    startTimer();
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void startTimer() {
        clockButton.setText("% Completed");
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(Clock2.this, "Timer Finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void updateTimerText() {
        long totalTimeInMillis = 5 * 60 * 1000; // 5 minutes in milliseconds
        long elapsedTimeInMillis = totalTimeInMillis - timeLeftInMillis;
        int percentageCompleted = (int) ((elapsedTimeInMillis / (double) totalTimeInMillis) * 100);
        clockButton.setText(percentageCompleted + "% Completed");
    }

    private double[] getTeacherLocation() {
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
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
