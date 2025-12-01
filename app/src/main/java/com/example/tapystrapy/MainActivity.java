package com.example.tapystrapy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.TapSdkFactory;

import java.util.Set;


public class MainActivity extends AppCompatActivity {
    private TapSdk tapSdk;
    private MyTapListener tapListener;
    private String tapIdentifier;
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

        setContentView(R.layout.activity_main);
        initializeTapSdk();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inactivityHandler.removeCallbacks(resetRunnable);
        if (tapSdk != null) {
            tapSdk.unregisterTapListener(tapListener);
        }
    }

    private void initializeTapSdk() {
        try {
            initializeUIElements();

            tapSdk = TapSdkFactory.getDefault(this);
            if (tapSdk != null) {
                tapListener = new MyTapListener(tapSdk, this);
                tapSdk.registerTapListener(tapListener);
//                tapSdk.disablePauseResumeHandling();
                connectionStatus.setText("Connecting");
            } else {
                Toast.makeText(this, "Failed to initialize Tap SDK", Toast.LENGTH_SHORT).show();
                Log.e("TAP", "Failed to initialize Tap SDK ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("TAP", "Error: " + e.getMessage());
        }
    }

    public void setTapId(String tapIdentifier) {
        this.tapIdentifier = tapIdentifier;
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
                tapInputLayout.setBackgroundColor(modes[0] ? 0xFFc9dbbd : 0xFFe1ebda);
                gyroscopeLayout.setBackgroundColor(modes[1] ? 0xFFc9dbbd : 0xFFe1ebda);
        });
    }

    public void updateFingerStatus(boolean[] fingers, int data, int repeatData) {
        runOnUiThread(() -> {
            thumbStatus.setText(fingers[0] ? "✓" : "—");
            indexStatus.setText(fingers[1] ? "✓" : "—");
            middleStatus.setText(fingers[2] ? "✓" : "—");
            ringStatus.setText(fingers[3] ? "✓" : "—");
            pinkyStatus.setText(fingers[4] ? "✓" : "—");

            tapId.setText(Integer.toString(data));
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
        tapSdk.startControllerMode(tapIdentifier);
        updateMode(new boolean[] {true, false, false, false});
    }

    public void changeMode_Gyro(View view) {
        Log.d("TAPPP", "changeMode_Gyro");
        tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0);
        updateMode(new boolean[] {false, true, false, false});
    }
}