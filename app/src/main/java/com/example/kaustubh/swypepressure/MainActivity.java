package com.example.kaustubh.swypepressure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.TextView;

// reference for velocity : http://android-er.blogspot.in/2013/12/velocitytracker-track-velocity-of-touch.html?m=1
// reference for pressure: https://stackoverflow.com/questions/18538513/how-to-detect-screen-pressure-on-android

public class MainActivity extends AppCompatActivity {

    TextView textAction, textPressure, textVelocityX, textVelocityY,
            textMaxVelocityX, textMaxVelocityY;
    VelocityTracker velocityTracker = null;

    float maxXVelocity;
    float maxYVelocity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textAction = (TextView) findViewById(R.id.action);
        textPressure = (TextView) findViewById(R.id.pressure);
        textVelocityX = (TextView) findViewById(R.id.velocityx);
        textVelocityY = (TextView) findViewById(R.id.velocityy);
        textMaxVelocityX = (TextView) findViewById(R.id.maxvelocityx);
        textMaxVelocityY = (TextView) findViewById(R.id.maxvelocityy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        String pressed = String.valueOf(event.getPressure());
        textPressure.setText("Pressure: " + pressed);
        Log.d("Logged Pressure", pressed);

        return true;
    }

}