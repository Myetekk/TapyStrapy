package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class FeelingsActivity extends AppCompatActivity {
    private LinearLayout feelings_happy, feelings_sad, feelings_fear, feelings_angry, feelings_pain, feelings_tired;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_feelings);
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
        feelings_happy = findViewById(R.id.feelings_happy);
        feelings_sad = findViewById(R.id.feelings_sad);
        feelings_fear = findViewById(R.id.feelings_fear);
        feelings_angry = findViewById(R.id.feelings_angry);
        feelings_pain = findViewById(R.id.feelings_pain);
        feelings_tired = findViewById(R.id.feelings_tired);

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: feelings_happy.performClick(); break;
                case 1: feelings_sad.performClick(); break;
                case 2: feelings_fear.performClick(); break;
                case 3: feelings_angry.performClick(); break;
                case 4: feelings_pain.performClick(); break;
                case 5: feelings_tired.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<5) {
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
            case 0: feelings_happy.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: feelings_sad.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: feelings_fear.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: feelings_angry.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 4: feelings_pain.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 5: feelings_tired.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        feelings_happy.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        feelings_sad.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        feelings_fear.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        feelings_angry.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        feelings_pain.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        feelings_tired.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    public void changeView_Feelings_EmotionLevel(View view) {
        String emotion = (String) view.getTag();
        Intent intent = new Intent(this, EmotionLevel.class);
        intent.putExtra("EMOTION", emotion);
        startActivity(intent);
    }
}