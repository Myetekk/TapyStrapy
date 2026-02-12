package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AskPersonsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, mumLabel, dadLabel, broLabel, sisLabel, grandmaLabel, grandpaLabel, attendantLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_persons);
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
        mumLabel = findViewById(R.id.mumLabel);
        dadLabel = findViewById(R.id.dadLabel);
        broLabel = findViewById(R.id.broLabel);
        sisLabel = findViewById(R.id.sisLabel);
        grandmaLabel = findViewById(R.id.grandmaLabel);
        grandpaLabel = findViewById(R.id.grandpaLabel);
        attendantLabel = findViewById(R.id.attendantLabel);
    }

    private void setCases(String caseText){
        if (caseText.equals("Mianownik")){
            questionLabel.setText("Kto?");
            mumLabel.setText("Mama");
            dadLabel.setText("Tata");
            broLabel.setText("Brat");
            sisLabel.setText("Siostra");
            grandmaLabel.setText("Babcia");
            grandpaLabel.setText("Dziadek");
            attendantLabel.setText("Opiekun");
        }
        else if (caseText.equals("Dopełniacz")){
            questionLabel.setText("Kogo?");
            mumLabel.setText("do mamy");
            dadLabel.setText("do taty");
            broLabel.setText("do brata");
            sisLabel.setText("do siostry");
            grandmaLabel.setText("do babci");
            grandpaLabel.setText("do dziadka");
            attendantLabel.setText("do opiekuna");
        }
        else {
            questionLabel.setText("Kogo?/Komu?");
            mumLabel.setText("mamo");
            dadLabel.setText("tato");
            broLabel.setText("bracie");
            sisLabel.setText("siostro");
            grandmaLabel.setText("babciu");
            grandpaLabel.setText("dziadku");
            attendantLabel.setText("opiekunie");
        }
    }
    public String formSentence(View view){
        String tag = (String) view.getTag();
        String formedSentence = "";
        switch (tag){
            case "mum": formedSentence = sentence + mumLabel.getText().toString(); break;
            case "dad": formedSentence = sentence + dadLabel.getText().toString(); break;
            case "bro": formedSentence = sentence + broLabel.getText().toString(); break;
            case "sis": formedSentence = sentence + sisLabel.getText().toString(); break;
            case "grandma": formedSentence = sentence + grandmaLabel.getText().toString(); break;
            case "grandpa": formedSentence = sentence + grandpaLabel.getText().toString(); break;
            case "attendant": formedSentence = sentence + attendantLabel.getText().toString(); break;
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
