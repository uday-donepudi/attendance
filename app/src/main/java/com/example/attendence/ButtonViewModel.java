package com.example.attendence;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ButtonViewModel extends ViewModel {
    private List<String> buttonData = new ArrayList<>();

    // Method to add button data
    public void addButton(String buttonText) {
        buttonData.add(buttonText);
    }

    // Method to get button data
    public List<String> getButtonData() {
        return buttonData;
    }
}
