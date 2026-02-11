package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AskBasicneedsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, toiletLabel, drinkLabel, eatLabel, playLabel, sleepLabel;

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

    private void initializeUIElements(){
        questionLabel = findViewById(R.id.questionLabel);
        toiletLabel = findViewById(R.id.toiletLabel);
        drinkLabel = findViewById(R.id.drinkLabel);
        eatLabel = findViewById(R.id.eatLabel);
        playLabel = findViewById(R.id.playLabel);
        sleepLabel = findViewById(R.id.sleepLabel);
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
