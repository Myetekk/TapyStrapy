package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {
    String emotion;
    private ImageView finalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");

        setFinalImage();
    }

    private void initializeUIElements() {
        finalImage = findViewById(R.id.finalImage);
    }

    private void setFinalImage() {
        try {
            switch (emotion) {
                case "emotion_happy_1": finalImage.setImageResource(R.drawable.emote_happy_1); break;
                case "emotion_happy_2": finalImage.setImageResource(R.drawable.emote_happy_2); break;
                case "emotion_happy_3": finalImage.setImageResource(R.drawable.emote_happy_3); break;
                case "emotion_sad_1": finalImage.setImageResource(R.drawable.emote_sad_1); break;
                case "emotion_fear_1": finalImage.setImageResource(R.drawable.emote_feared_1); break;
                case "emotion_angry_1": finalImage.setImageResource(R.drawable.emote_angry_1); break;
                case "emotion_pain_1": finalImage.setImageResource(R.drawable.emote_pain_1); break;
                case "emotion_tired_1": finalImage.setImageResource(R.drawable.emote_tired_1); break;
                default: finalImage.setImageResource(R.drawable.tapstrap_icon); break;
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
