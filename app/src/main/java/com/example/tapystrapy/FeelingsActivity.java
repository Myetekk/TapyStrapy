package com.example.tapystrapy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class FeelingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);
    }



    public void changeView_Feelings_Confirmation(View view) {
        String emotion = (String) view.getTag();
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("EMOTION", emotion);
        startActivity(intent);
    }
}