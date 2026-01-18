package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {
    String emotion;
    private ImageView finalImage;
    private TextView finalLabel;

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
        finalLabel = findViewById(R.id.finalLabel);
    }

    private void setFinalImage() {
        try {
            switch (emotion) {
                case "emotion_happy_1": finalImage.setImageResource(R.drawable.emote_happy_1); finalLabel.setText("Lekka radość"); break;
                case "emotion_happy_2": finalImage.setImageResource(R.drawable.emote_happy_2); finalLabel.setText("Radość"); break;
                case "emotion_happy_3": finalImage.setImageResource(R.drawable.emote_happy_3); finalLabel.setText("Duża radość"); break;

                case "emotion_sad_1": finalImage.setImageResource(R.drawable.emote_sad_1); finalLabel.setText("Lekki smutek"); break;
                case "emotion_sad_2": finalImage.setImageResource(R.drawable.emote_sad_1); finalLabel.setText("Smutek"); break;
                case "emotion_sad_3": finalImage.setImageResource(R.drawable.emote_sad_1); finalLabel.setText("Duży smutek"); break;

                case "emotion_fear_1": finalImage.setImageResource(R.drawable.emote_feared_1); finalLabel.setText("Lekki strach"); break;
                case "emotion_fear_2": finalImage.setImageResource(R.drawable.emote_feared_1); finalLabel.setText("Strach"); break;
                case "emotion_fear_3": finalImage.setImageResource(R.drawable.emote_feared_1); finalLabel.setText("Duży strach"); break;

                case "emotion_angry_1": finalImage.setImageResource(R.drawable.emote_angry_1); finalLabel.setText("Lekka złość"); break;
                case "emotion_angry_2": finalImage.setImageResource(R.drawable.emote_angry_1); finalLabel.setText("Złość"); break;
                case "emotion_angry_3": finalImage.setImageResource(R.drawable.emote_angry_1); finalLabel.setText("Duża złość"); break;

                case "emotion_pain_1": finalImage.setImageResource(R.drawable.emote_pain_1); finalLabel.setText("Lekki ból"); break;
                case "emotion_pain_2": finalImage.setImageResource(R.drawable.emote_pain_1); finalLabel.setText("Ból"); break;
                case "emotion_pain_3": finalImage.setImageResource(R.drawable.emote_pain_1); finalLabel.setText("Duży ból"); break;

                case "emotion_tired_1": finalImage.setImageResource(R.drawable.emote_tired_1); finalLabel.setText("Lekkie zmęczenie"); break;
                case "emotion_tired_2": finalImage.setImageResource(R.drawable.emote_tired_1); finalLabel.setText("Zmęczenie"); break;
                case "emotion_tired_3": finalImage.setImageResource(R.drawable.emote_tired_1); finalLabel.setText("Duże zmęczenie"); break;

                default: finalImage.setImageResource(R.drawable.tapstrap_icon); finalLabel.setText("-"); break;
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
