package bit.simle1.musicspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AlertBuilderFragment confirmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create enroll button reference and the listener
        Button btnEnroll = (Button) findViewById(R.id.btnEnroll);
        btnEnroll.setOnClickListener(new btnEnrollHandler());

        // Create spinner reference
        Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);

        // String array from the resource string.xml gets saved into a string array
        String[] instrumentArray = getResources().getStringArray(R.array.monthsArray);

        // Create adapter that accepts the current activity, text view layout, the month array
        int layoutID = android.R.layout.simple_spinner_dropdown_item;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, layoutID, instrumentArray);

        // Sets the layout to create the drop down views
        arrayAdapter.setDropDownViewResource(layoutID);

        // Sets adapter to the spinner
        spinnerMonth.setAdapter(arrayAdapter);
    }

    public class btnEnrollHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Create references for all 3 radiobuttons
            RadioButton rdoBtnAccordion = (RadioButton) findViewById(R.id.rdoBtnAccordion);
            RadioButton rdoBtnBassoon = (RadioButton) findViewById(R.id.rdoBtnBassoon);
            RadioButton rdoBtnCello = (RadioButton) findViewById(R.id.rdoBtnCello);

            // Create reference for TextView
            TextView txtEnroll = (TextView) findViewById(R.id.txtEnroll);

            // Created reference to be able to grab the value from the spinner
            Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
            String monthPicked = spinnerMonth.getSelectedItem().toString();

            // Check if radio buttons have been checked
            if (rdoBtnAccordion.isChecked())
            {
                txtEnroll.setText("You are now enrolled for " + rdoBtnAccordion.getText() + " lessons in " + monthPicked);
            }
            else if (rdoBtnBassoon.isChecked())
            {
                txtEnroll.setText("You are now enrolled for " + rdoBtnBassoon.getText() + " lessons in " + monthPicked);
            }
            else if (rdoBtnCello.isChecked())
            {
                txtEnroll.setText("You are now enrolled for " + rdoBtnCello.getText() + " lessons in " + monthPicked);
            }
            else
            {
                Toast.makeText(MainActivity.this, "No instrument was selected!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getInstrumentStr()
    {
        // Create references for all 3 radiobuttons
        RadioButton rdoBtnAccordion = (RadioButton) findViewById(R.id.rdoBtnAccordion);
        RadioButton rdoBtnBassoon = (RadioButton) findViewById(R.id.rdoBtnBassoon);
        RadioButton rdoBtnCello = (RadioButton) findViewById(R.id.rdoBtnCello);

        // Create string to save message
        String message;

        // Created reference to be able to grab the value from the spinner
        Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        String monthPicked = spinnerMonth.getSelectedItem().toString();

        // Check if radio buttons have been checked
        if (rdoBtnAccordion.isChecked())
        {
            message = "You are now enrolled for " + rdoBtnAccordion.getText() + " lessons in " + monthPicked;
        }
        else if (rdoBtnBassoon.isChecked())
        {
            message = "You are now enrolled for " + rdoBtnBassoon.getText() + " lessons in " + monthPicked;
        }
        else if (rdoBtnCello.isChecked())
        {
            message = "You are now enrolled for " + rdoBtnCello.getText() + " lessons in " + monthPicked;
        }
        else
        {
            message = "No instrument was selected!";
        }

        return message;
    }

    public void giveMeMyData(boolean enroll)
    {
        //Dismisses the dialog
        confirmMusic.dismiss();

        // Create reference for TextView
        TextView txtEnroll = (TextView) findViewById(R.id.txtEnroll);

        // If enroll returns true then set the textView's text
        if (enroll)
        {
            txtEnroll.setText(getInstrumentStr());
        }
        else
        {
            txtEnroll.setText("Oh well.....");
        }
    }
}
