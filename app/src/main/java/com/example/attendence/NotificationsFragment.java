package com.example.attendence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotificationsFragment extends Fragment {

    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;
    private ArrayAdapter<String> messageAdapter;
    private ArrayList<String> messageList;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notifications_fragment, container, false);

        // Initialize UI components
        messageListView = view.findViewById(R.id.messageListView);
        sendButton = view.findViewById(R.id.sendButton);

        // Initialize SharedPreferences
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Load stored messages
        loadMessages();

        // Initialize message list and adapter
        messageAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, messageList);
        messageListView.setAdapter(messageAdapter);

        // Set click listener for send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    private void loadMessages() {
        // Get the stored messages
        Set<String> messagesSet = sharedPreferences.getStringSet("messages", new HashSet<String>());
        messageList = new ArrayList<>(messagesSet);
    }

    private void saveMessage(String message) {
        // Get the current list of messages
        Set<String> messagesSet = sharedPreferences.getStringSet("messages", new HashSet<String>());

        // Add the new message to the set
        messagesSet.add(message);

        // Save the updated set back to SharedPreferences
        sharedPreferences.edit().putStringSet("messages", messagesSet).apply();
    }

    private void sendMessage() {
        // Get message from EditText
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            // Add message to list and notify adapter
            messageList.add(message);
            messageAdapter.notifyDataSetChanged();
            // Clear EditText after sending message
            messageEditText.getText().clear();

            // Save the message in SharedPreferences
            saveMessage(message);
        }
    }
}
