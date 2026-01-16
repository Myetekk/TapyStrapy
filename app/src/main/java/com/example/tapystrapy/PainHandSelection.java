package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class PainHandSelection extends AppCompatActivity {

    public class HandPartData {
        public String handPart, gender;

        public HandPartData(String bodyPart, String gender) {
            this.handPart = bodyPart;
            this.gender = gender;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pain_hand);

        findViewById(R.id.arm_layout).setTag(new HandPartData("arm", "neuter"));
        findViewById(R.id.elbow_layout).setTag(new HandPartData("elbow", "masculine"));
        findViewById(R.id.palm_layout).setTag(new HandPartData("palm", "feminine"));
    }

    public void changeView_Pain_Leftright(View view) {
        HandPartData handPartData = (HandPartData) view.getTag();

        Intent intent = new Intent(this, PainLeftRightSelection.class);
        intent.putExtra("BODY_PART", handPartData.handPart);
        intent.putExtra("GENDER", handPartData.gender);
        startActivity(intent);
    }
}
