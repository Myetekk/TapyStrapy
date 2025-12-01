package com.example.tapystrapy;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_settings);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void changeViewToMain(View view) {
        finish();
    }
}
