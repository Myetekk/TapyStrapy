package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AskItemsSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_main);
    }

    public void changeView_Pain_Head(View view) {
        Intent intent = new Intent(this, PainHeadSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Hand(View view) {
        Intent intent = new Intent(this, PainHandSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Torso(View view) {
        Intent intent = new Intent(this, PainTorsoSelection.class);
        startActivity(intent);
    }
    public void changeView_Pain_Leg(View view) {
        Intent intent = new Intent(this, PainLegSelection.class);
        startActivity(intent);
    }
}
