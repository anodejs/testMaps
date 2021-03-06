package com.example.administrator.myapplication;

    import android.app.Activity;
import android.os.Bundle;
    import android.text.method.ScrollingMovementMethod;
    import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


    public class SidurActivity extends Activity {

        private Spinner spinnerType, spinnerVerison;
        private Button btnSubmit;
        private TextView sidorText;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sidur);

            sidorText =(TextView) findViewById(R.id.TextViewSidor);
            sidorText.setMovementMethod(new ScrollingMovementMethod());
            spinnerType = (Spinner) findViewById(R.id.spinnerType1);
            spinnerVerison = (Spinner) findViewById(R.id.spinnerVerison1);
            btnSubmit = (Button) findViewById(R.id.btnSubmit);


            addListenerOnSpinnerItemSelection();
            addListenerOnButton();
        }



        public void addListenerOnSpinnerItemSelection() {

            spinnerType.setOnItemSelectedListener(new CustomOnItemSelectedListener());
            spinnerVerison.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        }

        // get the selected dropdown list value
        public void addListenerOnButton() {


            btnSubmit.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    //send and get from the server

                    Toast.makeText(getApplicationContext(),
                            "OnClickListener : " +
                                    "\nSpinner 1 : "+ String.valueOf(spinnerType.getSelectedItem()) +
                                    "\nSpinner 2 : "+ String.valueOf(spinnerVerison.getSelectedItem()),
                            Toast.LENGTH_SHORT).show();

                }

            });
        }
    }
