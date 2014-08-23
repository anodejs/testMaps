package com.example.administrator.myapplication;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.serialization.PropertyInfo;


import android.os.AsyncTask;
import android.text.format.Time;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AndroidWSClient extends Activity {


    private static final String NAMESPACE = "http://api/";
    private static String URL = "http://vmedu61.mtacloud.co.il:8080/Minyan/MinyanWS?wsdl";
    private static final String METHOD_NAME = "stam";
    private static final String SOAP_ACTION = "http://api/MinyanWS/stam";

    private TextView lblResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.wsdltest);

        lblResult = (TextView) findViewById(R.id.result);


        new MyTask().execute();


    }

    private class MyTask extends AsyncTask<Void, Void, Void>{

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            PropertyInfo propInfo = new PropertyInfo();
            propInfo.name="arg0";
            propInfo.type=PropertyInfo.STRING_CLASS;

            request.addProperty(propInfo, "John Smith");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);

                SoapPrimitive  resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
                String ans = resultsRequestSOAP.toString();
              lblResult.setText(ans);
                Toast.makeText(getApplicationContext(), ans, Toast.LENGTH_LONG).show();

            } catch (Exception e) {

                lblResult.setText(e.getMessage().toString());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            lblResult.setText(textResult);


            super.onPostExecute(result);
        }

    }
}
