package com.example.attendence;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Clock2 extends AppCompatActivity {

    private Button clockButton;
    private Button homeButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 5 * 60 * 1000; // 5 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock2);

        clockButton = findViewById(R.id.clockButton);
        homeButton = findViewById(R.id.homeButton);

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

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
