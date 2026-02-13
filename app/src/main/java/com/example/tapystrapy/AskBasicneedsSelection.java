package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class AskBasicneedsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, toiletLabel, drinkLabel, eatLabel, playLabel, sleepLabel;
    private LinearLayout ask_basicneeds_toilet, ask_basicneeds_drink, ask_basicneeds_eat, ask_basicneeds_play, ask_basicneeds_sleep;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_ask_basicneeds);
        initializeUIElements();

        Intent intent = getIntent();
        caseText = intent.getStringExtra("CASE");
        setCases(caseText);

        sentence = intent.getStringExtra("SENTENCE");
        if (sentence == null) {
            sentence = "";
        }
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



    private void initializeUIElements(){
        questionLabel = findViewById(R.id.questionLabel);
        toiletLabel = findViewById(R.id.toiletLabel);
        drinkLabel = findViewById(R.id.drinkLabel);
        eatLabel = findViewById(R.id.eatLabel);
        playLabel = findViewById(R.id.playLabel);
        sleepLabel = findViewById(R.id.sleepLabel);

        ask_basicneeds_toilet = findViewById(R.id.ask_basicneeds_toilet);
        ask_basicneeds_drink = findViewById(R.id.ask_basicneeds_drink);
        ask_basicneeds_eat = findViewById(R.id.ask_basicneeds_eat);
        ask_basicneeds_play = findViewById(R.id.ask_basicneeds_play);
        ask_basicneeds_sleep = findViewById(R.id.ask_basicneeds_sleep);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_basicneeds_toilet.performClick(); break;
                case 1: ask_basicneeds_drink.performClick(); break;
                case 2: ask_basicneeds_eat.performClick(); break;
                case 3: ask_basicneeds_play.performClick(); break;
                case 4: ask_basicneeds_sleep.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<4) {
            chosenElementId++;
            choseElement();
        }
        else if (gesture==Gesture.LEFT  &&  chosenElementId>0) {
            chosenElementId--;
            choseElement();
        }
        else choseElement();
    }
    private void choseElement() {
        unchoseElement();
        switch (chosenElementId) {
            case 0: ask_basicneeds_toilet.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: ask_basicneeds_drink.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: ask_basicneeds_eat.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: ask_basicneeds_play.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 4: ask_basicneeds_sleep.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        ask_basicneeds_toilet.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_basicneeds_drink.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_basicneeds_eat.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_basicneeds_play.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_basicneeds_sleep.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    private void setCases(String caseText){
        if (caseText.equals("Mianownik")){
            questionLabel.setText("Kto?");
            toiletLabel.setText("Toaleta");
            drinkLabel.setText("Pić");
            eatLabel.setText("Jeść");
            playLabel.setText("Bawić się");
            sleepLabel.setText("Spać");
        }
        else if (caseText.equals("CustomRequest")){
            questionLabel.setText("Co?");
            toiletLabel.setText("do toalety");
            drinkLabel.setText("się napić");
            eatLabel.setText("coś zjeść");
            playLabel.setText("się bawić");
            sleepLabel.setText("mi się spać");
        }
        else {
            questionLabel.setText("Z czym?");
            toiletLabel.setText("w toalecie");
            drinkLabel.setText("pić");
            eatLabel.setText("jeść");
            playLabel.setText("bawić się");
            sleepLabel.setText("zasnąć");
        }
    }

    public String formSentence(View view){
        String tag = (String) view.getTag();
        String formedSentence = "";
        switch (tag){
            case "toilet": formedSentence = sentence + toiletLabel.getText().toString(); break;
            case "drink": formedSentence = sentence + drinkLabel.getText().toString(); break;
            case "eat": formedSentence = sentence + eatLabel.getText().toString(); break;
            case "play": formedSentence = sentence + playLabel.getText().toString(); break;
            case "sleep": formedSentence = sentence + sleepLabel.getText().toString(); break;
        }
        return formedSentence;
    }

    public void changeView_Confirmation(View view){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        String formedSentence = formSentence(view);
        intent.putExtra("SENTENCE", formedSentence);
        startActivity(intent);
    }
}
