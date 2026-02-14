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

public class AskItemsSelection extends AppCompatActivity {
    String caseText, sentence;
    private TextView questionLabel, mugLabel, dollLabel, bookLabel, crayonsLabel;
    private LinearLayout ask_items_mug, ask_items_doll, ask_items_book, ask_items_crayons;
    private ImageView ask_items_mug_image, ask_items_doll_image, ask_items_book_image, ask_items_crayons_image;
    private int chosenElementId = 0;

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
        mugLabel = findViewById(R.id.mugLabel);
        dollLabel = findViewById(R.id.dollLabel);
        bookLabel = findViewById(R.id.bookLabel);
        crayonsLabel = findViewById(R.id.crayonsLabel);

        ask_items_mug = findViewById(R.id.ask_items_mug);
        ask_items_doll = findViewById(R.id.ask_items_doll);
        ask_items_book = findViewById(R.id.ask_items_book);
        ask_items_crayons = findViewById(R.id.ask_items_crayons);

        ask_items_mug_image = findViewById(R.id.ask_items_mug_image);
        ask_items_doll_image = findViewById(R.id.ask_items_doll_image);
        ask_items_book_image = findViewById(R.id.ask_items_book_image);
        ask_items_crayons_image = findViewById(R.id.ask_items_crayons_image);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_items_mug.performClick(); break;
                case 1: ask_items_doll.performClick(); break;
                case 2: ask_items_book.performClick(); break;
                case 3: ask_items_crayons.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<3) {
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
                case 0: ask_items_mug.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_items_mug_image.setImageResource(R.drawable.ask_items_mug_chosen); break;
                case 1: ask_items_doll.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_items_doll_image.setImageResource(R.drawable.ask_items_doll_chosen); break;
                case 2: ask_items_book.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_items_book_image.setImageResource(R.drawable.ask_items_book_chosen); break;
                case 3: ask_items_crayons.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_items_crayons_image.setImageResource(R.drawable.ask_items_crayons_chosen); break;
            }
        });
    }
    public void unchoseElement() {
        runOnUiThread(() -> {
            ask_items_mug.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_items_doll.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_items_book.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
            ask_items_crayons.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

            ask_items_mug_image.setImageResource(R.drawable.ask_items_mug);
            ask_items_doll_image.setImageResource(R.drawable.ask_items_doll);
            ask_items_book_image.setImageResource(R.drawable.ask_items_book);
            ask_items_crayons_image.setImageResource(R.drawable.ask_items_crayons);
        });
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
    public String formSentence(String tag){
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
        String sentenceTag = (String) view.getTag();
        String formedSentence = formSentence(sentenceTag);

        intent.putExtra("SENTENCE_TAG", sentenceTag);
        intent.putExtra("SENTENCE", formedSentence);
        startActivity(intent);
    }
}
