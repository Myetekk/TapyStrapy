package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class AskLocalisationsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, houseLabel, rehabLabel, outdoorLabel, shopLabel, schoolLabel;
    private LinearLayout ask_localisations_house, ask_localisations_rehabcenter, ask_localisations_outdoor, ask_localisations_shop, ask_localisations_school;
    private ImageView ask_localisations_house_image, ask_localisations_rehabcenter_image, ask_localisations_outdoor_image, ask_localisations_shop_image, ask_localisations_school_image;
    private int chosenElementId = 0;

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
        houseLabel = findViewById(R.id.houseLabel);
        rehabLabel = findViewById(R.id.rehabLabel);
        outdoorLabel = findViewById(R.id.outdoorLabel);
        shopLabel = findViewById(R.id.shopLabel);
        schoolLabel = findViewById(R.id.schoolLabel);

        ask_localisations_house = findViewById(R.id.ask_localisations_house);
        ask_localisations_rehabcenter = findViewById(R.id.ask_localisations_rehabcenter);
        ask_localisations_outdoor = findViewById(R.id.ask_localisations_outdoor);
        ask_localisations_shop = findViewById(R.id.ask_localisations_shop);
        ask_localisations_school = findViewById(R.id.ask_localisations_school);

        ask_localisations_house_image = findViewById(R.id.ask_localisations_house_image);
        ask_localisations_rehabcenter_image = findViewById(R.id.ask_localisations_rehabcenter_image);
        ask_localisations_outdoor_image = findViewById(R.id.ask_localisations_outdoor_image);
        ask_localisations_shop_image = findViewById(R.id.ask_localisations_shop_image);
        ask_localisations_school_image = findViewById(R.id.ask_localisations_school_image);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_localisations_house.performClick(); break;
                case 1: ask_localisations_rehabcenter.performClick(); break;
                case 2: ask_localisations_outdoor.performClick(); break;
                case 3: ask_localisations_shop.performClick(); break;
                case 4: ask_localisations_school.performClick(); break;
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
        runOnUiThread(() -> {
            unchoseElement();
            switch (chosenElementId) {
                case 0: ask_localisations_house.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_localisations_house_image.setImageResource(R.drawable.ask_localisations_house_chosen); break;
                case 1: ask_localisations_rehabcenter.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_localisations_rehabcenter_image.setImageResource(R.drawable.ask_localisations_rehabcenter_chosen); break;
                case 2: ask_localisations_outdoor.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_localisations_outdoor_image.setImageResource(R.drawable.ask_localisations_outdoor_chosen); break;
                case 3: ask_localisations_shop.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_localisations_shop_image.setImageResource(R.drawable.ask_localisations_shop_chosen); break;
                case 4: ask_localisations_school.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_localisations_school_image.setImageResource(R.drawable.ask_localisations_school_chosen); break;
            }
        });
    }
    public void unchoseElement() {
        runOnUiThread(() -> {
            ask_localisations_house.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_localisations_rehabcenter.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_localisations_outdoor.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_localisations_shop.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_localisations_school.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

            ask_localisations_house_image.setImageResource(R.drawable.ask_localisations_house);
            ask_localisations_rehabcenter_image.setImageResource(R.drawable.ask_localisations_rehabcenter);
            ask_localisations_outdoor_image.setImageResource(R.drawable.ask_localisations_outdoor);
            ask_localisations_shop_image.setImageResource(R.drawable.ask_localisations_shop);
            ask_localisations_school_image.setImageResource(R.drawable.ask_localisations_school);
        });
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
    public String formSentence(String tag){
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
        String sentenceTag = (String) view.getTag();
        String formedSentence = formSentence(sentenceTag);

        intent.putExtra("SENTENCE_TAG", sentenceTag);
        intent.putExtra("SENTENCE", formedSentence);
        startActivity(intent);
    }
}
