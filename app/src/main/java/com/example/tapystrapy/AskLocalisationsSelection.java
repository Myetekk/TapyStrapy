package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AskLocalisationsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, houseLabel, rehabLabel, outdoorLabel, shopLabel, schoolLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_localisations);
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
        houseLabel = findViewById(R.id.houseLabel);
        rehabLabel = findViewById(R.id.rehabLabel);
        outdoorLabel = findViewById(R.id.outdoorLabel);
        shopLabel = findViewById(R.id.shopLabel);
        schoolLabel = findViewById(R.id.schoolLabel);
    }

    private void setCases(String caseText){
        if (caseText.equals("Mianownik")){
            questionLabel.setText("Gdzie?");
            houseLabel.setText("Dom");
            rehabLabel.setText("Ośrodek");
            outdoorLabel.setText("Zewnątrz");
            shopLabel.setText("Sklep");
            schoolLabel.setText("Przedszkole/szkołą");
        }
        else {
            questionLabel.setText("Dokąd?");
            houseLabel.setText("do domu");
            rehabLabel.setText("do ośrodka");
            outdoorLabel.setText("na zewnątrz");
            shopLabel.setText("do sklepu");
            schoolLabel.setText("do przedszkola/szkoły");
        }
    }
    public String formSentence(View view){
        String tag = (String) view.getTag();
        String formedSentence = "";
        switch (tag){
            case "house": formedSentence = sentence + houseLabel.getText().toString(); break;
            case "rehab": formedSentence = sentence + rehabLabel.getText().toString(); break;
            case "outdoor": formedSentence = sentence + outdoorLabel.getText().toString(); break;
            case "shop": formedSentence = sentence + shopLabel.getText().toString(); break;
            case "school": formedSentence = sentence + schoolLabel.getText().toString(); break;
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
