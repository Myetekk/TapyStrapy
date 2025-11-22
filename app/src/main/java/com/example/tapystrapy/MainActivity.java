package com.example.tapystrapy;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.TapSdkFactory;


public class MainActivity extends AppCompatActivity {
    private TapSdk tapSdk;
    private String tapIdentifier = "";
    private MyTapListener tapListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("");

        this.tapSdk = TapSdkFactory.getDefault(this);

        tapSdk.enableDebug();

        tapListener = new MyTapListener(this.tapSdk);
        this.tapSdk.registerTapListener(tapListener);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
////        log("tap id: " + this.tapIdentifier);
//        vibrate();
//    }
//
//    private void vibrate() {
//        log("vibrate - start");
//        log("tap id: " + this.tapIdentifier);
//        this.tapSdk.vibrate(this.tapIdentifier, new int[] {5000});
//        log("vibrate - end");
//    }

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

    private void log(String message) {
        Log.println(Log.DEBUG, "customLogger", message);
    }
}