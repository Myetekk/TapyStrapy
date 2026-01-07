package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity  extends AppCompatActivity {
    String emotion, isFinal;
    private ImageView confirmationImage;

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
    }

    private void setConfirmationImage() {
        try {
            switch (emotion) {
                case "emotion_happy_1": confirmationImage.setImageResource(R.drawable.emote_happy_1); break;
                case "emotion_happy_2": confirmationImage.setImageResource(R.drawable.emote_happy_2); break;
                case "emotion_happy_3": confirmationImage.setImageResource(R.drawable.emote_happy_3); break;
                case "emotion_sad_1": confirmationImage.setImageResource(R.drawable.emote_sad_1); break;
                case "emotion_fear_1": confirmationImage.setImageResource(R.drawable.emote_feared_1); break;
                case "emotion_angry_1": confirmationImage.setImageResource(R.drawable.emote_angry_1); break;
                case "emotion_pain_1": confirmationImage.setImageResource(R.drawable.emote_pain_1); break;
                case "emotion_tired_1": confirmationImage.setImageResource(R.drawable.emote_tired_1); break;
                default: confirmationImage.setImageResource(R.drawable.tapstrap_icon); break;
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
