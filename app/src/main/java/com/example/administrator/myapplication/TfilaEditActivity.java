package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


public class TfilaEditActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private AutoCompleteTextView _autoCompleteTextView;
    private Button _sumbit;
    private Switch _switch;
    private Button startTimeButton, endTimeButton;
    private TextView startTimeTextView , endTimeTextView,adressTextViwe, distacneTextView;
    private Calendar cal;
    private int hour;
    private int min;
    private EditText editTextStartTime , editTextEndTime, editTextdistance;
    String location;
    private Boolean startTime = false;
    private Boolean active ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfila_edit);



        _autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.locationautocomplete);
        _sumbit = (Button) findViewById(R.id.sumbitbutton);
        _switch = (Switch)findViewById(R.id.switch1);
        startTimeTextView = (TextView)findViewById(R.id.startTimeTextView);
        endTimeTextView = (TextView)findViewById(R.id.endTimetextView);
        adressTextViwe = (TextView)findViewById(R.id.adressTextViwe);
        distacneTextView = (TextView)findViewById(R.id.distancetextView);
        editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        startTimeButton = (Button) findViewById(R.id.startTimeimageButton);
        editTextEndTime = (EditText) findViewById(R.id.endTimeeditText);
        endTimeButton = (Button) findViewById(R.id.endTimeimageButton);
        editTextdistance = (EditText) findViewById(R.id.distanceeditText);
        _autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);


        startTimeButton.setOnClickListener(this);
        endTimeButton.setOnClickListener(this);
        _sumbit.setOnClickListener(this);
        _switch.setOnCheckedChangeListener(this);

        Bundle b = getIntent().getExtras();
        location = b.getString("location");
        active = b.getBoolean("active");
       _autoCompleteTextView.setText(location);

        if (active)
        {
            _switch.setChecked(true);
        }
        else
        {
            _switch.setChecked(false);
            makeLayoutGone();
        }



        //press enter
        _autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {

                    editTextdistance.requestFocus();
                }
                return false;
            }
        });



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
        startTime = false;

        if (view.getId() == _sumbit.getId())
        {
            Location p = getLocationFromAddress(location);
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + p.getLatitude() + "\nLong: " + p.getLongitude(), Toast.LENGTH_LONG).show();

        }



        if (view.getId() == startTimeButton.getId())
        {
            startTime = true;
            showDialog(0);
        }

        if (view.getId() == endTimeButton.getId())
        {
            showDialog(1);
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
            if(startTime) {
                editTextStartTime.setText(hour + " : " + minute + " " + am_pm);

            }
            else {
                editTextEndTime.setText(hour + " : " + minute + " " + am_pm);
            }
        }
    };


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if(isChecked){

            makeLayoutVisible();
        }
        else
        {
            makeLayoutGone();
        }

    }

    private void makeLayoutVisible() {
        editTextStartTime.setVisibility(View.VISIBLE);
        startTimeButton.setVisibility(View.VISIBLE);
        editTextEndTime.setVisibility(View.VISIBLE);
        endTimeButton.setVisibility(View.VISIBLE);
        editTextdistance.setVisibility(View.VISIBLE);
        _autoCompleteTextView.setVisibility(View.VISIBLE);
        _sumbit.setVisibility(View.VISIBLE);
        startTimeTextView.setVisibility(View.VISIBLE);
        endTimeTextView.setVisibility(View.VISIBLE);
        adressTextViwe.setVisibility(View.VISIBLE);
        distacneTextView.setVisibility(View.VISIBLE);

    }

    private void makeLayoutGone() {

        editTextStartTime.setVisibility(View.GONE);
        startTimeButton.setVisibility(View.GONE);
        editTextEndTime.setVisibility(View.GONE);
        endTimeButton.setVisibility(View.GONE);
        editTextdistance.setVisibility(View.GONE);
        _autoCompleteTextView.setVisibility(View.GONE);
        _sumbit.setVisibility(View.GONE);
        startTimeTextView.setVisibility(View.GONE);
        endTimeTextView.setVisibility(View.GONE);
        adressTextViwe.setVisibility(View.GONE);
        distacneTextView.setVisibility(View.GONE);

    }


}
