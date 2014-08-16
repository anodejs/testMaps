package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button _scheudlerButton;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // if extending Activity
        setContentView(R.layout.activity_main);

        _scheudlerButton = (Button) findViewById(R.id.schedulerButton );
        _scheudlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scheduler.class);
                startActivity(intent);
            }
        });

    }
}