package com.example.attendence;
import android.widget.Toast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;


public class LoginFragment extends Fragment {

    EditText emailEditText, passwordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailEditText = view.findViewById(R.id.loginEmailEditText);
        passwordEditText = view.findViewById(R.id.loginPasswordEditText);
        Button loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the email and password entered by the user
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // Perform login action
                loginUser(email, password);
            }
        });

        return view;
    }

    private void loginUser(String email, String password) {
        // Implement your login logic here
        // For demonstration purposes, you can display a toast message
        String message = "Email: " + email + ", Password: " + password;
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
