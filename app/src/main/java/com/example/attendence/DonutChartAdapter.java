package com.example.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DonutChartAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public DonutChartAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data != null ? data : new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_donut_chart, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.donutArea1);
            holder.textView = convertView.findViewById(R.id.donutArea2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = data.get(position);
        holder.textView.setText(item);

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}
