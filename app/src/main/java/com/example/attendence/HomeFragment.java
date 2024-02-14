package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private LinearLayout buttonContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        buttonContainer = rootView.findViewById(R.id.buttonContainer);

        Button addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClicked();
            }
        });

        return rootView;
    }

    private void addButtonClicked() {
        Button newButton = new Button(requireContext());
        newButton.setText("New Button");
        newButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Remove the long-pressed button
                buttonContainer.removeView(v);
                return true;
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        newButton.setLayoutParams(params);
        buttonContainer.addView(newButton);

        // Move the anonymous button down
        View anonymousButton = buttonContainer.findViewById(R.id.anonymousButton);
        if (anonymousButton != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) anonymousButton.getLayoutParams();
            layoutParams.topMargin += newButton.getHeight() + 10; // Adjust top margin by new button's height and margin
            anonymousButton.setLayoutParams(layoutParams);
        }
    }
}
