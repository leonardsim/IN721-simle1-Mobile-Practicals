package bit.simle1.usinglocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declare Location class
    Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Location class
        currentLocation = new Location();

        // Create button reference and set listener
        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new SetLocationHandler());
    }

    // Event Handlers
    public class SetLocationHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            generateRandomNumber();
        }
    }

    // Methods
    private void generateRandomNumber()
    {
        currentLocation.generateRandomValues();

        // Formats the double to have 3 decimal places
        DecimalFormat precision = new DecimalFormat("0.000");

        // Create textView reference
        TextView tvLng = (TextView) findViewById(R.id.tvLng);
        TextView tvLat = (TextView) findViewById(R.id.tvLat);

        // Set the test with the dobule values
        tvLng.setText(precision.format(currentLocation.getLongVal()));
        tvLat.setText(precision.format(currentLocation.getLatVal()));
    }
}
