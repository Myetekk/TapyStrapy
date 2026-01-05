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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_feelings);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void changeViewToConfirmation(View view) {
        try {
            String emotion = (String) view.getTag();
            Intent intent = new Intent(this, ConfirmationActivity.class);
            intent.putExtra("EMOTION", emotion);
            startActivity(intent);
        }
        catch (Exception e) {
            Log.e("TAPPP", "Exception: "+e);
        }
    }
}