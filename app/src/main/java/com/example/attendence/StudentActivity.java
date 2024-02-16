package com.example.attendence;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class StudentActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private int minutesNearTeacher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Start a timer to check distance every minute
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean presence = isPresent();
                System.out.println("Is student present? " + presence);

                if (presence) {
                    minutesNearTeacher++;
                }
            }
        }, 0, 60 * 1000); // Check every 1 minute (60 seconds * 1000 milliseconds)

        // Stop the timer after 90 minutes
        Timer stopTimer = new Timer();
        stopTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                boolean isAttendanceSufficient = checkAttendance(minutesNearTeacher);
                System.out.println("Is attendance sufficient? " + isAttendanceSufficient);
            }
        }, 90 * 60 * 1000); // Stop after 90 minutes
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

    private double getDistance() {
        // Get teacher's location
        double[] teacherCoordinates = Clock2.getTeacherLocation();

        // Get student's location
        double[] studentCoordinates = getStudentLocation();

        // Calculate the Euclidean distance
        double diffLatitude = teacherCoordinates[0] - studentCoordinates[0];
        double diffLongitude = teacherCoordinates[1] - studentCoordinates[1];
        double squaredDiffLat = Math.pow(diffLatitude, 2);
        double squaredDiffLon = Math.pow(diffLongitude, 2);
        double sumOfSquaredDiffs = squaredDiffLat + squaredDiffLon;
        double distance = Math.sqrt(sumOfSquaredDiffs);

        return distance;
    }

    private boolean isPresent() {
        // Calculate the Euclidean distance
        double distance = getDistance();

        // Check if distance is less than the threshold
        double threshold = 0.000067;
        return distance < threshold;
    }

    private boolean checkAttendance(int minutesNearTeacher) {
        // Check if the percentage of time near teacher is greater than 75%
        return (minutesNearTeacher >= 0.75 * 90);
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
