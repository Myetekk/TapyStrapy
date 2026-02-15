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
import com.example.tapystrapy.model.Gesture;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ConfirmationActivity  extends AppCompatActivity {
    String emotion, sentenceTag, sentence, bodyPart, bodyFullAnswer;
    private ImageView confirmationImage;
    private TextView confirmationLabel;
    private LinearLayout confirmationAnswerYes, confirmationAnswerMedium, confirmationAnswerNo, confirmationAnswers;
    private int chosenElementId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_confirmation);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        sentenceTag = intent.getStringExtra("SENTENCE_TAG");
        sentence = intent.getStringExtra("SENTENCE");
        bodyPart = intent.getStringExtra("BODY_PART");
        bodyFullAnswer = intent.getStringExtra("FULL_ANSWER");

        setConfirmationImage();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
        AppState.getInstance().initializeTapSdk();

        unchoseElement();
        chosenElementId = 1;
        if (AppState.getInstance().get_connectionStatus()) {
            choseElement();
            showCenterElement();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppState.getInstance().destroyTapSdk();
    }



    private void initializeUIElements() {
        confirmationImage = findViewById(R.id.confirmationImage);
        confirmationLabel = findViewById(R.id.confirmationLabel);

        confirmationAnswerYes = findViewById(R.id.confirmationAnswerYes);
        confirmationAnswerMedium = findViewById(R.id.confirmationAnswerMedium);
        confirmationAnswerNo = findViewById(R.id.confirmationAnswerNo);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: confirmationAnswerYes.performClick(); break;
                case 2: confirmationAnswerNo.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<2) {
            chosenElementId = 2;
            choseElement();
        }
        else if (gesture==Gesture.LEFT  &&  chosenElementId>0) {
            chosenElementId = 0;
            choseElement();
        }
        else choseElement();
    }
    private void choseElement() {
        unchoseElement();
        switch (chosenElementId) {
            case 0: confirmationAnswerYes.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: confirmationAnswerMedium.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: confirmationAnswerNo.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        confirmationAnswerYes.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        confirmationAnswerMedium.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        confirmationAnswerNo.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }

    public void showCenterElement() {
        runOnUiThread(() -> {
            confirmationAnswerMedium.setVisibility(View.VISIBLE);
        });
        chosenElementId = 1;
    }
    public void hideCenterElement() {
        runOnUiThread(() -> {
            confirmationAnswerMedium.setVisibility(View.GONE);
        });
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
            else if (sentence != null) {
                confirmationLabel.setText(sentence);

                switch (sentenceTag) {
                    case "toilet": confirmationImage.setImageResource(R.drawable.ask_basicneeds_toilet); break;
                    case "drink": confirmationImage.setImageResource(R.drawable.ask_basicneeds_drink); break;
                    case "eat": confirmationImage.setImageResource(R.drawable.ask_basicneeds_eat); break;
                    case "play": confirmationImage.setImageResource(R.drawable.ask_basicneeds_play); break;
                    case "sleep": confirmationImage.setImageResource(R.drawable.ask_basicneeds_sleep); break;

                    case "mum": confirmationImage.setImageResource(R.drawable.ask_persons_mum); break;
                    case "dad": confirmationImage.setImageResource(R.drawable.ask_persons_dad); break;
                    case "bro": confirmationImage.setImageResource(R.drawable.ask_persons_brother); break;
                    case "sis": confirmationImage.setImageResource(R.drawable.ask_persons_sister); break;
                    case "grandma": confirmationImage.setImageResource(R.drawable.ask_persons_grandma); break;
                    case "grandpa": confirmationImage.setImageResource(R.drawable.ask_persons_grandpa); break;
                    case "attendant": confirmationImage.setImageResource(R.drawable.ask_persons_attendant); break;

                    case "house": confirmationImage.setImageResource(R.drawable.ask_localisations_house); break;
                    case "rehab": confirmationImage.setImageResource(R.drawable.ask_localisations_rehabcenter); break;
                    case "outdoor": confirmationImage.setImageResource(R.drawable.ask_localisations_outdoor); break;
                    case "shop": confirmationImage.setImageResource(R.drawable.ask_localisations_shop); break;
                    case "school": confirmationImage.setImageResource(R.drawable.ask_localisations_school); break;

                    case "mug": confirmationImage.setImageResource(R.drawable.ask_items_mug); break;
                    case "doll": confirmationImage.setImageResource(R.drawable.ask_items_doll); break;
                    case "book": confirmationImage.setImageResource(R.drawable.ask_items_book); break;
                    case "crayons": confirmationImage.setImageResource(R.drawable.ask_items_crayons); break;
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
                    case "pain_ears_l": confirmationImage.setImageResource(R.drawable.pain_ears_l); break;
                    case "pain_ears_r": confirmationImage.setImageResource(R.drawable.pain_ears); break;
                    case "pain_throat": confirmationImage.setImageResource(R.drawable.pain_throat); break;
                    // hands
                    case "pain_arm_l": confirmationImage.setImageResource(R.drawable.pain_arm_l); break;
                    case "pain_arm_r": confirmationImage.setImageResource(R.drawable.pain_arm); break;
                    case "pain_elbow_l": confirmationImage.setImageResource(R.drawable.pain_elbow_l); break;
                    case "pain_elbow_r": confirmationImage.setImageResource(R.drawable.pain_elbow); break;
                    case "pain_palm_l": confirmationImage.setImageResource(R.drawable.pain_palm_l); break;
                    case "pain_palm_r": confirmationImage.setImageResource(R.drawable.pain_palm); break;
                    //torso
                    case "pain_shoulders_l": confirmationImage.setImageResource(R.drawable.pain_shoulders_l); break;
                    case "pain_shoulders_r": confirmationImage.setImageResource(R.drawable.pain_shoulders_r); break;
                    case "pain_chest": confirmationImage.setImageResource(R.drawable.pain_chest); break;
                    case "pain_stomach": confirmationImage.setImageResource(R.drawable.pain_stomach); break;
                    case "pain_back": confirmationImage.setImageResource(R.drawable.pain_back); break;
                    case "pain_lowerback": confirmationImage.setImageResource(R.drawable.pain_lowerback); break;
                    // legs
                    case "pain_thigh_l": confirmationImage.setImageResource(R.drawable.pain_thigh_l); break;
                    case "pain_thigh_r": confirmationImage.setImageResource(R.drawable.pain_thigh); break;
                    case "pain_knee_l": confirmationImage.setImageResource(R.drawable.pain_knee_l); break;
                    case "pain_knee_r": confirmationImage.setImageResource(R.drawable.pain_knee); break;
                    case "pain_calf_l": confirmationImage.setImageResource(R.drawable.pain_calf_l); break;
                    case "pain_calf_r": confirmationImage.setImageResource(R.drawable.pain_calf); break;
                    case "pain_foot_l": confirmationImage.setImageResource(R.drawable.pain_foot_l); break;
                    case "pain_foot_r": confirmationImage.setImageResource(R.drawable.pain_foot); break;
                    default: confirmationImage.setImageResource(R.drawable.tapstrap_icon); break;
                }
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    public void confirmationAnswerNo_click(View view) {
        if (emotion != null) {
            Intent intent = new Intent(this, FeelingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (bodyPart != null) {
            Intent intent = new Intent(this, PainMainSelection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (sentence != null) {
            Intent intent = new Intent(this, AskMainSelection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else { finish(); }
    }

    public void changeView_Confirm_Final(View view) {
        Intent intent = new Intent(this, FinalActivity.class);
        if (emotion != null) {
            intent.putExtra("EMOTION", emotion);
        }
        else if (sentence != null) {
            intent.putExtra("SENTENCE_TAG", sentenceTag);
            intent.putExtra("SENTENCE", sentence);
        }
        else if (bodyPart != null) {
            intent.putExtra("BODY_PART", bodyPart);
            intent.putExtra("FULL_ANSWER", bodyFullAnswer);
        }
        startActivity(intent);
    }
}
