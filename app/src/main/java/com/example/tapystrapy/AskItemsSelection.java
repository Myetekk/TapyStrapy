package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AskItemsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, mugLabel, dollLabel, bookLabel, crayonsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_items);
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
        mugLabel = findViewById(R.id.mugLabel);
        dollLabel = findViewById(R.id.dollLabel);
        bookLabel = findViewById(R.id.bookLabel);
        crayonsLabel = findViewById(R.id.crayonsLabel);
    }

    private void setCases(String caseText){
        if (caseText.equals("Mianownik")){
            questionLabel.setText("Co?");
            mugLabel.setText("Kubek");
            dollLabel.setText("Zabawka");
            bookLabel.setText("Książka");
            crayonsLabel.setText("Kredki");
        }
        else {
            questionLabel.setText("Co?");
            mugLabel.setText("kubek");
            dollLabel.setText("zabawkę");
            bookLabel.setText("książkę");
            crayonsLabel.setText("kredki");
        }
    }
    public String formSentence(View view){
        String tag = (String) view.getTag();
        String formedSentence = "";
        switch (tag){
            case "mug": formedSentence = sentence + mugLabel.getText().toString(); break;
            case "doll": formedSentence = sentence + dollLabel.getText().toString(); break;
            case "book": formedSentence = sentence + bookLabel.getText().toString(); break;
            case "crayons": formedSentence = sentence + crayonsLabel.getText().toString(); break;
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
