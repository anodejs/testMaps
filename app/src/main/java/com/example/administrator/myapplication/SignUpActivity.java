package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by amitmach on 8/9/2014.
 */
public class SignUpActivity extends Activity{

    private MinyanApp m_app;
    private EditText m_emailTextView;
    private EditText m_nameTextView;
    private TextView m_errorTextView;
    private  Button m_signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        m_emailTextView = (EditText) findViewById(R.id.emailedittextview);
        m_nameTextView = (EditText) findViewById(R.id.nameedittextview);
        m_errorTextView = (TextView) findViewById(R.id.errortextview);
        m_signUpButton = (Button) findViewById(R.id.signupbutton);
        m_app = (MinyanApp) getApplication();



        m_signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m_errorTextView.setText("");
                if (m_emailTextView.getText().toString().matches("")) {
                    m_errorTextView.setText("נא הכנס דואר אלקטרוני");
                    return;
                }
                if (m_nameTextView.getText().toString().matches("")) {
                    m_errorTextView.setText("נא הכנס שם משתמש");
                    return;
                }

                new SignupTask().execute();
            }
        });
        //press enter
        m_emailTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {

                    m_nameTextView.requestFocus();
                }
                return false;
            }
        });

            //press enter after username > call sumbit
            m_nameTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                 if (keyCode == KeyEvent.KEYCODE_ENTER) {

                     m_signUpButton.callOnClick();

                   return true;
                 } else {
                      return false;
                  }
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
