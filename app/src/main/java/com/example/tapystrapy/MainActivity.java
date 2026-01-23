package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.TapSdkFactory;

public class MainActivity extends AppCompatActivity {
    private TapSdk tapSdk;
    private MyTapListener tapListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        initializeTapSdk();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tapSdk != null) {
            tapSdk.unregisterTapListener(tapListener);
        }
    }


    private void initializeTapSdk() {
        try {
            tapSdk = TapSdkFactory.getDefault(this);
            AppState.getInstance().set_tapSdk(tapSdk);
            if (tapSdk != null) {
                tapListener = new MyTapListener(tapSdk);
                tapSdk.registerTapListener(tapListener);
                AppState.getInstance().set_tapListener(tapListener);
            } else {
                Toast.makeText(this, "Failed to initialize Tap SDK", Toast.LENGTH_SHORT).show();
                Log.e("TAP", "Failed to initialize Tap SDK ");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("TAP", "Error: " + e.getMessage());
        }
    }


    public void changeView_Main_Feelings(View view) {
        Intent intent = new Intent(this, FeelingsActivity.class);
        startActivity(intent);
    }
    public void changeView_Main_Activities(View view) {
        Intent intent = new Intent(this, ActivitiesActivity.class);
        startActivity(intent);
    }
    public void changeView_Main_Pain(View view) {
        Intent intent = new Intent(this, PainMainSelection.class);
        startActivity(intent);
    }
    public void changeView_Main_Debug(View view) {
        Intent intent = new Intent(this, DebugActivity.class);
        startActivity(intent);
    }
}
