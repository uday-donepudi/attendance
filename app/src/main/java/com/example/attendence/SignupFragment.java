package com.example.attendence;
import android.annotation.SuppressLint;
import android.widget.Toast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;


public class SignupFragment extends Fragment {

    EditText emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        emailEditText = view.findViewById(R.id.signupEmailEditText);
        passwordEditText = view.findViewById(R.id.signupPasswordEditText);
        Button signupButton = view.findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the email and password entered by the user
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // Perform signup action
                signupUser(email, password);
            }
        });

        return view;
    }

    private void signupUser(String email, String password) {
        // Implement your signup logic here
        // For demonstration purposes, you can display a toast message
        String message = "Email: " + email + ", Password: " + password;
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
