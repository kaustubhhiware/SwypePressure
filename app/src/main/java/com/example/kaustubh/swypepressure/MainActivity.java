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
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(event);
                maxXVelocity = 0;
                maxYVelocity = 0;

                textVelocityX.setText("X-velocity (pixel/s): 0");
                textVelocityY.setText("Y-velocity (pixel/s): 0");
                textMaxVelocityX.setText("max. X-velocity: 0");
                textMaxVelocityY.setText("max. Y-velocity: 0");

                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                //1000 provides pixels per second

                float xVelocity = velocityTracker.getXVelocity();
                float yVelocity = velocityTracker.getYVelocity();

                if(xVelocity > maxXVelocity){
                    //max in right side
                    maxXVelocity = xVelocity;
                }

                if(yVelocity > maxYVelocity){
                    //Max in down side
                    maxYVelocity = yVelocity;
                }

                textVelocityX.setText("X-velocity (pixel/s): " + xVelocity);
                textVelocityY.setText("Y-velocity (pixel/s): " + yVelocity);
                textMaxVelocityX.setText("max. X-velocity: " + maxXVelocity);
                textMaxVelocityY.setText("max. Y-velocity: " + maxYVelocity);
                String vstat = "(" + String.valueOf(xVelocity)+ "," + String.valueOf(yVelocity) + ")";
                Log.d("Logged Velocity-X, Y", vstat);

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.recycle();
                break;
        }
        Log.d("Logged Pressure", pressed);

        return true;
    }

}