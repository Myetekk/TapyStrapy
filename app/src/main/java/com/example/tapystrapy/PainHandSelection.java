package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;

public class PainHandSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pain_hand);

        findViewById(R.id.arm_layout).setTag(new BodyPartData("arm", "ramię", Gender.NEUTER));
        findViewById(R.id.elbow_layout).setTag(new BodyPartData("elbow", "łokieć", Gender.MASCULINE));
        findViewById(R.id.palm_layout).setTag(new BodyPartData("palm", "dłoń", Gender.FEMININE));
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
