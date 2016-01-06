package com.yanbober.scroller_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<Person> mList = new ArrayList<>();

    public ListAdapter() {
        for (int index=0; index<30; index++) {
            Person person = new Person();
            person.mId = index;
            person.mName = "Test Demo "+index;
            mList.add(person);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).mId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_checkbox_layout, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText(mList.get(position).mName);

        return convertView;
    }


}
