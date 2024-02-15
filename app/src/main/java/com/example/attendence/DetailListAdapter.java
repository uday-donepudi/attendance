package com.example.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mDataList;

    public DetailListAdapter(Context context, List<String> dataList) {
        super(context, 0, dataList);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Get the data item for this position
        String item = mDataList.get(position);

        // Find and set the TextView with the item name
        TextView textViewItem = convertView.findViewById(android.R.id.text1);
        textViewItem.setText(item);

        return convertView;
    }
}
