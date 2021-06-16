package com.example.laboratorio07b;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensorAcelerometer,sensorMagnetic;
    SensorEventListener sensorEvent,sensorEventMagnetic;
    TextView GravX, GravY, GravZ,MagneX,MagneY,MagneZ,salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorAcelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        GravX = (TextView)findViewById(R.id.GraX);
        GravY = (TextView)findViewById(R.id.GraY);
        GravZ = (TextView)findViewById(R.id.GraZ);
        MagneX = (TextView)findViewById(R.id.MagX);
        MagneY = (TextView)findViewById(R.id.MagY);
        MagneZ = (TextView)findViewById(R.id.MagZ);
        salida = (TextView)findViewById(R.id.Respuesta);
        if(sensorAcelerometer ==null && sensorMagnetic ==null)
            finish();
        sensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float X = sensorEvent.values[0];
                float Y = sensorEvent.values[1];
                float Z = sensorEvent.values[2];
                GravX.setText("X: \t"+String.valueOf(X));
                GravY.setText("Y: \t"+String.valueOf(Y));
                GravZ.setText("Z: \t"+String.valueOf(Z));
                if(X>0 && Y>-1 && Y<1)
                    salida.setText("HORIZONTAL 1");
                if(X<0 && Y>-1 && Y<1)
                    salida.setText("HORIZONTAL 2");
                if(Y>0 && X>-1 && X<1 && Z>0)
                    salida.setText("VERTICAL 1");
                if(Y<0 && X>-1 && X<1 && Z>0)
                    salida.setText("VERTICAL 2");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorEventMagnetic = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float X = sensorEvent.values[0];
                float Y = sensorEvent.values[1];
                float Z = sensorEvent.values[2];
                MagneX.setText("X: \t"+String.valueOf(X));
                MagneY.setText("Y: \t"+String.valueOf(Y));
                MagneZ.setText("Z: \t"+String.valueOf(Z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }
    private void start(){
        sensorManager.registerListener(sensorEvent, sensorAcelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventMagnetic, sensorMagnetic,SensorManager.SENSOR_DELAY_NORMAL);
    }

}