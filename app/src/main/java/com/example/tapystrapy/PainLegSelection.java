package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tapystrapy.model.BodyPartData;
import com.example.tapystrapy.model.Gender;

public class PainLegSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pain_leg);

        findViewById(R.id.thigh_layout).setTag(new BodyPartData("thigh", "udo", Gender.NEUTER));
        findViewById(R.id.knee_layout).setTag(new BodyPartData("knee", "kolano", Gender.NEUTER));
        findViewById(R.id.calf_layout).setTag(new BodyPartData("calf", "łydka", Gender.FEMININE));
        findViewById(R.id.foot_layout).setTag(new BodyPartData("foot", "stopa", Gender.FEMININE));
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
}
