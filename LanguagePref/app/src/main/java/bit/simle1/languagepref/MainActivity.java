package bit.simle1.languagepref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

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

    }

    public class SetLanguageClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // Find out which language radio button is selected
            RadioGroup gp = (RadioGroup) findViewById(R.id.)
        }
    }
}
