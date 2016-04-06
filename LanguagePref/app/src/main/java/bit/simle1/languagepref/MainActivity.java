package bit.simle1.languagepref;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Declare SharedPreferences and Editor
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate SharedPreferences and Editor
        // getSharedPreferences looks for a pre-exiting stored sharedPreferences called 'sPref',
        // if there isn't one it creates and returns it. If there is, it fetches it and returns it
        // It can create and fetch
        prefs = getSharedPreferences("sPref", MODE_PRIVATE);

        // The method will return an Editor object which is bound to the Shared Preferences object, and which will be sued to write key-value
        // pairs to
        prefsEditor = prefs.edit();

        // Create button reference for language and set listener
        Button btnLang = (Button) findViewById(R.id.btnLang);
        btnLang.setOnClickListener(new SetLanguageClickHandler());

        // Create button reference for colour and set listener
        Button btnColour = (Button) findViewById(R.id.btnColour);
        btnColour.setOnClickListener(new SetColourClickHandler());


        // Fetch the stored language preference
        // Returns null if the provided key-value pair hasn't been set
        String languagePref = prefs.getString("language", null);
        if(languagePref != null)
        {
            String greetingInChosenLang = getGreeting(languagePref);

            // Create the reference and set the text to the chosen language
            TextView tvLang = (TextView) findViewById(R.id.tvLang);
            tvLang.setText(greetingInChosenLang);
        }

        // Fetch the stored colour preference
        // Returns null if the provided key-value pair hasn't been set
        String colourPref = prefs.getString("colour", null);
        if(colourPref != null)
        {
            int chosenColour = getColour(colourPref);

            // Create the reference and set the text to the chosen language
            TextView tvLang = (TextView) findViewById(R.id.tvLang);
            tvLang.setTextColor(chosenColour);
        }
    }

    // Event Handlers
    public class SetLanguageClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // Find out which language radio button is selected
            RadioGroup rg = (RadioGroup) findViewById(R.id.rdoGrpLang);

            // Gets the id of the checked radio button
            int checkedId = rg.getCheckedRadioButtonId();

            // Create reference to the checked button
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);

            // Set text value to the checkedLanguage string
            String checkedLanguage = checkedButton.getText().toString();

            // Store new chosen language in prefs
            prefsEditor.putString("language", checkedLanguage);
            prefsEditor.commit();

            TextView tvLang = (TextView) findViewById(R.id.tvLang);
            tvLang.setText(getGreeting(checkedLanguage));
        }
    }

    public class SetColourClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            // Find out which language radio button is selected
            RadioGroup rg = (RadioGroup) findViewById(R.id.rdoGrpColour);

            // Gets the id of the checked radio button
            int checkedId = rg.getCheckedRadioButtonId();

            // Create reference to the checked button
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);

            // Set text value to the checkedLanguage string
            String checkedColour = checkedButton.getText().toString();

            // Store new chosen language in prefs
            prefsEditor.putString("colour", checkedColour);
            prefsEditor.commit();

            TextView tvLang = (TextView) findViewById(R.id.tvLang);
            tvLang.setTextColor(getColour(checkedColour));
        }
    }

    // Methods
    // Returns a greeting message
    private String getGreeting(String language)
    {
        String greeting = "";

        switch (language)
        {
            case ("French"):
                greeting = "Bonjour Le Monde";
                break;
            case ("German"):
                greeting = "Hallo Welt";
                break;
            case ("Spanish"):
                greeting = "Hola Mundo";
                break;
            default:
                break;
        }

        return greeting;
    }

    // Returns an int colour
    private int getColour(String colour)
    {
        int langColor = 0;

        switch (colour)
        {
            case ("Red"):
                langColor = Color.RED;
                break;
            case ("Blue"):
                langColor = Color.BLUE;
                break;
            case ("Green"):
                langColor = Color.GREEN;
                break;
            default:
                break;
        }

        return langColor;
    }
}
