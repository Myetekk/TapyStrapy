package com.example.tapystrapy;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.TapSdkFactory;


public class MainActivity extends AppCompatActivity {
    private TapSdk tapSdk;
    private String tapIdentifier = "";
    private MyTapListener tapListener;
    private TextView connectionStatus;
    private TextView thumbStatus, indexStatus, middleStatus, ringStatus, pinkyStatus, tapId, tapRepeatCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initializeTapSdk();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (tapSdk != null) {
//            tapSdk.resume();  // Switch TAP devices to Controller Mode
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (tapSdk != null) {
//            tapSdk.pause();  // Switch TAP devices back to Text Mode
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                this.tapSdk.registerTapListener(tapListener);
                connectionStatus.setText("Connecting");
            } else {
                Toast.makeText(this, "Failed to initialize Tap SDK", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
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
    }

    public void updateConnectionStatus(boolean connected) {
        runOnUiThread(() -> {
            connectionStatus.setText(connected ? "Connected" : "Disconnected");
            connectionStatus.setTextColor(connected ? 0xFF22c55e : 0xFFC02F2F);
            connectionStatus.setBackgroundColor(connected ? 0xFFeefaf2 : 0xFFffe4e4);
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
    }
}