package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.TapSdkFactory;
import com.example.tapystrapy.model.Gesture;

public class MainActivity extends AppCompatActivity {
    private TapSdk tapSdk;
    private MyTapListener tapListener;
    private LinearLayout main_feelingsSection, main_activitiesSection, main_painSection;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppState.getInstance().set_activity(this);

        setContentView(R.layout.activity_main);
        initializeTapSdk();
        initializeUIElements();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
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

    private void initializeUIElements() {
        main_feelingsSection = findViewById(R.id.main_feelingsSection);
        main_activitiesSection = findViewById(R.id.main_activitiesSection);
        main_painSection = findViewById(R.id.main_painSection);

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: main_feelingsSection.performClick(); break;
                case 1: main_activitiesSection.performClick(); break;
                case 2: main_painSection.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<2) {
            chosenElementId++;
            choseElement();
        }
        else if (gesture==Gesture.LEFT  &&  chosenElementId>0) {
            chosenElementId--;
            choseElement();
        }
        else choseElement();
    }
    private void choseElement() {
        main_feelingsSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        main_activitiesSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        main_painSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

        if (chosenElementId == 0) main_feelingsSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
        if (chosenElementId == 1) main_activitiesSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
        if (chosenElementId == 2) main_painSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
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
