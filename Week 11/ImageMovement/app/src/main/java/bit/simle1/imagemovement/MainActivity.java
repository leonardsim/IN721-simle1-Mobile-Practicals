package bit.simle1.imagemovement;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Declare SensorManager and Sensor
    SensorManager sm;
    Sensor accelerometer;

    // ImageView x,y-coordinates
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to the device's sensors
        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);

        // Get the default sensor for a given type
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    private class setAccelerometerHandler implements SensorEventListener
    {

        @Override
        public void onSensorChanged(SensorEvent event) {

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
