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
    private TextView thumbStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initializeTapSdk();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tapSdk != null) {
            tapSdk.resume();  // Switch TAP devices to Controller Mode
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tapSdk != null) {
            tapSdk.pause();  // Switch TAP devices back to Text Mode
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tapSdk != null) {
            // Unregister listeners and cleanup if needed
            tapSdk.unregisterTapListener(tapListener);
        }
    }

    private void initializeTapSdk() {
        try {
            Toast.makeText(this, "AAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
            initializeUIElements();

            tapSdk = TapSdkFactory.getDefault(this);
            if (tapSdk != null) {
                tapListener = new MyTapListener(tapSdk, this);
                this.tapSdk.registerTapListener(tapListener);
                // set connection status
            } else {

            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void initializeUIElements() {
        connectionStatus = findViewById(R.id.connectionStatus);
        thumbStatus = findViewById(R.id.thumbStatus);
    }

    public void setConnectionStatus(boolean connected) {
        runOnUiThread(() -> {
            connectionStatus.setText(connected ? "Connected" : "Disconnected");
            connectionStatus.setTextColor(connected ? 0xFF22c55e : 0xFFC02F2F);
            connectionStatus.setBackgroundColor(connected ? 0xFFeefaf2 : 0xFFffe4e4);
        });
    }
}