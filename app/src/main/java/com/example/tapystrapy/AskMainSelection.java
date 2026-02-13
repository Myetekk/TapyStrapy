package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class AskMainSelection extends AppCompatActivity {
    String address, sentence;
    LinearLayout ask_address_layout, ask_basicneeds_layout, ask_persons_layout, ask_localisations_layout, ask_items_layout;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_ask_main);
        initializeUIElements();

        Intent intent = getIntent();
        address = intent.getStringExtra("ADDRESS");
        if (address == null) {
            address = "";
        }
        else if (address.equals("request")) {
            disableLayout(ask_address_layout);
        }
        else if (address.equals("pleasehelp")) {
            disableLayout(ask_address_layout);
            disableLayout(ask_localisations_layout);
            disableLayout(ask_items_layout);
        }
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
        if (AppState.getInstance().get_connectionStatus()) {
            switch (chosenElementId) {
                case 0: if (!ask_address_layout.isEnabled()) chosenElementId++; break;
                case 1: if (!ask_basicneeds_layout.isEnabled()) chosenElementId++; break;
                case 2: if (!ask_persons_layout.isEnabled()) chosenElementId++; break;
                case 3: if (!ask_localisations_layout.isEnabled()) chosenElementId++; break;
                case 4: if (!ask_items_layout.isEnabled()) chosenElementId--; break;
            }
            choseElement();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppState.getInstance().destroyTapSdk();
    }



    public void disableLayout(LinearLayout ll){
        ll.setClickable(false);
        ll.setEnabled(false);
        ll.setAlpha(0.3f);
    }

    public void initializeUIElements(){
        ask_address_layout = findViewById(R.id.ask_address_layout);
        ask_basicneeds_layout = findViewById(R.id.ask_basicneeds_layout);
        ask_persons_layout = findViewById(R.id.ask_persons_layout);
        ask_localisations_layout = findViewById(R.id.ask_localisations_layout);
        ask_items_layout = findViewById(R.id.ask_items_layout);
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_address_layout.performClick(); break;
                case 1: ask_basicneeds_layout.performClick(); break;
                case 2: ask_persons_layout.performClick(); break;
                case 3: ask_localisations_layout.performClick(); break;
                case 4: ask_items_layout.performClick(); break;
            }
        }
        else if (gesture==Gesture.RIGHT  &&  chosenElementId<4) {
            switch (chosenElementId) {
                case 0: if (ask_basicneeds_layout.isEnabled()) chosenElementId++; break;
                case 1: if (ask_persons_layout.isEnabled()) chosenElementId++; break;
                case 2: if (ask_localisations_layout.isEnabled()) chosenElementId++; break;
                case 3: if (ask_items_layout.isEnabled()) chosenElementId++; break;
            }
            choseElement();
        }
        else if (gesture==Gesture.LEFT  &&  chosenElementId>0) {
            switch (chosenElementId) {
                case 1: if (ask_address_layout.isEnabled()) chosenElementId--; break;
                case 2: if (ask_basicneeds_layout.isEnabled()) chosenElementId--; break;
                case 3: if (ask_persons_layout.isEnabled()) chosenElementId--; break;
                case 4: if (ask_localisations_layout.isEnabled()) chosenElementId--; break;
            }
            choseElement();
        }
        else choseElement();
    }
    private void choseElement() {
        unchoseElement();
        switch (chosenElementId) {
            case 0: ask_address_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 1: ask_basicneeds_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 2: ask_persons_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 3: ask_localisations_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
            case 4: ask_items_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); break;
        }
    }
    public void unchoseElement() {
        ask_address_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_basicneeds_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_persons_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_localisations_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_items_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
    }



    public void changeView_Ask_Address(View view) {
        Intent intent = new Intent(this, AskAddressSelection.class);
        startActivity(intent);
    }
    public void changeView_Ask_Basicneeds(View view) {
        Intent intent = new Intent(this, AskBasicneedsSelection.class);
        if (!address.isEmpty()){
            if (address.equals("request")) {
                intent.putExtra("CASE", "CustomRequest");
            }
            else if (address.equals("pleasehelp")) {
                intent.putExtra("CASE", "CustomPleasehelp");
            }
        }
        else {
            intent.putExtra("CASE", "Mianownik");
        }
        intent.putExtra("SENTENCE", sentence);
        startActivity(intent);
    }
    public void changeView_Ask_Persons(View view) {
        Intent intent = new Intent(this, AskPersonsSelection.class);
        if (!address.isEmpty()){
            if (address.equals("request")) {
                intent.putExtra("CASE", "Dopełniacz");
            }
            else if (address.equals("pleasehelp")) {
                intent.putExtra("CASE", "Wołacz");
            }
        }
        else {
            intent.putExtra("CASE", "Mianownik");
        }
        intent.putExtra("SENTENCE", sentence);
        startActivity(intent);
    }
    public void changeView_Ask_Localisations(View view) {
        Intent intent = new Intent(this, AskLocalisationsSelection.class);
        if (!address.isEmpty() && address.equals("request")) {
            intent.putExtra("CASE", "Dopełniacz");
        }
        else {
            intent.putExtra("CASE", "Mianownik");
        }
        intent.putExtra("SENTENCE", sentence);
        startActivity(intent);
    }
    public void changeView_Ask_Items(View view) {
        Intent intent = new Intent(this, AskItemsSelection.class);
        if (!address.isEmpty() && address.equals("request")) {
            intent.putExtra("CASE", "Biernik");
        }
        else {
            intent.putExtra("CASE", "Mianownik");
        }
        intent.putExtra("SENTENCE", sentence);
        startActivity(intent);
    }
}
