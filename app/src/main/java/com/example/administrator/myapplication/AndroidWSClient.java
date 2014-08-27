package com.example.administrator.myapplication;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.serialization.PropertyInfo;


import android.os.AsyncTask;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

public class AndroidWSClient extends Activity {


    private static final String NAMESPACE = "http://api/";
    private static String URL = "http://vmedu61.mtacloud.co.il:8080/Minyan/MinyanWS?wsdl";
    private static final String METHOD_NAME = "stam";
    private static final String SOAP_ACTION = "http://api/MinyanWS/stam";
    String ans = "";
    private TextView lblResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.wsdltest);

        lblResult = (TextView) findViewById(R.id.result);

        new MyTask().execute();


    }

    private class MyTask extends AsyncTask<Void, Void, String> {

        String textResult;

        @Override
        protected String doInBackground(Void... params) {

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
                 ans = resultsRequestSOAP.toString();

            } catch (Exception e) {


            }
            return ans;
        }

        @Override
        protected void onPostExecute(String result) {

            lblResult.setText(result);

        }

    }
}
