package com.example.attendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.attendence.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private ListView detailListView;
    private EditText searchEditText;
    private List<String> list;
    private DetailListAdapter adapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        // Initialize views
        detailListView = view.findViewById(R.id.detailListView);
        searchEditText = view.findViewById(R.id.searchEditText);

        // Initialize data list
        list = new ArrayList<>();
        // Add dummy data

            list.add("Item " );

        // Initialize adapter
        adapter = new DetailListAdapter(getContext(), list);

        // Set adapter to ListView
        detailListView.setAdapter(adapter);

        detailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Check if the clicked item is the dummy item
                if (position == 0) { // Assuming the dummy item is at position 0
                    // Navigate to DetailFragment
                    navigateToDetailFragment();
                }
            }
        });

        return view;
    }
    private void navigateToDetailFragment() {
        // Create an instance of the FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

// Begin a transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

// Replace the current fragment with the new fragment
        DonutChartFragment newFragment = new DonutChartFragment();
        fragmentTransaction.replace(R.id.donut_container,newFragment);

// (Optional) Add the transaction to the back stack
        fragmentTransaction.addToBackStack(null);
        list.clear();

// Commit the transaction
        fragmentTransaction.commit();

    }


}
