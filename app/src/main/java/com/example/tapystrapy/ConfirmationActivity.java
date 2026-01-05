package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity  extends AppCompatActivity {
    private ImageView confirmationFeelingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        String emotion = intent.getStringExtra("EMOTION");
        initializeUIElements();
        setEmotion(emotion);
    }

    private void initializeUIElements() {
        confirmationFeelingImage = findViewById(R.id.confirmationFeelingImage);
    }

    private void setEmotion(String emotion) {
        try {
            switch (emotion) {
                case "emotion_happy": confirmationFeelingImage.setImageResource(R.drawable.emote_happy_1); break;
                case "emotion_sad": confirmationFeelingImage.setImageResource(R.drawable.emote_sad_1); break;
                case "emotion_fear": confirmationFeelingImage.setImageResource(R.drawable.emote_feared_1); break;
                case "emotion_angry": confirmationFeelingImage.setImageResource(R.drawable.emote_angry_1); break;
                case "emotion_pain": confirmationFeelingImage.setImageResource(R.drawable.emote_pain_1); break;
                case "emotion_tired": confirmationFeelingImage.setImageResource(R.drawable.emote_tired_1); break;
                default: confirmationFeelingImage.setImageResource(R.drawable.tapstrap_icon); break;
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }
}
