package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tapystrapy.model.Gender;

public class PainLeftRightSelection extends AppCompatActivity {
    Gender gender;
    String genderStr, bodyPart, bodyPartPolish;
    TextView questionText, leftText, rightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pain_leftright);

        genderStr = getIntent().getStringExtra("GENDER");
        if (genderStr != null) {
            gender = Gender.valueOf(genderStr);
        }


        questionText = findViewById((R.id.text_question));
        leftText = findViewById((R.id.text_left));
        rightText = findViewById((R.id.text_right));

        genderLoad();
        bodyPart = getIntent().getStringExtra("BODY_PART");
        bodyPartPolish =  getIntent().getStringExtra("BODY_PART_POLISH");
    }

    void genderLoad(){
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

    String getFullAnswer(View view){
        String tag = (String) view.getTag();
        String fullAnswer = "";

        switch (tag) {
            case "left": fullAnswer = leftText.getText().toString() + " " + bodyPartPolish; break;
            case "right": fullAnswer = rightText.getText().toString() + " " + bodyPartPolish; break;
        }
        return fullAnswer;
    }

    public void changeView_Final(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        String fullAnswer = getFullAnswer(view);
        intent.putExtra("BODY_PART", "pain_" + bodyPart);
        intent.putExtra("FULL_ANSWER", fullAnswer);
        startActivity(intent);
    }
}
