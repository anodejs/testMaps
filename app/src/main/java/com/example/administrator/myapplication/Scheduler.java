package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Scheduler extends ListActivity {

    private TfilaAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        Tfila[] list = new Tfila[]{};
        m_adapter = new TfilaAdapter(this,R.layout.tfilalistitem,list);
        setListAdapter(m_adapter);
        new GetTfilot().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, TfilaEditActivity.class);
        Bundle b = new Bundle();
        Tfila item = (Tfila) m_adapter.getItem(position);
        b.putString("location", item.get_location());
        intent.putExtras(b);

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       return false;
    }


    private class GetTfilot extends AsyncTask<Void, Void, Void> {
        ArrayList<Tfila> m_tfilot;

        protected Void doInBackground(Void... params) {

            //get it from the server
            m_tfilot = new ArrayList<Tfila>();

            Time now = new Time(Time.getCurrentTimezone());
            now.setToNow();

            Tfila sample = new Tfila(Tfila.Type.Saharit, now, now, "ראשון", 10, 0);
            Tfila sample1 = new Tfila(Tfila.Type.Aravit, now, now, "שני", 20,1);
            m_tfilot.add(sample);

            m_tfilot.add(sample1);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Scheduler.this.m_adapter.clear();
            for (Tfila tfila: m_tfilot) {
                Scheduler.this.m_adapter.add(tfila);
            }
            Scheduler.this.m_adapter.notifyDataSetChanged();
        }
    }
}
