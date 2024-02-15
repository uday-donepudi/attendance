package com.example.attendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.attendence.R;
import com.github.mikephil.charting.charts.PieChart;

public class DonutChartFragment extends Fragment {

    private PieChart donutChart;
    private TextView donutArea1;
    private TextView donutArea2;

    public DonutChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donut_chart, container, false);

        // Initialize views
        donutChart = view.findViewById(R.id.donutChart);
        donutArea1 = view.findViewById(R.id.donutArea1);
        donutArea2 = view.findViewById(R.id.donutArea2);

        return view;
    }
}
