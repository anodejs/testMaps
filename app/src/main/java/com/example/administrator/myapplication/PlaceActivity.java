package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by amitmach on 8/9/2014.
 */
public class PlaceActivity extends ListActivity
{
    private ParticipantAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Bundle b = getIntent().getExtras();

        String name = b.getString("name");
        String address = b.getString("address");

        Participant[] list = new Participant[]{};

        m_adapter = new ParticipantAdapter(this,R.layout.participantlist,list);

        setListAdapter(m_adapter);

        TextView title = (TextView) findViewById(R.id.placetitle );
        title.setText(name);
        TextView addressTextView = (TextView) findViewById(R.id.addresstextview);
        addressTextView.setText(address);

        Button navigateButton = (Button) findViewById(R.id.navigatebutton );
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=32.159481,34.842360&daddr=32.149417,34.844420"));
                startActivity(intent);
            }
        });


        new GetPlaceInfo().execute();

    }

    private class GetPlaceInfo extends AsyncTask<Void, Void, Void> {
        ArrayList<com.example.administrator.myapplication.Participant> m_places;

        protected Void doInBackground(Void... params) {

            //get it from the server
            m_places = new ArrayList<Participant>();

            Date current = new Date();
//            //public Place(Date time, String name, String address, double latitude , double longitude, int numberOfPeople)
            Participant a = new Participant("tovi");
            Participant b = new Participant("amit");

            m_places.add(a);
            m_places.add(b);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            PlaceActivity.this.m_adapter.clear();
            for (Participant place : m_places) {
                PlaceActivity.this.m_adapter.add(place);
            }
            PlaceActivity.this.m_adapter.notifyDataSetChanged();
        }
    }

}
