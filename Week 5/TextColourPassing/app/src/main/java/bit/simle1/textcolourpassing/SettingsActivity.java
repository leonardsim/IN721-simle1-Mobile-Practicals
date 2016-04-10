package bit.simle1.textcolourpassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        returnColour();
    }

    public void returnColour()
    {
        TextView tvColour = (TextView) findViewById(R.id.tvColourText);

        int textColour = tvColour.getCurrentTextColor();

        // Create an intent
        Intent returnIntent = new Intent();

        // Load up the return data
        returnIntent.putExtra("txtColour", textColour);

        // Set the return code
        setResult(Activity.RESULT_OK, returnIntent);

        // Pop off the Activity Stack
        finish();
    }
}
