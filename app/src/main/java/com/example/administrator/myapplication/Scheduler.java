package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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

        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_scheduler, lv, false);
        lv.addHeaderView(header, null, false);

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

            Tfila sample = new Tfila(Tfila.Type.Saharit, now, now, "ראשון", 10, 0,1);
            Tfila sample1 = new Tfila(Tfila.Type.Aravit, now, now, "שני", 20,0,0);
            Tfila sample2 = new Tfila(Tfila.Type.Minha, now, now, "שלישי", 20,0,0);
            Tfila sample3 = new Tfila(Tfila.Type.Saharit, now, now, "ראשון", 10, 1,1);
            Tfila sample4 = new Tfila(Tfila.Type.Aravit, now, now, "שני", 20,1,0);
            Tfila sample5 = new Tfila(Tfila.Type.Minha, now, now, "שלישי", 20,1,0);
            Tfila sample6 = new Tfila(Tfila.Type.Saharit, now, now, "ראשון", 10, 2,1);
            Tfila sample7 = new Tfila(Tfila.Type.Aravit, now, now, "שני", 20,2,0);
            Tfila sample8 = new Tfila(Tfila.Type.Minha, now, now, "שלישי", 20,2,0);
            Tfila sample9 = new Tfila(Tfila.Type.Saharit, now, now, "ראשון", 10, 3,1);
            Tfila sample10 = new Tfila(Tfila.Type.Aravit, now, now, "שני", 20,3,0);
            Tfila sample11 = new Tfila(Tfila.Type.Minha, now, now, "שלישי", 20,3,0);

            m_tfilot.add(sample);
            m_tfilot.add(sample1);
            m_tfilot.add(sample2);
            m_tfilot.add(sample3);
            m_tfilot.add(sample4);
            m_tfilot.add(sample5);
            m_tfilot.add(sample6);
            m_tfilot.add(sample7);
            m_tfilot.add(sample8);
            m_tfilot.add(sample9);
            m_tfilot.add(sample10);
            m_tfilot.add(sample11);

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
