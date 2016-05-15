package bit.simle1.imagemovement;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

    // Height and Width of screen
    int height;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to the device's sensors
        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);

        // Get reference
        ivBall = (ImageView) findViewById(R.id.ivBall);

        // Get the width and height of screen at runtime
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        // Set the imageView's initial location
        ivX = (width / 2) - 50;
        ivY = (height / 2) - 50;
        ivBall.setX(ivX);
        ivBall.setY(ivY);

        // Get the default sensor for a given type
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    private class setAccelerometerHandler implements SensorEventListener
    {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            float velX = x * 10 * -1;
            float velY = y * 10;

            // Increment the values
            ivX += velX;
            ivY += velY;

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
