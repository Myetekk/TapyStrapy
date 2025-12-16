package com.example.tapystrapy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class FeelingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_feelings);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void changeViewToMain(View view) {
        finish();
    }

    public void chooseHappy(View view) {
        Log.d("TAPPP", "chooseHappy");
    }
    public void chooseSad(View view) {
        Log.d("TAPPP", "chooseSad");
    }
    public void chooseFeared(View view) {
        Log.d("TAPPP", "chooseFeared");
    }
    public void chooseAngry(View view) {
        Log.d("TAPPP", "chooseAngry");
    }
    public void choosePain(View view) {
        Log.d("TAPPP", "choosePain");
    }
    public void chooseTired(View view) {
        Log.d("TAPPP", "chooseTired");
    }
}