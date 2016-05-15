package bit.simle1.imagemovement;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Declare SensorManager and Sensor
    SensorManager sm;
    Sensor accelerometer;

    // Declare ImageView
    ImageView ivBall;

    // ImageView x,y-coordinates
    float ivX;
    float ivY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to the device's sensors
        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);

        // Get reference
        ivBall = (ImageView) findViewById(R.id.ivBall);

        // Get the default sensor for a given type
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    private class setAccelerometerHandler implements SensorEventListener
    {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            // Increment the values
            ivX += x;
            ivY += y;

            // Set the X and Y coordinates
            ivBall.setX(ivX);
            ivBall.setY(ivY);

            // Redraws the image view
            ivBall.invalidate();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(new setAccelerometerHandler(), accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(new setAccelerometerHandler());
    }
}
