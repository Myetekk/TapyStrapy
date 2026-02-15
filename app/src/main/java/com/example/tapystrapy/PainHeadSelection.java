package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;
import com.example.tapystrapy.model.Gesture;

public class PainHeadSelection extends AppCompatActivity {
    private LinearLayout pain_head_forehead, pain_head_hairback, pain_head_entirehead, pain_head_eye, pain_head_nose, pain_head_tooth, pain_head_ear, pain_head_throat;
    private ImageView pain_forehead_image, pain_hairback_image, pain_entirehead_image, pain_eyes_image, pain_nose_image, pain_tooth_image, pain_ears_image, pain_throat_image;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_head);
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
        pain_head_forehead = findViewById(R.id.pain_head_forehead);
        pain_head_hairback = findViewById(R.id.pain_head_hairback);
        pain_head_entirehead = findViewById(R.id.pain_head_entirehead);
        pain_head_eye = findViewById(R.id.pain_head_eye);
        pain_head_nose = findViewById(R.id.pain_head_nose);
        pain_head_tooth = findViewById(R.id.pain_head_tooth);
        pain_head_ear = findViewById(R.id.pain_head_ear);
        pain_head_throat = findViewById(R.id.pain_head_throat);

        pain_forehead_image = findViewById(R.id.pain_forehead_image);
        pain_hairback_image = findViewById(R.id.pain_hairback_image);
        pain_entirehead_image = findViewById(R.id.pain_entirehead_image);
        pain_eyes_image = findViewById(R.id.pain_eyes_image);
        pain_nose_image = findViewById(R.id.pain_nose_image);
        pain_tooth_image = findViewById(R.id.pain_tooth_image);
        pain_ears_image = findViewById(R.id.pain_ears_image);
        pain_throat_image = findViewById(R.id.pain_throat_image);

        pain_head_forehead.setTag(new BodyPartData("forehead", "czoło", Gender.NEUTER));
        pain_head_hairback.setTag(new BodyPartData("hairback", "tył głowy", Gender.MASCULINE));
        pain_head_entirehead.setTag(new BodyPartData("entirehead", "cała głowa", Gender.FEMININE));
        pain_head_eye.setTag(new BodyPartData("eyes", "oczy", Gender.NEUTER));
        pain_head_nose.setTag(new BodyPartData("nose", "nos", Gender.MASCULINE));
        pain_head_tooth.setTag(new BodyPartData("tooth", "ząb", Gender.MASCULINE));
        pain_head_ear.setTag(new BodyPartData("ears", "ucho", Gender.NEUTER));
        pain_head_throat.setTag(new BodyPartData("throat", "gardło", Gender.NEUTER));

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: pain_head_forehead.performClick(); break;
                case 1: pain_head_hairback.performClick(); break;
                case 2: pain_head_entirehead.performClick(); break;
                case 3: pain_head_eye.performClick(); break;
                case 4: pain_head_nose.performClick(); break;
                case 5: pain_head_tooth.performClick(); break;
                case 6: pain_head_ear.performClick(); break;
                case 7: pain_head_throat.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<7) {
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
                case 0: pain_head_forehead.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_forehead_image.setImageResource(R.drawable.pain_forehead_chosen); break;
                case 1: pain_head_hairback.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_hairback_image.setImageResource(R.drawable.pain_hairback_chosen); break;
                case 2: pain_head_entirehead.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_entirehead_image.setImageResource(R.drawable.pain_entirehead_chosen); break;
                case 3: pain_head_eye.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_eyes_image.setImageResource(R.drawable.pain_eyes_chosen); break;
                case 4: pain_head_nose.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_nose_image.setImageResource(R.drawable.pain_nose_chosen); break;
                case 5: pain_head_tooth.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_tooth_image.setImageResource(R.drawable.pain_tooth_chosen); break;
                case 6: pain_head_ear.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_ears_image.setImageResource(R.drawable.pain_ears_chosen); break;
                case 7: pain_head_throat.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); pain_throat_image.setImageResource(R.drawable.pain_throat_chosen); break;
            }
        });
    }
    public void unchoseElement() {
        runOnUiThread(() -> {
            pain_head_forehead.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_hairback.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_entirehead.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_eye.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_nose.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_tooth.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_ear.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            pain_head_throat.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

            pain_forehead_image.setImageResource(R.drawable.pain_forehead);
            pain_hairback_image.setImageResource(R.drawable.pain_hairback);
            pain_entirehead_image.setImageResource(R.drawable.pain_entirehead);
            pain_eyes_image.setImageResource(R.drawable.pain_eyes);
            pain_nose_image.setImageResource(R.drawable.pain_nose);
            pain_tooth_image.setImageResource(R.drawable.pain_tooth);
            pain_ears_image.setImageResource(R.drawable.pain_ears);
            pain_throat_image.setImageResource(R.drawable.pain_throat);
        });
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

    public void changeView_Confirmation(View view) {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        BodyPartData bodyPartData = (BodyPartData) view.getTag();
        String fullAnswer = getFullAnswer(view);
        intent.putExtra("BODY_PART", "pain_" + bodyPartData.bodyPart);
        intent.putExtra("FULL_ANSWER", fullAnswer);
        startActivity(intent);
    }
}
