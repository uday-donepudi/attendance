package com.example.attendence;

import android.annotation.SuppressLint;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;
    private ArrayAdapter<String> messageAdapter;
    private ArrayList<String> messageList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notifications_fragment, container, false);

        // Initialize UI components
        messageListView = view.findViewById(R.id.messageListView);
        messageEditText = view.findViewById(R.id.messageEditText);
        sendButton = view.findViewById(R.id.sendButton);

        // Initialize message list and adapter
        messageList = new ArrayList<>();
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



    private void sendMessage() {
        // Get message from EditText
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            // Add message to list and notify adapter
            messageList.add(message);
            messageAdapter.notifyDataSetChanged();
            // Clear EditText after sending message
            messageEditText.getText().clear();

            // Display the message as a notification
            displayNotification(message);
        }
    }

    @SuppressLint("MissingPermission")
    private void displayNotification(String message) {
        // Create a NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "default")
                .setSmallIcon(R.drawable.ic_notification)

                         // Set your app's notification icon here
                .setContentTitle("New Message")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create a NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        // Show the notification
        notificationManager.notify(1, builder.build());
    }

}
