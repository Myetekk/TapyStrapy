package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AskAddressSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_ask_address);
    }

    //poprawić to by były tylko 2 funkcje i argumenty z layoutu xml patrz pain torso
    public void changeView_Main_Ask_Request(View view) {
        Intent intent = new Intent(this, AskMainSelection.class);
        intent.putExtra("ADDRESS", "request");
        intent.putExtra("SENTENCE", "Chcę ");
        startActivity(intent);
    }
    public void changeView_Main_Ask_Pleasehelp(View view) {
        Intent intent = new Intent(this, AskMainSelection.class);
        intent.putExtra("ADDRESS", "pleasehelp");
        intent.putExtra("SENTENCE", "Pomóż mi proszę ");
        startActivity(intent);
    }
    public void changeView_Ask_Persons_Apologize(View view) {
        Intent intent = new Intent(this, AskPersonsSelection.class);
        intent.putExtra("CASE", "Wołacz");
        intent.putExtra("SENTENCE", "Przepraszam ");
        startActivity(intent);
    }
    public void changeView_Ask_Persons_Thank(View view) {
        Intent intent = new Intent(this, AskPersonsSelection.class);
        intent.putExtra("CASE", "Wołacz");
        intent.putExtra("SENTENCE", "Dziękuję ");
        startActivity(intent);
    }
}
