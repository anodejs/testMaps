package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

        TextView distanceTextView = (TextView) rowView.findViewById(R.id.placelistitemdistance);
        TextView locationTextView = (TextView) rowView.findViewById(R.id.placelistitemlocation);
        TextView timeTextView = (TextView) rowView.findViewById(R.id.placelistitemtime);
        TextView numberOfPeopleTextView = (TextView) rowView.findViewById(R.id.placelistitemnumber);


        Place place = (Place) getItem(position);
        distanceTextView.setText(String.valueOf(place.getDistance()));
        locationTextView.setText(place.getAddress());
        timeTextView.setText(place.getTime().format("%k:%M"));
        String numberOfPeople = place.getNumberOfPeople() + "";
        numberOfPeopleTextView.setText(numberOfPeople);

        Button navigateButton = (Button)rowView.findViewById(R.id.navigatebutton);

        navigateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=32.159481,34.842360&daddr=32.149417,34.844420"));
               // startActivity(intent);


                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                v.getContext().startActivity(intent);
            }

        });

        return rowView;

    }

    public void clear() {
        values.clear();
    }

    public void add(Place place) {
        values.add(place);
    }
}
