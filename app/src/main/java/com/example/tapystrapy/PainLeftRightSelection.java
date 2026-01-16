package com.example.tapystrapy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PainLeftRightSelection extends AppCompatActivity {
    public enum Gender {
        MASCULINE,
        FEMININE,
        NEUTER
    }

    Gender gender;
    String genderStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pain_leftright);

        genderStr = getIntent().getStringExtra("GENDER");
        if (genderStr != null) {
            gender = Gender.valueOf(genderStr.toUpperCase());
        }

        genderLoad();
    }

    void genderLoad(){
        TextView questionText = findViewById((R.id.text_question));
        TextView leftText = findViewById((R.id.text_left));
        TextView rightText = findViewById((R.id.text_right));

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

    void temp(View view){
        //tu będzie kolejny change view ale to później się tym zajmę
    }
}
