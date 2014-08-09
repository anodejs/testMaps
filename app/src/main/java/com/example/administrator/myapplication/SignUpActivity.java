package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by amitmach on 8/9/2014.
 */
public class SignUpActivity extends Activity{

    private MinyanApp m_app;
    private EditText m_emailTextView;
    private EditText m_nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        m_emailTextView = (EditText) findViewById(R.id.emailedittextview);
        m_nameTextView = (EditText) findViewById(R.id.nameedittextview);

        Button signUpButton = (Button) findViewById(R.id.signupbutton);
        m_app = (MinyanApp) getApplication();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignupTask().execute();
            }
        });
    }

    private class SignupTask extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... params) {

            //call the server with the name and email

            //
            m_app.setUserAccount(m_emailTextView.getText().toString(), m_nameTextView.getText().toString());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent myIntent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }
    }
}
