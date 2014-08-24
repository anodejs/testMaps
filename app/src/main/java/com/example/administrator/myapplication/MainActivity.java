package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button _nearMeButton;
    private Button _scheudlerButton;
    private Button _sidurButton;
    private Button _compassButton;
    private GPSTracker gps;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // if extending Activity
        setContentView(R.layout.activity_main);


     //   Intent intent = new Intent(MainActivity.this, AndroidWSClient.class);
       //     startActivity(intent);


        _nearMeButton = (Button) findViewById(R.id.nearMeNowButton );
        _scheudlerButton = (Button) findViewById(R.id.schedulerButton );
        _sidurButton = (Button) findViewById(R.id.SidurButton );
        _compassButton = (Button) findViewById(R.id.CompassButton );


        _nearMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gps = new GPSTracker(MainActivity.this);
                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    //add put extra for location
                    //send to the server client location
                    Intent intent = new Intent(MainActivity.this, NearMeNow.class);
                    Bundle b = new Bundle();
                    b.putDouble("Longitude", gps.getLatitude());
                    b.putDouble("Latitude", gps.getLatitude());
                    //b.putString("address", item.getAddress());
                    intent.putExtras(b);
                    startActivity(intent);

                }
                else{
                    // can't get location
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();

                }


            }


        });
        _scheudlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scheduler.class);
                startActivity(intent);
            }
        });
        _sidurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SidurActivity.class);
                startActivity(intent);
            }
        });

        _compassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MagneticCompass.class);
                startActivity(intent);
            }
        });

    }
}