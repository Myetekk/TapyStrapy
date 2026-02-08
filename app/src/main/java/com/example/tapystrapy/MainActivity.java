package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class MainActivity extends AppCompatActivity {
    private LinearLayout main_feelingsSection, main_activitiesSection, main_painSection;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);
        initializeUIElements();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
        AppState.getInstance().initializeTapSdk();

        unchoseElement();
        if (AppState.getInstance().get_connectionStatus()) choseElement();
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppState.getInstance().destroyTapSdk();
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
        unchoseElement();
        switch (chosenElementId) {
            case 0: main_feelingsSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: main_activitiesSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: main_painSection.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        main_feelingsSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        main_activitiesSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        main_painSection.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
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
