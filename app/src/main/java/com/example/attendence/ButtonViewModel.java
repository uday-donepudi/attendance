package com.example.attendence;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ButtonViewModel extends ViewModel {
    private List<String> buttonData = new ArrayList<>();

    public void addButton(String buttonText) {
        buttonData.add(buttonText);
    }

    public List<String> getButtonData() {
        return buttonData;
    }
}
