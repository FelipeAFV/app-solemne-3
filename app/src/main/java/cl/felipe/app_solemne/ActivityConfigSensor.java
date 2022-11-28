package cl.felipe.app_solemne;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityConfigSensor extends AppCompatActivity implements SensorEventListener {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accel=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if (Sensor.TYPE_ACCELEROMETER == sensor.getType() ) {
            float xAcc = sensorEvent.values[0];
            float yAcc = sensorEvent.values[1];
            float zAcc = sensorEvent.values[2];
            float umbral = sensor.getMaximumRange() /6;

            System.out.println("Capting sensor");
            if (xAcc > umbral || yAcc > umbral ||  zAcc > umbral ) {
                System.out.println("Capting sensor inside");
                Intent menuIntent = new Intent(this, MenuActivity.class);
                menuIntent.putExtra("userId", this.getIntent().getStringExtra("userId"));
                this.startActivity(menuIntent);
            }

        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
