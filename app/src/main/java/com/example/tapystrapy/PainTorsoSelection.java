package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;

public class PainTorsoSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pain_torso);

        findViewById(R.id.shoulders_layout).setTag(new BodyPartData("shoulders", "bark", Gender.MASCULINE));
        findViewById(R.id.chest_layout).setTag(new BodyPartData("chest", "klatka piersiowa", Gender.FEMININE));
        findViewById(R.id.stomach_layout).setTag(new BodyPartData("stomach", "brzuch", Gender.MASCULINE));
        findViewById(R.id.back_layout).setTag(new BodyPartData("back", "plecy", Gender.NEUTER));
        findViewById(R.id.lowerback_layout).setTag(new BodyPartData("lowerback", "lędźwie", Gender.NEUTER));
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
