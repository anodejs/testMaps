package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;


public class SplashActivity extends Activity {

    private MinyanApp m_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        m_app =  (MinyanApp) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(m_app.hasUserAccount()) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }
        else {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }


}
