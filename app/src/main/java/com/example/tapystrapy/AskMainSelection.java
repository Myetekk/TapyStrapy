package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AskMainSelection extends AppCompatActivity {

    String address, sentence;
    LinearLayout addressLayout, localisationsLayout, itemsLayout;
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
            disableLayout(addressLayout);
        }
        else if (address.equals("pleasehelp")) {
            disableLayout(addressLayout);
            disableLayout(localisationsLayout);
            disableLayout(itemsLayout);
        }
        sentence = intent.getStringExtra("SENTENCE");
        if (sentence == null) {
            sentence = "";
        }
    }

    public void disableLayout(LinearLayout ll){
        ll.setClickable(false);
        ll.setEnabled(false);
        ll.setAlpha(0.3f);
    }

    public void initializeUIElements(){
        addressLayout = findViewById(R.id.address_layout);
        localisationsLayout = findViewById(R.id.localisations_layout);
        itemsLayout = findViewById(R.id.items_layout);
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
