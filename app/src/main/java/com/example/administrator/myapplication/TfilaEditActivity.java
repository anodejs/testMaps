package com.example.administrator.myapplication;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.List;


public class TfilaEditActivity extends Activity implements View.OnClickListener {

    private AutoCompleteTextView _autoCompleteTextView;
    private Button _sumbit;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfila_edit);

        _autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.locationautocomplete);
        _sumbit = (Button) findViewById(R.id.btnSubmit);
        _autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        Bundle b = getIntent().getExtras();

         location = b.getString("location");
        _autoCompleteTextView.setText(location);
        _sumbit.setOnClickListener(this);
    }


    public GeoPoint getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new GeoPoint((int) (location.getLatitude() * 1E6),
                    (int) (location.getLongitude() * 1E6));

            return p1;
        }
        catch (Exception ex) {
        }

    }
    @Override
    public void onClick(View view) {

        if (view.getId() == _sumbit.getId())
        {
            GeoPoint p = getLocationFromAddress(location);
        }
    }
}
