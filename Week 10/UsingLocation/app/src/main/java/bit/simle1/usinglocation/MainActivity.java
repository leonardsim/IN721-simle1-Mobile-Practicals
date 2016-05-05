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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        // Generate random number
        Random rand = new Random();

        // The range to random Longitude
        double maxLong = 180;
        double minLong = -180;

        // The range to random Latitude
        double maxLat = 90;
        double minLat = -90;

        // Initialise longitude and latitude with random values
        // (max - min) gives the range
        // +1 includes the last value of max value\
        // +min gives it the start value
        double longRand = minLong + ((maxLong - minLong) + 1) * rand.nextDouble();
        double latRand = minLat + ((maxLat - minLat) + 1) * rand.nextDouble();

        // Formats the double to have 3 decimal places
        DecimalFormat precision = new DecimalFormat("0.000");

        // Create textView reference
        TextView tvLng = (TextView) findViewById(R.id.tvLng);
        TextView tvLat = (TextView) findViewById(R.id.tvLat);

        // Set the test with the dobule values
        tvLng.setText(precision.format(longRand));
        tvLat.setText(precision.format(latRand));
    }
}
