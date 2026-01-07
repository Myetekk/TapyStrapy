package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EmotionLevel extends AppCompatActivity {
    String emotion;
    private ImageView emotionLevelImage1, emotionLevelImage2, emotionLevelImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emotion_level);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        setEmotionImages();
    }

    private void initializeUIElements() {
        emotionLevelImage1 = findViewById(R.id.emotionLevelImage1);
        emotionLevelImage2 = findViewById(R.id.emotionLevelImage2);
        emotionLevelImage3 = findViewById(R.id.emotionLevelImage3);
    }

    private void setEmotionImages() {
        try {
            switch (emotion) {
                case "emotion_happy_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_happy_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_happy_2);
                    emotionLevelImage3.setImageResource(R.drawable.emote_happy_3);
                } break;
                case "emotion_sad_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_sad_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_sad_1);
                    emotionLevelImage3.setImageResource(R.drawable.emote_sad_1);
                } break;
                case "emotion_fear_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_feared_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_feared_1);
                    emotionLevelImage3.setImageResource(R.drawable.emote_feared_1);
                } break;
                case "emotion_angry_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_angry_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_angry_1);
                    emotionLevelImage3.setImageResource(R.drawable.emote_angry_1);
                } break;
                case "emotion_pain_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_pain_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_pain_1);
                    emotionLevelImage3.setImageResource(R.drawable.emote_pain_1);
                } break;
                case "emotion_tired_1": {
                    emotionLevelImage1.setImageResource(R.drawable.emote_tired_1);
                    emotionLevelImage2.setImageResource(R.drawable.emote_tired_1);
                    emotionLevelImage3.setImageResource(R.drawable.emote_tired_1);
                } break;
                default: emotionLevelImage1.setImageResource(R.drawable.tapstrap_icon); break;
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    public void changeView_emotionLevel_Confirmation(View view) {
        String emotionLevel = setChoosenEmotionString((String) view.getTag());
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("EMOTION", emotionLevel);
        intent.putExtra("IS_FINAL", "true");
        startActivity(intent);
    }

    private String setChoosenEmotionString(String emotionLevel) {
        String emotionString = emotion.substring(0, emotion.length()-1);

        switch (emotionLevel) {
            case "emotion_level_low": emotionString += "1"; break;
            case "emotion_level_medium": emotionString += "2"; break;
            case "emotion_level_high": emotionString += "3"; break;
        }

        return emotionString;
    }
}
