package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;


public class TfilaEditActivity extends Activity implements View.OnClickListener {

    private AutoCompleteTextView _autoCompleteTextView;
    private Button _sumbit;
    private ImageButton ib;
    private Calendar cal;
    private int hour;
    private int min;
    private EditText et;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfila_edit);

        _autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.locationautocomplete);
        _sumbit = (Button) findViewById(R.id.btnSubmit);
        _autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        ib = (ImageButton) findViewById(R.id.startTimeimageButton);
        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        //todo
        et = (EditText) findViewById(R.id.editTextEndTime);
        ib.setOnClickListener(this);



        Bundle b = getIntent().getExtras();

         location = b.getString("location");
        _autoCompleteTextView.setText(location);
        _sumbit.setOnClickListener(this);
    }


    public Location getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        Location p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new Location("");//provider name is unecessary
            p1.setLatitude(location.getLatitude());//your coords of course
            p1.setLongitude(location.getLongitude());

            return p1;
        }
        catch (Exception ex) {
        }

        return null;

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == _sumbit.getId())
        {
        //    GeoPoint p = getLocationFromAddress(location);
        }

        if (view.getId() == ib.getId())
        {
            showDialog(0);
        }
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new TimePickerDialog(this, timePickerListener, hour, min, false);
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }
            et.setText(hour + " : " + minute + " " + am_pm);
        }
    };

}
