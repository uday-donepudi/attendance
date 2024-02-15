package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment_St extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_home_fragment_st, container, false);

        // Find the button in the layout
        Button openNewPageButton = rootView.findViewById(R.id.openNewPageButton);

        // Set OnClickListener to the button
        openNewPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity here
                Intent intent = new Intent(getActivity(), StudentActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
