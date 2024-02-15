package com.example.attendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    // Existing code...
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard_fragment, container, false);

        listView = view.findViewById(R.id.listView);

        // Adapter setup
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // Handle click events on ListView items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Check if the clicked item is the dummy item
                if (position == 0) { // Assuming the dummy item is at position 0
                    // Navigate to DetailFragment
                    navigateToDetailFragment();
                }
            }
        });

        // Add a dummy item to the list
        addItemToList("Dummy Item");

        return view;
    }

    // Method to add an item to the data list and notify adapter
    private void addItemToList(String item) {
        dataList.add(item);
        adapter.notifyDataSetChanged();
    }

    // Method to navigate to DetailFragment
    // Method to navigate to DetailFragment
    private void navigateToDetailFragment() {
        // Clear the data list
        dataList.clear();
        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DetailFragment detailFragment = new DetailFragment();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addToBackStack(null); // Optional: Add the transaction to the back stack
        fragmentTransaction.commit();
    }
    
}
