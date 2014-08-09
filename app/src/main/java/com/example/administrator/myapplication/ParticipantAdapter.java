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
public class ParticipantAdapter extends BaseAdapter {
    private final Context context;

    private final int rowLayout;

    private ArrayList<Participant> values;

    public ParticipantAdapter(Context context, int rowlayout, Participant[] values) {
        super();
        this.context = context;
        this.rowLayout = rowlayout;
        this.values = new ArrayList<Participant>(Arrays.asList(values));

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


        TextView participantTextView = (TextView) rowView.findViewById(R.id.participantnametextview);


        Participant participant = (Participant) getItem(position);

        participantTextView.setText(participant.getName());

        return rowView;

    }

    public void clear() {
        values.clear();
    }

    public void add(Participant place) {
        values.add(place);
    }
}
