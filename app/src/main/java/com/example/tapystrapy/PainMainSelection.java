package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class PainMainSelection extends AppCompatActivity {
    private LinearLayout pain_head, pain_hands, pain_body, pain_legs;
    private ImageView pain_head_image, pain_hands_image, pain_body_image, pain_legs_image;
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

        pain_head_image = findViewById(R.id.pain_head_image);
        pain_hands_image = findViewById(R.id.pain_hands_image);
        pain_body_image = findViewById(R.id.pain_body_image);
        pain_legs_image = findViewById(R.id.pain_legs_image);

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
        runOnUiThread(() -> {
            unchoseElement();
            switch (chosenElementId) {
                case 0: pain_head.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_head_image.setImageResource(R.drawable.pain_head_chosen); break;
                case 1: pain_hands.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_hands_image.setImageResource(R.drawable.pain_hands_chosen); break;
                case 2: pain_body.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_body_image.setImageResource(R.drawable.pain_torso_chosen); break;
                case 3: pain_legs.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_legs_image.setImageResource(R.drawable.pain_legs_chosen); break;
            }
        });
    }
    public void unchoseElement() {
        runOnUiThread(() -> {
            pain_head.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_hands.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_body.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_legs.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

            pain_head_image.setImageResource(R.drawable.pain_head);
            pain_hands_image.setImageResource(R.drawable.pain_hands);
            pain_body_image.setImageResource(R.drawable.pain_torso);
            pain_legs_image.setImageResource(R.drawable.pain_legs);
        });
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
