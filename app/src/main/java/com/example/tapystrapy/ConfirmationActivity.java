package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity  extends AppCompatActivity {
    String emotion, bodyPart, bodyFullAnswer;
    private ImageView confirmationImage;
    private TextView confirmationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppState.getInstance().set_activity(this);

        setContentView(R.layout.activity_confirmation);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        bodyPart = intent.getStringExtra("BODY_PART");
        bodyFullAnswer = intent.getStringExtra("FULL_ANSWER");

        setConfirmationImage();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
    }

    private void initializeUIElements() {
        confirmationImage = findViewById(R.id.confirmationImage);
        confirmationLabel = findViewById(R.id.confirmationLabel);
    }

    private void setConfirmationImage() {
        try {
            if (emotion != null) {
                switch (emotion) {
                    case "emotion_happy_1": confirmationImage.setImageResource(R.drawable.emote_happy_1); confirmationLabel.setText("Lekka radość"); break;
                    case "emotion_happy_2": confirmationImage.setImageResource(R.drawable.emote_happy_2); confirmationLabel.setText("Radość"); break;
                    case "emotion_happy_3": confirmationImage.setImageResource(R.drawable.emote_happy_3); confirmationLabel.setText("Duża radość"); break;

                    case "emotion_sad_1": confirmationImage.setImageResource(R.drawable.emote_sad_1); confirmationLabel.setText("Lekki smutek"); break;
                    case "emotion_sad_2": confirmationImage.setImageResource(R.drawable.emote_sad_2); confirmationLabel.setText("Smutek"); break;
                    case "emotion_sad_3": confirmationImage.setImageResource(R.drawable.emote_sad_3); confirmationLabel.setText("Duży smutek"); break;

                    case "emotion_fear_1": confirmationImage.setImageResource(R.drawable.emote_feared_1); confirmationLabel.setText("Lekki strach"); break;
                    case "emotion_fear_2": confirmationImage.setImageResource(R.drawable.emote_feared_2); confirmationLabel.setText("Strach"); break;
                    case "emotion_fear_3": confirmationImage.setImageResource(R.drawable.emote_feared_3); confirmationLabel.setText("Duży strach"); break;

                    case "emotion_angry_1": confirmationImage.setImageResource(R.drawable.emote_angry_1); confirmationLabel.setText("Lekka złość"); break;
                    case "emotion_angry_2": confirmationImage.setImageResource(R.drawable.emote_angry_2); confirmationLabel.setText("Złość"); break;
                    case "emotion_angry_3": confirmationImage.setImageResource(R.drawable.emote_angry_3); confirmationLabel.setText("Duża złość"); break;

                    case "emotion_pain_1": confirmationImage.setImageResource(R.drawable.emote_pain_1); confirmationLabel.setText("Lekki ból"); break;
                    case "emotion_pain_2": confirmationImage.setImageResource(R.drawable.emote_pain_2); confirmationLabel.setText("Ból"); break;
                    case "emotion_pain_3": confirmationImage.setImageResource(R.drawable.emote_pain_3); confirmationLabel.setText("Duży ból"); break;

                    case "emotion_tired_1": confirmationImage.setImageResource(R.drawable.emote_tired_1); confirmationLabel.setText("Lekkie zmęczenie"); break;
                    case "emotion_tired_2": confirmationImage.setImageResource(R.drawable.emote_tired_2); confirmationLabel.setText("Zmęczenie"); break;
                    case "emotion_tired_3": confirmationImage.setImageResource(R.drawable.emote_tired_3); confirmationLabel.setText("Duże zmęczenie"); break;

                    default: confirmationImage.setImageResource(R.drawable.tapstrap_icon); confirmationLabel.setText("-"); break;
                }
            }
            else if (bodyPart != null) {
                confirmationLabel.setText(bodyFullAnswer);

                switch (bodyPart) {
                    //head
                    case "pain_forehead": confirmationImage.setImageResource(R.drawable.pain_forehead); break;
                    case "pain_hairback": confirmationImage.setImageResource(R.drawable.pain_hairback); break;
                    case "pain_entirehead": confirmationImage.setImageResource(R.drawable.pain_entirehead); break;
                    case "pain_eyes": confirmationImage.setImageResource(R.drawable.pain_eyes); break;
                    case "pain_nose": confirmationImage.setImageResource(R.drawable.pain_nose); break;
                    case "pain_tooth": confirmationImage.setImageResource(R.drawable.pain_tooth); break;
                    case "pain_ears": confirmationImage.setImageResource(R.drawable.pain_ears); break;
                    case "pain_throat": confirmationImage.setImageResource(R.drawable.pain_throat); break;
                    // hands
                    case "pain_arm": confirmationImage.setImageResource(R.drawable.pain_arm); break;
                    case "pain_elbow": confirmationImage.setImageResource(R.drawable.pain_elbow); break;
                    case "pain_palm": confirmationImage.setImageResource(R.drawable.pain_palm); break;
                    //torso
                    case "pain_shoulders": confirmationImage.setImageResource(R.drawable.pain_shoulders); break;
                    case "pain_chest": confirmationImage.setImageResource(R.drawable.pain_chest); break;
                    case "pain_stomach": confirmationImage.setImageResource(R.drawable.pain_stomach); break;
                    case "pain_back": confirmationImage.setImageResource(R.drawable.pain_back); break;
                    case "pain_lowerback": confirmationImage.setImageResource(R.drawable.pain_lowerback); break;
                    // legs
                    case "pain_thigh": confirmationImage.setImageResource(R.drawable.pain_thigh); break;
                    case "pain_knee": confirmationImage.setImageResource(R.drawable.pain_knee); break;
                    case "pain_calf": confirmationImage.setImageResource(R.drawable.pain_calf); break;
                    case "pain_foot": confirmationImage.setImageResource(R.drawable.pain_foot); break;
                    default: confirmationImage.setImageResource(R.drawable.tapstrap_icon); break;
                }
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    public void confirmationAnswerNo_click(View view) {
        finish();
    }

    public void changeView_Confirm_Final(View view) {
        Intent intent = new Intent(this, FinalActivity.class);
        if (emotion != null) {
            intent.putExtra("EMOTION", emotion);
        }
        else if (bodyPart != null) {
            intent.putExtra("BODY_PART", bodyPart);
            intent.putExtra("FULL_ANSWER", bodyFullAnswer);
        }
        startActivity(intent);
    }
}
