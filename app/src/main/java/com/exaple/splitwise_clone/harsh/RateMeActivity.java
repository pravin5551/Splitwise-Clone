package com.exaple.splitwise_clone.harsh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.exaple.splitwise_clone.R;

public class RateMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_me);


    }

    public void rateMe(View v) {

        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://detail?.id=" + "com.android.chrome")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }
}