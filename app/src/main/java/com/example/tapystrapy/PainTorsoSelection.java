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

public class PainTorsoSelection extends AppCompatActivity {
    private LinearLayout pain_torso_shoulders, pain_torso_chest, pain_torso_stomach, pain_torso_back, pain_torso_lowerback;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_torso);
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
        pain_torso_shoulders = findViewById(R.id.pain_torso_shoulders);
        pain_torso_chest = findViewById(R.id.pain_torso_chest);
        pain_torso_stomach = findViewById(R.id.pain_torso_stomach);
        pain_torso_back = findViewById(R.id.pain_torso_back);
        pain_torso_lowerback = findViewById(R.id.pain_torso_lowerback);

        pain_torso_shoulders.setTag(new BodyPartData("shoulders", "bark", Gender.MASCULINE));
        pain_torso_chest.setTag(new BodyPartData("chest", "klatka piersiowa", Gender.FEMININE));
        pain_torso_stomach.setTag(new BodyPartData("stomach", "brzuch", Gender.MASCULINE));
        pain_torso_back.setTag(new BodyPartData("back", "plecy", Gender.NEUTER));
        pain_torso_lowerback.setTag(new BodyPartData("lowerback", "lędźwie", Gender.NEUTER));

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_torso_shoulders.performClick(); break;
                case 1: pain_torso_chest.performClick(); break;
                case 2: pain_torso_stomach.performClick(); break;
                case 3: pain_torso_back.performClick(); break;
                case 4: pain_torso_lowerback.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<4) {
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
            case 0: pain_torso_shoulders.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: pain_torso_chest.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: pain_torso_stomach.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: pain_torso_back.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 4: pain_torso_lowerback.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        pain_torso_shoulders.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_torso_chest.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_torso_stomach.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_torso_back.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_torso_lowerback.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    public void changeView_Pain_Leftright(View view) {
        BodyPartData bodyPartData = (BodyPartData) view.getTag();

        Intent intent = new Intent(this, PainLeftRightSelection.class);
        intent.putExtra("BODY_PART", bodyPartData.bodyPart);
        intent.putExtra("BODY_PART_POLISH", bodyPartData.bodyPartPolish);
        intent.putExtra("GENDER", bodyPartData.gender.toString());
        startActivity(intent);
    }

    /**
     * Variant of getFullAnswer for single body parts.
     * @param view
     * @return
     */
    String getFullAnswer(View view) {
        BodyPartData bodyPartData = (BodyPartData) view.getTag();
        String fullAnswer = bodyPartData.bodyPartPolish.substring(0,1).toUpperCase() + bodyPartData.bodyPartPolish.substring(1);

        return fullAnswer;
    }

    public void changeView_Final(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        BodyPartData bodyPartData = (BodyPartData) view.getTag();
        String fullAnswer = getFullAnswer(view);
        intent.putExtra("BODY_PART", "pain_" + bodyPartData.bodyPart);
        intent.putExtra("FULL_ANSWER", fullAnswer);
        startActivity(intent);
    }
}
