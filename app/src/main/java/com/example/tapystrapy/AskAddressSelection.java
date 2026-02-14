package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.tapystrapy.model.Gesture;

public class AskAddressSelection extends AppCompatActivity {
    private LinearLayout ask_address_request, ask_address_pleasehelp, ask_address_apologize, ask_address_thank;
    private ImageView ask_address_request_image, ask_address_pleasehelp_image, ask_address_apologize_image, ask_address_thank_image;
    private int chosenElementId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_ask_address);
        initializeUIElements();
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



    private void initializeUIElements() {
        ask_address_request = findViewById(R.id.ask_address_request);
        ask_address_pleasehelp = findViewById(R.id.ask_address_pleasehelp);
        ask_address_apologize = findViewById(R.id.ask_address_apologize);
        ask_address_thank = findViewById(R.id.ask_address_thank);

        ask_address_request_image = findViewById(R.id.ask_address_request_image);
        ask_address_pleasehelp_image = findViewById(R.id.ask_address_pleasehelp_image);
        ask_address_apologize_image = findViewById(R.id.ask_address_apologize_image);
        ask_address_thank_image = findViewById(R.id.ask_address_thank_image);

        chosenElementId = 0;
    }

    public void changeChosenElement(Gesture gesture) {
        if (gesture==Gesture.UP) {
            switch (chosenElementId) {
                case 0: ask_address_request.performClick(); break;
                case 1: ask_address_pleasehelp.performClick(); break;
                case 2: ask_address_apologize.performClick(); break;
                case 3: ask_address_thank.performClick(); break;
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
        unchoseElement();
        switch (chosenElementId) {
            case 0: ask_address_request.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_address_request_image.setImageResource(R.drawable.ask_address_request_chosen); break;
            case 1: ask_address_pleasehelp.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_address_pleasehelp_image.setImageResource(R.drawable.ask_address_pleasehelp_chosen); break;
            case 2: ask_address_apologize.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_address_apologize_image.setImageResource(R.drawable.ask_address_apologize_chosen); break;
            case 3: ask_address_thank.setBackgroundColor(ContextCompat.getColor(this, R.color.chosen_element)); ask_address_thank_image.setImageResource(R.drawable.ask_address_thank_chosen); break;
        }
    }
    public void unchoseElement() {
        ask_address_request.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_address_pleasehelp.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_address_apologize.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));
        ask_address_thank.setBackgroundColor(ContextCompat.getColor(this, R.color.almost_white));

        ask_address_request_image.setImageResource(R.drawable.ask_address_request);
        ask_address_pleasehelp_image.setImageResource(R.drawable.ask_address_pleasehelp);
        ask_address_apologize_image.setImageResource(R.drawable.ask_address_apologize);
        ask_address_thank_image.setImageResource(R.drawable.ask_address_thank);
    }


    public void changeView_Main_Ask(View view) {
        Intent intent = new Intent(this, AskMainSelection.class);
        String tag = (String)  view.getTag();
        intent.putExtra("ADDRESS", tag);
        if (tag.equals("request")) {
            intent.putExtra("SENTENCE", "Chcę ");
        }
        else {
            intent.putExtra("SENTENCE", "Pomóż mi proszę ");
        }
        startActivity(intent);
    }

    public void changeView_Ask_Persons(View view) {
        Intent intent = new Intent(this, AskPersonsSelection.class);
        String tag = (String)  view.getTag();

        if (tag.equals("apologize")) {
            intent.putExtra("CASE", "Wołacz");
            intent.putExtra("SENTENCE", "Przepraszam ");
        }
        else {
            intent.putExtra("CASE", "Wołacz");
            intent.putExtra("SENTENCE", "Dziękuję ");
        }
        startActivity(intent);
    }
}
