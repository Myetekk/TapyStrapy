package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;

public class PainHeadSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_head);

        findViewById(R.id.forehead_layout).setTag(new BodyPartData("forehead", "czoło", Gender.NEUTER));
        findViewById(R.id.hairback_layout).setTag(new BodyPartData("hairback", "tył głowy", Gender.MASCULINE));
        findViewById(R.id.entirehead_layout).setTag(new BodyPartData("entirehead", "cała głowa", Gender.FEMININE));
        findViewById(R.id.eye_layout).setTag(new BodyPartData("eyes", "oko", Gender.NEUTER));
        findViewById(R.id.nose_layout).setTag(new BodyPartData("nose", "nos", Gender.MASCULINE));
        findViewById(R.id.tooth_layout).setTag(new BodyPartData("tooth", "ząb", Gender.MASCULINE));
        findViewById(R.id.ear_layout).setTag(new BodyPartData("ears", "ucho", Gender.NEUTER));
        findViewById(R.id.throat_layout).setTag(new BodyPartData("throat", "gardło", Gender.NEUTER));
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

    public void changeView_Final(View view) {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        BodyPartData bodyPartData = (BodyPartData) view.getTag();
        String fullAnswer = getFullAnswer(view);
        intent.putExtra("BODY_PART", "pain_" + bodyPartData.bodyPart);
        intent.putExtra("FULL_ANSWER", fullAnswer);
        startActivity(intent);
    }
}
