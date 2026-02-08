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

public class PainLegSelection extends AppCompatActivity {
    private LinearLayout pain_leg_thigh, pain_leg_knee, pain_leg_calf, pain_leg_foot;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_leg);
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
        pain_leg_thigh = findViewById(R.id.pain_leg_thigh);
        pain_leg_knee = findViewById(R.id.pain_leg_knee);
        pain_leg_calf = findViewById(R.id.pain_leg_calf);
        pain_leg_foot = findViewById(R.id.pain_leg_foot);

        pain_leg_thigh.setTag(new BodyPartData("thigh", "udo", Gender.NEUTER));
        pain_leg_knee.setTag(new BodyPartData("knee", "kolano", Gender.NEUTER));
        pain_leg_calf.setTag(new BodyPartData("calf", "łydka", Gender.FEMININE));
        pain_leg_foot.setTag(new BodyPartData("foot", "stopa", Gender.FEMININE));

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_leg_thigh.performClick(); break;
                case 1: pain_leg_knee.performClick(); break;
                case 2: pain_leg_calf.performClick(); break;
                case 3: pain_leg_foot.performClick(); break;
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
            case 0: pain_leg_thigh.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: pain_leg_knee.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: pain_leg_calf.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: pain_leg_foot.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        pain_leg_thigh.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_leg_knee.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_leg_calf.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_leg_foot.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
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
