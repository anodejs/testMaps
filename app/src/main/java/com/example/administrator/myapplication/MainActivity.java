package com.example.administrator.myapplication;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    GPSTracker gps;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // if extending Activity
        //setContentView(R.layout.activity_main);

        // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());

        // if extending Activity 2. Get ListView from activity_main.xml
        //ListView listView = (ListView) findViewById(R.id.listview);

        // 3. setListAdapter
        //listView.setAdapter(adapter); if extending Activity
        setListAdapter(adapter);
    }

    private ArrayList<Model> generateData(){
        ArrayList<Model> models = new ArrayList<Model>();
        models.add(new Model("Group Title"));
        models.add(new Model(R.drawable.icon,"מצא עכשיו"));
        models.add(new Model(R.drawable.icon,"לוח תפילות"));
        models.add(new Model(R.drawable.icon,"סידור"));

        return models;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //Place item = (Place) m_adapter.getItem(position);
        //choose nearMeNow
        if (position == 1) {
            gps = new GPSTracker(this);

            if (gps.canGetLocation()) {

                Toast.makeText(getApplicationContext(), gps.getLatitude() + "", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, NearMeNow.class);

                //ADD MY  LOCATION
                Bundle b = new Bundle();
                b.putDouble("Longitude", gps.getLatitude());
                b.putDouble("Latitude", gps.getLatitude());
                //b.putString("address", item.getAddress());
                intent.putExtras(b);
                startActivity(intent);
            } else {
                gps.showSettingsAlert();
            }
        }

        //choose sidor
            else if (position == 3)
            {
                Intent intent = new Intent(MainActivity.this, SidorActivity.class);
                startActivity(intent);

            }

        }


}