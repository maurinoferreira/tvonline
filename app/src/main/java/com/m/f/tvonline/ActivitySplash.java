package com.m.f.tvonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.m.f.tvonline.Live.LiveActivity;



public class ActivitySplash extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new CountDownTimer(com.m.f.tvonline.Config.SPLASH_TIME, 1000) {

            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }
}
