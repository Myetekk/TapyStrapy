package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AskMainSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_main);
    }

    public void changeView_Ask_Address(View view) {
        Intent intent = new Intent(this, AskAddressSelection.class);
        startActivity(intent);
    }
    public void changeView_Ask_Basicneeds(View view) {
        Intent intent = new Intent(this, AskBasicneedsSelection.class);
        startActivity(intent);
    }
    public void changeView_Ask_Persons(View view) {
        Intent intent = new Intent(this, AskPersonsSelection.class);
        startActivity(intent);
    }
    public void changeView_Ask_Localisations(View view) {
        Intent intent = new Intent(this, AskLocalisationsSelection.class);
        startActivity(intent);
    }
    public void changeView_Ask_Items(View view) {
        Intent intent = new Intent(this, AskItemsSelection.class);
        startActivity(intent);
    }
}
