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
 * Created by amitmach on 8/16/2014.
 */
public class TfilaAdapter extends BaseAdapter{

    private final Context context;

    private final int rowLayout;

    private ArrayList<Tfila> values;

    public TfilaAdapter(Context context, int rowlayout, Tfila[] values) {
        super();
        this.context = context;
        this.rowLayout = rowlayout;
        this.values = new ArrayList<Tfila>(Arrays.asList(values));

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


        TextView dayTextView = (TextView) rowView.findViewById(R.id.daytextview);
        TextView tfilaTypeTextView= (TextView) rowView.findViewById(R.id.tfilatypetextview);
        TextView startTimeTextView = (TextView) rowView.findViewById(R.id.starttimetextview);
        TextView endTimeTextView = (TextView) rowView.findViewById(R.id.endtimetextview);



        Tfila tfila = (Tfila) getItem(position);

        dayTextView.setText(tfila.get_day() + "");
        tfilaTypeTextView.setText(tfila.get_type().toString());
        startTimeTextView.setText(tfila.get_startTime().toString());
        endTimeTextView.setText(tfila.get_endTime().toString());

        return rowView;

    }

    public void clear() {
        values.clear();
    }

    public void add(Tfila tfila) {
        values.add(tfila);
    }
}
