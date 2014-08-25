package com.example.shakeitbaby;

import com.example.shakeitbaby.ShakerDetector.OnShakeListener;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
// accelerometer hardware
import android.hardware.Sensor;
import android.hardware.SensorManager;


public class MainActivity extends Activity {

    private ShakerDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.hornybaby);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakerDetector(new OnShakeListener() {
            @Override
            public void onShake() {
                // Do stuff!
            	mp.start();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }   
}