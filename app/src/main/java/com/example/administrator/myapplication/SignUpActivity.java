package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.kobjects.util.Util;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import Wsdl2Code.WebServices.MinyanWSService.MinyanWSService;

public class SignUpActivity extends Activity {

    private MinyanApp m_app;
    private EditText m_emailTextView;
    private EditText m_nameTextView;
    private TextView m_errorTextView;
    private Button m_signUpButton;
    private boolean loginStatus;

    private static final String NAMESPACE = "http://api/";
    private static String URL = "http://vmedu61.mtacloud.co.il:8080/Minyan/MinyanWS?wsdl";
    private static final String METHOD_NAME = "insertUser";
    private static final String SOAP_ACTION = "http://api/MinyanWS/insertUser";


    String SENDER_ID = "901725098092";

    static final String TAG = "GCM Demo";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String PROPERTY_REG_ID = "registration_id";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;

    String regid;
    SoapObject response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        m_emailTextView = (EditText) findViewById(R.id.emailedittextview);
        m_nameTextView = (EditText) findViewById(R.id.nameedittextview);
        m_errorTextView = (TextView) findViewById(R.id.errortextview);
        m_signUpButton = (Button) findViewById(R.id.signupbutton);
        m_app = (MinyanApp) getApplication();

        context = getApplicationContext();

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }


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
                if (keyCode == 66) {

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


    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }

        return registrationId;
    }

    private void registerInBackground() {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    //
                    //
                    //       sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }


        }.execute(null, null, null);
    }


    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.commit();
    }

    private SharedPreferences getGcmPreferences(Context context) {
        return getSharedPreferences(DemoActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }


    class SignupTask extends AsyncTask<Void, Void, Boolean> {

        protected Boolean doInBackground(Void... params) {

            MinyanWSService service = new MinyanWSService();
       //     loginStatus = service.insertUser(regid, m_nameTextView.getText().toString(), m_emailTextView.getText().toString());

            return true;
            //todo
          //  return loginStatus;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (result) {
                m_app.setUserAccount(m_emailTextView.getText().toString(), m_nameTextView.getText().toString(), regid);
                Intent myIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
            else
            {
                m_errorTextView.setText("שם משתמש קיים, נא הכנס שם חדש");
                return;
            }
        }
    }

}