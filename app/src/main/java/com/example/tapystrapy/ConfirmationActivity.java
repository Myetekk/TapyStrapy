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
    String emotion, isFinal;
    private ImageView confirmationImage;
    private TextView confirmationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirmation);
        initializeUIElements();

        Intent intent = getIntent();
        emotion = intent.getStringExtra("EMOTION");
        isFinal = intent.getStringExtra("IS_FINAL");

        setConfirmationImage();
    }

    private void initializeUIElements() {
        confirmationImage = findViewById(R.id.confirmationImage);
        confirmationLabel = findViewById(R.id.confirmationLabel);
    }

    private void setConfirmationImage() {
        try {
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

                case "emotion_pain_1": confirmationImage.setImageResource(R.drawable.emote_pain_2); confirmationLabel.setText("Lekki ból"); break;
                case "emotion_pain_2": confirmationImage.setImageResource(R.drawable.emote_pain_2); confirmationLabel.setText("Ból"); break;
                case "emotion_pain_3": confirmationImage.setImageResource(R.drawable.emote_pain_2); confirmationLabel.setText("Duży ból"); break;

                case "emotion_tired_1": confirmationImage.setImageResource(R.drawable.emote_tired_1); confirmationLabel.setText("Lekkie zmęczenie"); break;
                case "emotion_tired_2": confirmationImage.setImageResource(R.drawable.emote_tired_2); confirmationLabel.setText("Zmęczenie"); break;
                case "emotion_tired_3": confirmationImage.setImageResource(R.drawable.emote_tired_3); confirmationLabel.setText("Duże zmęczenie"); break;

                default: confirmationImage.setImageResource(R.drawable.tapstrap_icon); confirmationLabel.setText("-"); break;
            }
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }

    public void confirmationAnswerYes_click(View view) {
        if (isFinal==null) changeView_Confirm_EmotionLevel(view);
        else changeView_Confirm_Final();
    }

    public void confirmationAnswerNo_click(View view) {
        finish();
    }

    public void changeView_Confirm_EmotionLevel(View view) {
        Intent intent = new Intent(this, EmotionLevel.class);
        intent.putExtra("EMOTION", emotion);
        startActivity(intent);
    }

    public void changeView_Confirm_Final() {
        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("EMOTION", emotion);
        startActivity(intent);
    }
}
