package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class PainMainSelection extends AppCompatActivity {
    private LinearLayout pain_head, pain_hands, pain_body, pain_legs;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_main);
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
        pain_head = findViewById(R.id.pain_head);
        pain_hands = findViewById(R.id.pain_hands);
        pain_body = findViewById(R.id.pain_body);
        pain_legs = findViewById(R.id.pain_legs);

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_head.performClick(); break;
                case 1: pain_hands.performClick(); break;
                case 2: pain_body.performClick(); break;
                case 3: pain_legs.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<3) {
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
            case 0: pain_head.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: pain_hands.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: pain_body.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: pain_legs.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        pain_head.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_hands.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_body.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_legs.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    public void changeView_Pain_Head(View view) {
        Intent intent = new Intent(this, PainHeadSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Hand(View view) {
        Intent intent = new Intent(this, PainHandSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Torso(View view) {
        Intent intent = new Intent(this, PainTorsoSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Leg(View view) {
        Intent intent = new Intent(this, PainLegSelection.class);
        startActivity(intent);
    }
}
