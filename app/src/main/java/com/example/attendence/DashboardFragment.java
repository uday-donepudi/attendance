package com.example.attendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new DashboardAdapter(dataList);
        recyclerView.setAdapter(adapter);
// Add an example item to the list
        addItemToList("Example Item");

        return view;
    }

    // Method to add an item to the data list and notify adapter
    private void addItemToList(String item) {
        dataList.add(item);
        adapter.notifyItemInserted(dataList.size() - 1);
    }

}
