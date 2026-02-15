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

public class FinalActivity extends AppCompatActivity {
    String emotion, sentenceTag, sentence, bodyPart, bodyFullAnswer;
    private ImageView finalImage;
    private TextView finalLabel;
    private LinearLayout finalMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_final);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        sentenceTag = intent.getStringExtra("SENTENCE_TAG");
        sentence = intent.getStringExtra("SENTENCE");
        bodyPart = intent.getStringExtra("BODY_PART");
        bodyFullAnswer = intent.getStringExtra("FULL_ANSWER");

        setFinalImage();
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
        finalImage = findViewById(R.id.finalImage);
        finalLabel = findViewById(R.id.finalLabel);

        finalMenu = findViewById(R.id.finalMenu);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            finalMenu.performClick();
        }
        else choseElement();
    }
    private void choseElement() {
        finalMenu.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element));
    }
    public void unchoseElement() {
        finalMenu.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }


    private void setFinalImage() {
        try {
            if (emotion != null) {
                switch (emotion) {
                    case "emotion_happy_1": finalImage.setImageResource(R.drawable.emote_happy_1); finalLabel.setText("Lekka radość"); break;
                    case "emotion_happy_2": finalImage.setImageResource(R.drawable.emote_happy_2); finalLabel.setText("Radość"); break;
                    case "emotion_happy_3": finalImage.setImageResource(R.drawable.emote_happy_3); finalLabel.setText("Duża radość"); break;

                    case "emotion_sad_1": finalImage.setImageResource(R.drawable.emote_sad_1); finalLabel.setText("Lekki smutek"); break;
                    case "emotion_sad_2": finalImage.setImageResource(R.drawable.emote_sad_2); finalLabel.setText("Smutek"); break;
                    case "emotion_sad_3": finalImage.setImageResource(R.drawable.emote_sad_3); finalLabel.setText("Duży smutek"); break;

                    case "emotion_fear_1": finalImage.setImageResource(R.drawable.emote_feared_1); finalLabel.setText("Lekki strach"); break;
                    case "emotion_fear_2": finalImage.setImageResource(R.drawable.emote_feared_2); finalLabel.setText("Strach"); break;
                    case "emotion_fear_3": finalImage.setImageResource(R.drawable.emote_feared_3); finalLabel.setText("Duży strach"); break;

                    case "emotion_angry_1": finalImage.setImageResource(R.drawable.emote_angry_1); finalLabel.setText("Lekka złość"); break;
                    case "emotion_angry_2": finalImage.setImageResource(R.drawable.emote_angry_2); finalLabel.setText("Złość"); break;
                    case "emotion_angry_3": finalImage.setImageResource(R.drawable.emote_angry_3); finalLabel.setText("Duża złość"); break;

                    case "emotion_pain_1": finalImage.setImageResource(R.drawable.emote_pain_1); finalLabel.setText("Lekki ból"); break;
                    case "emotion_pain_2": finalImage.setImageResource(R.drawable.emote_pain_2); finalLabel.setText("Ból"); break;
                    case "emotion_pain_3": finalImage.setImageResource(R.drawable.emote_pain_3); finalLabel.setText("Duży ból"); break;

                    case "emotion_tired_1": finalImage.setImageResource(R.drawable.emote_tired_1); finalLabel.setText("Lekkie zmęczenie"); break;
                    case "emotion_tired_2": finalImage.setImageResource(R.drawable.emote_tired_2); finalLabel.setText("Zmęczenie"); break;
                    case "emotion_tired_3": finalImage.setImageResource(R.drawable.emote_tired_3); finalLabel.setText("Duże zmęczenie"); break;

                    default: finalImage.setImageResource(R.drawable.tapstrap_icon); finalLabel.setText("-"); break;
                }
            }
            else if (sentence != null) {
                finalLabel.setText(sentence);

                switch (sentenceTag) {
                    case "toilet": finalImage.setImageResource(R.drawable.ask_basicneeds_toilet); break;
                    case "drink": finalImage.setImageResource(R.drawable.ask_basicneeds_drink); break;
                    case "eat": finalImage.setImageResource(R.drawable.ask_basicneeds_eat); break;
                    case "play": finalImage.setImageResource(R.drawable.ask_basicneeds_play); break;
                    case "sleep": finalImage.setImageResource(R.drawable.ask_basicneeds_sleep); break;

                    case "mum": finalImage.setImageResource(R.drawable.ask_persons_mum); break;
                    case "dad": finalImage.setImageResource(R.drawable.ask_persons_dad); break;
                    case "bro": finalImage.setImageResource(R.drawable.ask_persons_brother); break;
                    case "sis": finalImage.setImageResource(R.drawable.ask_persons_sister); break;
                    case "grandma": finalImage.setImageResource(R.drawable.ask_persons_grandma); break;
                    case "grandpa": finalImage.setImageResource(R.drawable.ask_persons_grandpa); break;
                    case "attendant": finalImage.setImageResource(R.drawable.ask_persons_attendant); break;

                    case "house": finalImage.setImageResource(R.drawable.ask_localisations_house); break;
                    case "rehab": finalImage.setImageResource(R.drawable.ask_localisations_rehabcenter); break;
                    case "outdoor": finalImage.setImageResource(R.drawable.ask_localisations_outdoor); break;
                    case "shop": finalImage.setImageResource(R.drawable.ask_localisations_shop); break;
                    case "school": finalImage.setImageResource(R.drawable.ask_localisations_school); break;

                    case "mug": finalImage.setImageResource(R.drawable.ask_items_mug); break;
                    case "doll": finalImage.setImageResource(R.drawable.ask_items_doll); break;
                    case "book": finalImage.setImageResource(R.drawable.ask_items_book); break;
                    case "crayons": finalImage.setImageResource(R.drawable.ask_items_crayons); break;
                }
            }
            else if (bodyPart != null) {
                finalLabel.setText(bodyFullAnswer);

                switch (bodyPart) {
                    //head
                    case "pain_forehead": finalImage.setImageResource(R.drawable.pain_forehead); break;
                    case "pain_hairback": finalImage.setImageResource(R.drawable.pain_hairback); break;
                    case "pain_entirehead": finalImage.setImageResource(R.drawable.pain_entirehead); break;
                    case "pain_eyes": finalImage.setImageResource(R.drawable.pain_eyes); break;
                    case "pain_nose": finalImage.setImageResource(R.drawable.pain_nose); break;
                    case "pain_tooth": finalImage.setImageResource(R.drawable.pain_tooth); break;
                    case "pain_ears_l": finalImage.setImageResource(R.drawable.pain_ears_l); break;
                    case "pain_ears_r": finalImage.setImageResource(R.drawable.pain_ears); break;
                    case "pain_throat": finalImage.setImageResource(R.drawable.pain_throat); break;
                    // hands
                    case "pain_arm_l": finalImage.setImageResource(R.drawable.pain_arm_l); break;
                    case "pain_arm_r": finalImage.setImageResource(R.drawable.pain_arm); break;
                    case "pain_elbow_l": finalImage.setImageResource(R.drawable.pain_elbow_l); break;
                    case "pain_elbow_r": finalImage.setImageResource(R.drawable.pain_elbow); break;
                    case "pain_palm_l": finalImage.setImageResource(R.drawable.pain_palm_l); break;
                    case "pain_palm_r": finalImage.setImageResource(R.drawable.pain_palm); break;
                    //torso
                    case "pain_shoulders_l": finalImage.setImageResource(R.drawable.pain_shoulders_l); break;
                    case "pain_shoulders_r": finalImage.setImageResource(R.drawable.pain_shoulders_r); break;
                    case "pain_chest": finalImage.setImageResource(R.drawable.pain_chest); break;
                    case "pain_stomach": finalImage.setImageResource(R.drawable.pain_stomach); break;
                    case "pain_back": finalImage.setImageResource(R.drawable.pain_back); break;
                    case "pain_lowerback": finalImage.setImageResource(R.drawable.pain_lowerback); break;
                    // legs
                    case "pain_thigh_l": finalImage.setImageResource(R.drawable.pain_thigh_l); break;
                    case "pain_thigh_r": finalImage.setImageResource(R.drawable.pain_thigh); break;
                    case "pain_knee_l": finalImage.setImageResource(R.drawable.pain_knee_l); break;
                    case "pain_knee_r": finalImage.setImageResource(R.drawable.pain_knee); break;
                    case "pain_calf_l": finalImage.setImageResource(R.drawable.pain_calf_l); break;
                    case "pain_calf_r": finalImage.setImageResource(R.drawable.pain_calf); break;
                    case "pain_foot_l": finalImage.setImageResource(R.drawable.pain_foot_l); break;
                    case "pain_foot_r": finalImage.setImageResource(R.drawable.pain_foot); break;
                    default: finalImage.setImageResource(R.drawable.tapstrap_icon); break;
                }
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    public void finalGoToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
