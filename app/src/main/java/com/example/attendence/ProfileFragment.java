package com.example.attendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.attendence.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);

        // Initialize views
        ImageView teacherImageView = view.findViewById(R.id.teacherImageView);
        EditText teacherNameEditText = view.findViewById(R.id.teacherNameEditText);
        EditText teacherSubjectEditText = view.findViewById(R.id.teacherSubjectEditText);
        EditText teacherEmailEditText = view.findViewById(R.id.teacherEmailEditText);
        EditText teacherPhoneEditText = view.findViewById(R.id.teacherPhoneEditText);
        EditText teacherAddressEditText = view.findViewById(R.id.teacherAddressEditText);

        Button saveButton = view.findViewById(R.id.saveButton);
        Button editButton = view.findViewById(R.id.Editbutton);
        // You may need to change 'textView3', 'textView' IDs to the appropriate ones if they exist

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

        return view;
    }
}
