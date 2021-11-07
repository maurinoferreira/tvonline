package com.m.f.tvonline.Live;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.WindowManager;

import com.m.f.tvonline.R;






import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;



public class LivePlayerActivity extends AppCompatActivity implements OnPreparedListener{

    private VideoView videoView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.live_player_layout);

        url = getIntent().getStringExtra("path");

        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);

        Uri videoUri = Uri.parse(url);
        videoView.setVideoURI(videoUri);

    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        videoView.start();
    }

    public void onBackPressed() {
        super.onBackPressed();
        videoView.stopPlayback();

    }





}