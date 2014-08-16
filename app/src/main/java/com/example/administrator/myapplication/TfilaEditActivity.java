package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AutoCompleteTextView;


public class TfilaEditActivity extends Activity {

    private AutoCompleteTextView _autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfila_edit);

        _autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.locationautocomplete);

        _autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        Bundle b = getIntent().getExtras();

        String location = b.getString("location");
        _autoCompleteTextView.setText(location);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      return false;
    }
}
