package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;
import com.example.tapystrapy.model.Gesture;

public class PainHandSelection extends AppCompatActivity {
    private LinearLayout pain_hand_arm, pain_hand_elbow, pain_hand_palm;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_hand);
        initializeUIElements();

        unchoseElement();
        if (AppState.getInstance().get_connectionStatus()) choseElement();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
        AppState.getInstance().initializeTapSdk();
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppState.getInstance().destroyTapSdk();
    }



    private void initializeUIElements() {
        pain_hand_arm = findViewById(R.id.pain_hand_arm);
        pain_hand_elbow = findViewById(R.id.pain_hand_elbow);
        pain_hand_palm = findViewById(R.id.pain_hand_palm);

        pain_hand_arm.setTag(new BodyPartData("arm", "ramię", Gender.NEUTER));
        pain_hand_elbow.setTag(new BodyPartData("elbow", "łokieć", Gender.MASCULINE));
        pain_hand_palm.setTag(new BodyPartData("palm", "dłoń", Gender.FEMININE));

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_hand_arm.performClick(); break;
                case 1: pain_hand_elbow.performClick(); break;
                case 2: pain_hand_palm.performClick(); break;
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
            case 0: pain_hand_arm.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: pain_hand_elbow.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: pain_hand_palm.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        pain_hand_arm.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_hand_elbow.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_hand_palm.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    public void changeView_Pain_Leftright(View view) {
        BodyPartData bodyPartData = (BodyPartData) view.getTag();

        Intent intent = new Intent(this, PainLeftRightSelection.class);
        intent.putExtra("BODY_PART", bodyPartData.bodyPart);
        intent.putExtra("BODY_PART_POLISH", bodyPartData.bodyPartPolish);
        intent.putExtra("GENDER", bodyPartData.gender.toString());
        startActivity(intent);
    }
}
