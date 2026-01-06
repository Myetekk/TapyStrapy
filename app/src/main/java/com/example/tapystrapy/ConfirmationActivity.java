package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity  extends AppCompatActivity {
    private ImageView confirmationFeelingImage;
    String emotion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirmation);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        setEmotionImage();
    }

    private void initializeUIElements() {
        confirmationFeelingImage = findViewById(R.id.confirmationFeelingImage);
    }

    private void setEmotionImage() {
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

    public void changeView_Confirm_EmotionLevel(View view) {
        Intent intent = new Intent(this, EmotionLevel.class);
        startActivity(intent);
    }

    public void changeView_Confirm_Feelings(View view) {
        finish();
    }
}
