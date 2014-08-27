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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SignUpActivity extends Activity{

    private MinyanApp m_app;
    private EditText m_emailTextView;
    private EditText m_nameTextView;
    private TextView m_errorTextView;
    private  Button m_signUpButton;
    private String regid;
    private boolean loginStatus;

    private static final String NAMESPACE = "http://api/";
    private static String URL = "http://vmedu61.mtacloud.co.il:8080/Minyan/MinyanWS?wsdl";
    private static final String METHOD_NAME = "stam";
    private static final String SOAP_ACTION = "http://api/MinyanWS/insertUser";

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
                DemoActivity demo = new DemoActivity();
                regid = demo.getRegID();

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

    private class SignupTask extends AsyncTask<Void, Void, Boolean> {


        protected Boolean doInBackground(Void... params) {


            // Create request
            SoapObject request =  new SoapObject(NAMESPACE, METHOD_NAME);
            // Property which holds input parameters
            PropertyInfo unamePI = new PropertyInfo();
            PropertyInfo passPI = new PropertyInfo();
            PropertyInfo regPI = new PropertyInfo();


            regPI.setName("googleId");
            //Set dataType
            regPI.setValue(regid);
            //Set dataType
            regPI.setType(String.class);
            //Add the property to request object
            request.addProperty(regPI);

            // Set Username
            unamePI.setName("userName");
            // Set Value
            unamePI.setValue(m_nameTextView.getText().toString());
            // Set dataType
            unamePI.setType(String.class);
            // Add the property to request object
            request.addProperty(unamePI);
            //Set Password
            passPI.setName("mail");
            //Set dataType
            passPI.setValue(m_emailTextView.getText().toString());
            //Set dataType
            passPI.setType(String.class);
            //Add the property to request object
            request.addProperty(passPI);



            // Create envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // Set output SOAP object
            envelope.setOutputSoapObject(request);
            // Create HTTP call object
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            try {
                // Invoke web service
                androidHttpTransport.call(SOAP_ACTION, envelope);
                // Get the response
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                // Assign it to  boolean variable variable
                loginStatus = Boolean.parseBoolean(response.toString());
                m_app.setUserAccount(m_emailTextView.getText().toString(), m_nameTextView.getText().toString(),regid );


            } catch (Exception e) {
                //Assign Error Status true in static variable 'errored'
             //   CheckLoginActivity.errored = true;
                e.printStackTrace();
            }
            //Return booleam to calling object
            return loginStatus;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            Intent myIntent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }
    }
}
