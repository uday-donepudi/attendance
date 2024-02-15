package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private LinearLayout buttonContainer;
    private ButtonViewModel buttonViewModel;
    private Spinner yearSpinner, branchSpinner, sectionSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        buttonContainer = rootView.findViewById(R.id.buttonContainer);
        yearSpinner = rootView.findViewById(R.id.yearSpinner);
        branchSpinner = rootView.findViewById(R.id.branchSpinner);
        sectionSpinner = rootView.findViewById(R.id.sectionSpinner);

        // Initialize ViewModel
        buttonViewModel = new ViewModelProvider(requireActivity()).get(ButtonViewModel.class);

        // Recreate buttons from ViewModel data
        if (buttonContainer.getChildCount() == 0) { // Check if buttons are already added
            for (String buttonText : buttonViewModel.getButtonData()) {
                addButton(buttonText);
            }
        }

        // Set up adapters for spinners
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.year_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.branch_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> sectionAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.section_array, android.R.layout.simple_spinner_item);

        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearSpinner.setAdapter(yearAdapter);
        branchSpinner.setAdapter(branchAdapter);
        sectionSpinner.setAdapter(sectionAdapter);

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
        // Get the selected values from the spinners
        String selectedYear = yearSpinner.getSelectedItem().toString();
        String selectedBranch = branchSpinner.getSelectedItem().toString();
        String selectedSection = sectionSpinner.getSelectedItem().toString();

        // Generate the button text using the selected values
        String buttonText = selectedYear + " - " + selectedBranch + " - " + selectedSection;

        // Simulate adding a new button with the generated name
        buttonViewModel.addButton(buttonText);
        addButton(buttonText);
    }

    private void addButton(String buttonText) {
        Button newButton = new Button(requireContext());
        newButton.setText(buttonText);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another page when the button is clicked
                Intent intent = new Intent(requireContext(), Clock1.class);
                startActivity(intent);
            }
        });
        newButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Remove the long-pressed button
                buttonContainer.removeView(v);
                buttonViewModel.getButtonData().remove(buttonText);
                return true;
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        newButton.setLayoutParams(params);
        buttonContainer.addView(newButton);
    }
}
