package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class AskPersonsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, mumLabel, dadLabel, broLabel, sisLabel, grandmaLabel, grandpaLabel, attendantLabel;
    private LinearLayout ask_persons_mum, ask_persons_dad, ask_persons_brother, ask_persons_sister, ask_persons_grandma, ask_persons_grandpa, ask_persons_attendant;
    private int chosenElementId = 0;

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
        mumLabel = findViewById(R.id.mumLabel);
        dadLabel = findViewById(R.id.dadLabel);
        broLabel = findViewById(R.id.broLabel);
        sisLabel = findViewById(R.id.sisLabel);
        grandmaLabel = findViewById(R.id.grandmaLabel);
        grandpaLabel = findViewById(R.id.grandpaLabel);
        attendantLabel = findViewById(R.id.attendantLabel);

        ask_persons_mum = findViewById(R.id.ask_persons_mum);
        ask_persons_dad = findViewById(R.id.ask_persons_dad);
        ask_persons_brother = findViewById(R.id.ask_persons_brother);
        ask_persons_sister = findViewById(R.id.ask_persons_sister);
        ask_persons_grandma = findViewById(R.id.ask_persons_grandma);
        ask_persons_grandpa = findViewById(R.id.ask_persons_grandpa);
        ask_persons_attendant = findViewById(R.id.ask_persons_attendant);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_persons_mum.performClick(); break;
                case 1: ask_persons_dad.performClick(); break;
                case 2: ask_persons_brother.performClick(); break;
                case 3: ask_persons_sister.performClick(); break;
                case 4: ask_persons_grandma.performClick(); break;
                case 5: ask_persons_grandpa.performClick(); break;
                case 6: ask_persons_attendant.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<6) {
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
            case 0: ask_persons_mum.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: ask_persons_dad.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: ask_persons_brother.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: ask_persons_sister.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 4: ask_persons_grandma.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 5: ask_persons_grandpa.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 6: ask_persons_attendant.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        ask_persons_mum.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_dad.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_brother.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_sister.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_grandma.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_grandpa.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_attendant.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
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
