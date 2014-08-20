package com.example.administrator.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amitmach on 8/9/2014.
 */
public class PlacesAdapter extends BaseAdapter {

    private final Context context;

    private final int rowLayout;

    private ArrayList<Place> values;

    public PlacesAdapter(Context context, int rowlayout, Place[] values) {
        super();
        this.context = context;
        this.rowLayout = rowlayout;
        this.values = new ArrayList<Place>(Arrays.asList(values));

    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(rowLayout, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.placelisitemtitle);
        TextView distanceTextView = (TextView) rowView.findViewById(R.id.placelistitemdistance);
        TextView locationTextView = (TextView) rowView.findViewById(R.id.placelistitemlocation);
        TextView timeTextView = (TextView) rowView.findViewById(R.id.placelistitemtime);
        TextView numberOfPeopleTextView = (TextView) rowView.findViewById(R.id.placelistitemnumber);


        Place place = (Place) getItem(position);
        titleTextView.setText(place.getName());
        distanceTextView.setText(String.valueOf(place.getDistance()));
        locationTextView.setText(place.getAddress());
        timeTextView.setText(place.getTime().format("%k:%M"));
        String numberOfPeople = place.getNumberOfPeople() + "";
        numberOfPeopleTextView.setText(numberOfPeople);


        return rowView;

    }

    public void clear() {
        values.clear();
    }

    public void add(Place place) {
        values.add(place);
    }
}
