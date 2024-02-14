package com.example.attendence;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        private ImageView teacherImageView;
        private EditText teacherNameEditText, teacherSubjectEditText, teacherEmailEditText, teacherPhoneEditText, teacherAddressEditText;
        private Button saveButton, editButton;
        private TextView textView3, textView;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            // Initialize views
            teacherImageView = findViewById(R.id.teacherImageView);
            teacherNameEditText = findViewById(R.id.teacherNameEditText);
            teacherSubjectEditText = findViewById(R.id.teacherSubjectEditText);
            teacherEmailEditText = findViewById(R.id.teacherEmailEditText);
            teacherPhoneEditText = findViewById(R.id.teacherPhoneEditText);
            teacherAddressEditText = findViewById(R.id.teacherAddressEditText);

            editButton = findViewById(R.id.saveButton);
            textView3 = findViewById(R.id.textView3);
            textView = findViewById(R.id.textView);

            // Set onClickListener for saveButton
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Add your save functionality here
                }
            });

            // Set onClickListener for editButton
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Add your edit functionality here
                }
            });
        return inflater.inflate(R.layout.activity_profile_fragment, container, false);
    }
}
