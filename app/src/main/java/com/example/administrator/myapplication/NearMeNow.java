package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class NearMeNow extends ListActivity {

    private PlacesAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_me_now);

        Place[] list = new Place[]{};

        m_adapter = new PlacesAdapter(this,R.layout.placelist,list);

        setListAdapter(m_adapter);

        new GetPlaces().execute();

    }

    private class GetPlaces extends AsyncTask<Void, Void, Void> {
        ArrayList<Place> m_places;

        protected Void doInBackground(Void... params) {

            //get it from the server
            m_places = new ArrayList<Place>();

            Date current = new Date();
            Place a = new Place(current, "a", "address", 32, 23, 5);
            Place b = new Place(current, "b", "baddress", 32, 23, 6);
            m_places.add(a);
            m_places.add(b);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            NearMeNow.this.m_adapter.clear();
            for (Place place : m_places) {
                NearMeNow.this.m_adapter.add(place);
            }
            NearMeNow.this.m_adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(NearMeNow.this, PlaceActivity.class);
        Bundle b = new Bundle();
        Place item = (Place) m_adapter.getItem(position);
        b.putString("name", item.getName());
        b.putString("address", item.getAddress());
        intent.putExtras(b);
        startActivity(intent);
    }
}
