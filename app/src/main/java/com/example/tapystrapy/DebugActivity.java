package com.example.tapystrapy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class DebugActivity extends AppCompatActivity {
    private static final int inactivityTimeout = 3000;
    private TextView connectionStatus;
    private LinearLayout tapInputLayout, gyroscopeLayout;
    private TextView thumbStatus, indexStatus, middleStatus, ringStatus, pinkyStatus, tapId, tapRepeatCount;
    private TextView gyroX, gyroY, gyroZ;


    private final Handler inactivityHandler = new Handler(Looper.getMainLooper());
    private final Runnable resetRunnable = new Runnable() {
        @Override
        public void run() {
            thumbStatus.setText("—");
            indexStatus.setText("—");
            middleStatus.setText("—");
            ringStatus.setText("—");
            pinkyStatus.setText("—");
            tapId.setText("—");
            tapRepeatCount.setText("—");

            gyroX.setText("—");
            gyroY.setText("—");
            gyroZ.setText("—");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppState.getInstance().set_activity(this);

        setContentView(R.layout.activity_debug);
        initializeUIElements();

        AppState.getInstance().call_updateConnectionStatus();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        inactivityHandler.removeCallbacks(resetRunnable);
    }



    public void onConnected() {
        tapInputLayout.setFocusable(true);
        tapInputLayout.setClickable(true);
        gyroscopeLayout.setFocusable(true);
        gyroscopeLayout.setClickable(true);

        updateMode(new boolean[] {true, false, false, false});
    }
    public void onDisconnected() {
        tapInputLayout.setFocusable(false);
        tapInputLayout.setClickable(false);
        gyroscopeLayout.setFocusable(false);
        gyroscopeLayout.setClickable(false);

        updateMode(new boolean[] {false, false, false, false});
    }

    private void initializeUIElements() {
        connectionStatus = findViewById(R.id.connectionStatus);

        thumbStatus = findViewById(R.id.thumbStatus);
        indexStatus = findViewById(R.id.indexStatus);
        middleStatus = findViewById(R.id.middleStatus);
        ringStatus = findViewById(R.id.ringStatus);
        pinkyStatus = findViewById(R.id.pinkyStatus);
        tapId = findViewById(R.id.tapId);
        tapRepeatCount = findViewById(R.id.tapRepeatCount);

        gyroX = findViewById(R.id.gyroX);
        gyroY = findViewById(R.id.gyroY);
        gyroZ = findViewById(R.id.gyroZ);

        tapInputLayout = findViewById(R.id.tapInputLayout);
        gyroscopeLayout = findViewById(R.id.gyroscopeLayout);
    }

    public void updateConnectionStatus(boolean connected) {
        runOnUiThread(() -> {
            connectionStatus.setText(connected ? "Connected" : "Disconnected");
            connectionStatus.setTextColor(connected ? 0xFF22c55e : 0xFFC02F2F);
            connectionStatus.setBackgroundColor(connected ? 0xFFeefaf2 : 0xFFffe4e4);
        });
    }
    public void updateMode(boolean[] modes) {
        runOnUiThread(() -> {
                tapInputLayout.setBackgroundColor(modes[0] ? ContextCompat.getColor(this, R.color.chosen_element) : ContextCompat.getColor(this, R.color.almost_white));
                gyroscopeLayout.setBackgroundColor(modes[1] ? ContextCompat.getColor(this, R.color.chosen_element) : ContextCompat.getColor(this, R.color.almost_white));
        });
    }
    public void updateFingerStatus(boolean[] fingers, int fingersId, int repeatData) {
        runOnUiThread(() -> {
            thumbStatus.setText(fingers[0] ? "✓" : "—");
            indexStatus.setText(fingers[1] ? "✓" : "—");
            middleStatus.setText(fingers[2] ? "✓" : "—");
            ringStatus.setText(fingers[3] ? "✓" : "—");
            pinkyStatus.setText(fingers[4] ? "✓" : "—");

            tapId.setText(Integer.toString(fingersId));
            tapRepeatCount.setText(Integer.toString(repeatData));
        });

        inactivityHandler.removeCallbacks(resetRunnable);
        inactivityHandler.postDelayed(resetRunnable, inactivityTimeout);
    }
    public void updateGyro(double[] gyro) {
        runOnUiThread(() -> {
            gyroX.setText(String.valueOf(gyro[0]));
            gyroY.setText(String.valueOf(gyro[1]));
            gyroZ.setText(String.valueOf(gyro[2]));
        });

        inactivityHandler.removeCallbacks(resetRunnable);
        inactivityHandler.postDelayed(resetRunnable, inactivityTimeout);
    }


    public void changeMode_Tap(View view) {
        Log.d("TAPPP", "changeMode_Tap");
        AppState.getInstance().call_startControllerMode();
        updateMode(new boolean[] {true, false, false, false});
    }
    public void changeMode_Gyro(View view) {
        Log.d("TAPPP", "changeMode_Gyro");
        AppState.getInstance().call_startRawSensorMode();
        updateMode(new boolean[] {false, true, false, false});
    }
}