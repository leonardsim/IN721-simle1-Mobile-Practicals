package com.bit.simle1.sensortest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvSensor;
    SensorManager sm;
    Sensor sensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);
        sensorLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        tvSensor = (TextView) findViewById(R.id.tvSensor);

        Button btnSensor = (Button) findViewById(R.id.btnSensor);
        btnSensor.setOnClickListener(new setSensorHandler());
    }

    public class setSensorHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            List<Sensor> listSensor = sm.getSensorList(Sensor.TYPE_ALL);

            StringBuilder sb = new StringBuilder();

            for(Sensor sensor: listSensor)
            {
                sb.append(sensor.getName() + "\n");
            }

            tvSensor.setText(sb.toString());
        }
    }

    public class setIlluminationHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            
        }
    }

    public class SensorLight implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float lumenReading = event.values[0];

            //ref txtbox
            TextView txtLumen = (TextView) findViewById(R.id.txtLumen);

            txtLumen.setText("" + lumenReading);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        sm.unregisterListener(new SensorLight());
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        sm.registerListener(new SensorLight(), sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
