package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
    }


    public void changeView_Main_Feelings(View view) {
        Intent intent = new Intent(this, FeelingsActivity.class);
        startActivity(intent);
    }
    public void changeView_Main_Ask(View view) {
        Intent intent = new Intent(this, AskMainSelection.class);
        startActivity(intent);
    }
    public void changeView_Main_Pain(View view) {
        Intent intent = new Intent(this, PainMainSelection.class);
        startActivity(intent);
    }
    public void changeView_Main_Debug(View view) {
        Intent intent = new Intent(this, DebugActivity.class);
        startActivity(intent);
    }
}
