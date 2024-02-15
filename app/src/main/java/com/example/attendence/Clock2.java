package com.example.attendence;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Clock2 extends AppCompatActivity {

    private Button clockButton;
    private Button homeButton; // Button to navigate to the home page
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 5 * 60 * 1000; // 5 minutes

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock2);

        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton); // Initialize the homeButton

        updateTimerText();

        // Start the timer automatically when Clock2 activity is created
        startTimer();

        // Set OnClickListener for the homeButton
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page
                Intent intent = new Intent(Clock2.this, HomeFragment.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Navigate to the home page when back button is pressed
        super.onBackPressed();
        Intent intent = new Intent(Clock2.this, HomeFragment.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to it
    }


    public void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

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

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
