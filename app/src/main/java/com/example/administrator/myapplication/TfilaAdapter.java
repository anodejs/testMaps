package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.administrator.myapplication.Tfila.Type.*;


public class TfilaAdapter extends BaseAdapter{

    public final static int SUNDAY = 0;
    public final static int MONDAY = 1;
    public final static int TUESDAY = 2;
    public final static int WEDNESDAY = 3;
    public final static int THURSDAY = 4;
    public final static int FRIDAY = 5;
    public final static int SATURDAY = 6;

    public final static int SAHARIT = 0;
    public final static int MINHA = 1;
    public final static int ARAVIT = 2;


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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(rowLayout, parent, false);


        ImageView dayImageView = (ImageView) rowView.findViewById(R.id.day_icon);
       // ImageView tfilaTypeImageView= (ImageView) rowView.findViewById(R.id.type_icon);

        TextView tfilaTypeImageView= (TextView) rowView.findViewById(R.id.type_icon);

        TextView startTimeTextView = (TextView) rowView.findViewById(R.id.starttimetextview);
        TextView endTimeTextView = (TextView) rowView.findViewById(R.id.endtimetextview);

        Tfila tfila = (Tfila) getItem(position);

        switch (tfila.get_day())
        {
            case (0):
            {
                dayImageView.setImageResource(R.drawable.a);

                break;
            }
            case (1):
            {
                dayImageView.setImageResource(R.drawable.b);
                break;
            }
            case (2):
            {
                dayImageView.setImageResource(R.drawable.c);
                break;
            }
            case (3):
            {
                dayImageView.setImageResource(R.drawable.d);
                break;
            }
            case (4):
            {
                dayImageView.setImageResource(R.drawable.e);
                break;
            }
            case (5):
            {
                dayImageView.setImageResource(R.drawable.f);
                break;
            }

        }
        Tfila.Type type = tfila.get_type();

        switch (type)
        {

            case Saharit:
           //     tfilaTypeImageView.setImageResource(R.drawable.saharit);
                tfilaTypeImageView.setText(" שחרית");
                break;
            case Minha:
           //     tfilaTypeImageView.setImageResource(R.drawable.minha);
                tfilaTypeImageView.setText(" מנחה");
                break;
            case Aravit:
             //   tfilaTypeImageView.setImageResource(R.drawable.aravit);
                tfilaTypeImageView.setText(" ערבית");
                break;
        }

        startTimeTextView.setText(tfila.get_startTime().format("%k:%M"));
        endTimeTextView.setText(tfila.get_endTime().format("%k:%M"));

        if(tfila.get_active() == 0)
        {
            rowView.setBackgroundColor(Color.LTGRAY);
        }

        return rowView;

    }

    public void clear() {
        values.clear();
    }

    public void add(Tfila tfila) {
        values.add(tfila);
    }
}
