package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.security.AccessController.getContext;

/**
 * Created by amitmach on 8/9/2014.
 */
public class SignUpActivity extends Activity{

    private MinyanApp m_app;
    private EditText m_emailTextView;
    private EditText m_nameTextView;
    private TextView m_errorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        m_emailTextView = (EditText) findViewById(R.id.emailedittextview);
        m_nameTextView = (EditText) findViewById(R.id.nameedittextview);
        m_errorTextView = (TextView) findViewById(R.id.errortextview);
        Button signUpButton = (Button) findViewById(R.id.signupbutton);
        m_app = (MinyanApp) getApplication();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m_errorTextView.setText("");
                if(m_emailTextView.getText().toString().matches(""))
                {
                    m_errorTextView.setText("נא הכנס דואר אלקטרוני");
                        return;
                }
                if(m_nameTextView.getText().toString().matches(""))
                {
                    m_errorTextView.setText("נא הכנס שם משתמש");
                    return;
                }

                new SignupTask().execute();
            }
        });
    }

    private class SignupTask extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... params) {

            //call the server with the name and email

            //server do vailed check
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
