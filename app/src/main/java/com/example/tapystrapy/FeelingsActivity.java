package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class FeelingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppState.getInstance().set_activity(this);

        setContentView(R.layout.activity_feelings);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppState.getInstance().set_activity(this);
    }



    public void changeView_Feelings_EmotionLevel(View view) {
        String emotion = (String) view.getTag();
        Intent intent = new Intent(this, EmotionLevel.class);
        intent.putExtra("EMOTION", emotion);
        startActivity(intent);
    }
}