package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gender;
import com.example.tapystrapy.model.Gesture;

public class PainLeftRightSelection extends AppCompatActivity {
    Gender gender;
    String genderStr, bodyPart, bodyPartSide, bodyPartPolish;
    TextView questionText, leftText, rightText;
    private ImageView pain_left_image, pain_right_image;
    private LinearLayout pain_left, pain_right;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_leftright);
        initializeUIElements();

        genderStr = getIntent().getStringExtra("GENDER");
        if (genderStr != null) {
            gender = Gender.valueOf(genderStr);
        }

        genderLoad();
        bodyPart = getIntent().getStringExtra("BODY_PART");
        bodyPartPolish =  getIntent().getStringExtra("BODY_PART_POLISH");
        imageLoad();
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
        questionText = findViewById((R.id.text_question));
        leftText = findViewById((R.id.text_left));
        rightText = findViewById((R.id.text_right));

        pain_left = findViewById(R.id.pain_left);
        pain_right = findViewById(R.id.pain_right);

        pain_left_image = findViewById(R.id.pain_left_image);
        pain_right_image = findViewById(R.id.pain_right_image);

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_left.performClick(); break;
                case 1: pain_right.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<1) {
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
            case 0:
                pain_left.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
                switch (bodyPart) {
                    case "ears":
                        pain_left_image.setImageResource(R.drawable.pain_ears_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_ears);
                        break;
                    case "arm":
                        pain_left_image.setImageResource(R.drawable.pain_arm_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_arm);
                        break;
                    case "elbow":
                        pain_left_image.setImageResource(R.drawable.pain_elbow_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_elbow);
                        break;
                    case "palm":
                        pain_left_image.setImageResource(R.drawable.pain_palm_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_palm);
                        break;
                    case "shoulders":
                        pain_left_image.setImageResource(R.drawable.pain_shoulders_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_shoulders_r);
                        break;
                    case "thigh":
                        pain_left_image.setImageResource(R.drawable.pain_thigh_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_thigh);
                        break;
                    case "knee":
                        pain_left_image.setImageResource(R.drawable.pain_knee_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_knee);
                        break;
                    case "calf":
                        pain_left_image.setImageResource(R.drawable.pain_calf_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_calf);
                        break;
                    case "foot":
                        pain_left_image.setImageResource(R.drawable.pain_foot_l_chosen);
                        pain_right_image.setImageResource(R.drawable.pain_foot);
                        break;
                }
                break;
            case 1:
                pain_right.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
                switch (bodyPart) {
                    case "ears":
                        pain_left_image.setImageResource(R.drawable.pain_ears_l);
                        pain_right_image.setImageResource(R.drawable.pain_ears_chosen);
                        break;
                    case "arm":
                        pain_left_image.setImageResource(R.drawable.pain_arm_l);
                        pain_right_image.setImageResource(R.drawable.pain_arm_chosen);
                        break;
                    case "elbow":
                        pain_left_image.setImageResource(R.drawable.pain_elbow_l);
                        pain_right_image.setImageResource(R.drawable.pain_elbow_chosen);
                        break;
                    case "palm":
                        pain_left_image.setImageResource(R.drawable.pain_palm_l);
                        pain_right_image.setImageResource(R.drawable.pain_palm_chosen);
                        break;
                    case "shoulders":
                        pain_left_image.setImageResource(R.drawable.pain_shoulders_l);
                        pain_right_image.setImageResource(R.drawable.pain_shoulders_r_chosen);
                        break;
                    case "thigh":
                        pain_left_image.setImageResource(R.drawable.pain_thigh_l);
                        pain_right_image.setImageResource(R.drawable.pain_thigh_chosen);
                        break;
                    case "knee":
                        pain_left_image.setImageResource(R.drawable.pain_knee_l);
                        pain_right_image.setImageResource(R.drawable.pain_knee_chosen);
                        break;
                    case "calf":
                        pain_left_image.setImageResource(R.drawable.pain_calf_l);
                        pain_right_image.setImageResource(R.drawable.pain_calf_chosen);
                        break;
                    case "foot":
                        pain_left_image.setImageResource(R.drawable.pain_foot_l);
                        pain_right_image.setImageResource(R.drawable.pain_foot_chosen);
                        break;
                }
                break;
        }
    }
    public void unchoseElement() {
        pain_left.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        pain_right.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

        switch (bodyPart) {
            case "ears":
                pain_left_image.setImageResource(R.drawable.pain_ears_l);
                pain_right_image.setImageResource(R.drawable.pain_ears);
                break;
            case "arm":
                pain_left_image.setImageResource(R.drawable.pain_arm_l);
                pain_right_image.setImageResource(R.drawable.pain_arm);
                break;
            case "elbow":
                pain_left_image.setImageResource(R.drawable.pain_elbow_l);
                pain_right_image.setImageResource(R.drawable.pain_elbow);
                break;
            case "palm":
                pain_left_image.setImageResource(R.drawable.pain_palm_l);
                pain_right_image.setImageResource(R.drawable.pain_palm);
                break;
            case "shoulders":
                pain_left_image.setImageResource(R.drawable.pain_shoulders_l);
                pain_right_image.setImageResource(R.drawable.pain_shoulders_r);
                break;
            case "thigh":
                pain_left_image.setImageResource(R.drawable.pain_thigh_l);
                pain_right_image.setImageResource(R.drawable.pain_thigh);
                break;
            case "knee":
                pain_left_image.setImageResource(R.drawable.pain_knee_l);
                pain_right_image.setImageResource(R.drawable.pain_knee);
                break;
            case "calf":
                pain_left_image.setImageResource(R.drawable.pain_calf_l);
                pain_right_image.setImageResource(R.drawable.pain_calf);
                break;
            case "foot":
                pain_left_image.setImageResource(R.drawable.pain_foot_l);
                pain_right_image.setImageResource(R.drawable.pain_foot);
                break;
        }
    }



    void genderLoad() {
        switch (gender) {
            case MASCULINE:
                questionText.setText(R.string.question_masculine);
                leftText.setText(R.string.left_masculine);
                rightText.setText(R.string.right_masculine);
                break;
            case FEMININE:
                questionText.setText(R.string.question_feminine);
                leftText.setText(R.string.left_feminine);
                rightText.setText(R.string.right_feminine);
                break;
            case NEUTER:
                questionText.setText(R.string.question_neuter);
                leftText.setText(R.string.left_neuter);
                rightText.setText(R.string.right_neuter);
                break;
        }
    }

    void imageLoad() {
        try {
            switch (bodyPart) {
                case "ears":
                    pain_left_image.setImageResource(R.drawable.pain_ears_l);
                    pain_right_image.setImageResource(R.drawable.pain_ears);
                    break;
                case "arm":
                    pain_left_image.setImageResource(R.drawable.pain_arm_l);
                    pain_right_image.setImageResource(R.drawable.pain_arm);
                    break;
                case "elbow":
                    pain_left_image.setImageResource(R.drawable.pain_elbow_l);
                    pain_right_image.setImageResource(R.drawable.pain_elbow);
                    break;
                case "palm":
                    pain_left_image.setImageResource(R.drawable.pain_palm_l);
                    pain_right_image.setImageResource(R.drawable.pain_palm);
                    break;
                case "shoulders":
                    pain_left_image.setImageResource(R.drawable.pain_shoulders_l);
                    pain_right_image.setImageResource(R.drawable.pain_shoulders_r);
                    break;
                case "thigh":
                    pain_left_image.setImageResource(R.drawable.pain_thigh_l);
                    pain_right_image.setImageResource(R.drawable.pain_thigh);
                    break;
                case "knee":
                    pain_left_image.setImageResource(R.drawable.pain_knee_l);
                    pain_right_image.setImageResource(R.drawable.pain_knee);
                    break;
                case "calf":
                    pain_left_image.setImageResource(R.drawable.pain_calf_l);
                    pain_right_image.setImageResource(R.drawable.pain_calf);
                    break;
                case "foot":
                    pain_left_image.setImageResource(R.drawable.pain_foot_l);
                    pain_right_image.setImageResource(R.drawable.pain_foot);
                    break;
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    String getFullAnswer(View view){
        String tag = (String) view.getTag();
        String fullAnswer = "";

        switch (tag) {
            case "left":
                fullAnswer = leftText.getText().toString() + " " + bodyPartPolish;
                bodyPartSide = bodyPart + "_l";
                break;
            case "right":
                fullAnswer = rightText.getText().toString() + " " + bodyPartPolish;
                bodyPartSide = bodyPart + "_r";
                break;
        }
        return fullAnswer;
    }

    public void changeView_Confirmation(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        String fullAnswer = getFullAnswer(view);
        intent.putExtra("BODY_PART", "pain_" + bodyPartSide);
        intent.putExtra("FULL_ANSWER", fullAnswer);
        startActivity(intent);
    }
}
