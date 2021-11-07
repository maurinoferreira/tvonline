package com.m.f.tvonline;

/**
 * Created by mauri on 05/11/2018.
 */


import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;


import com.hengyi.fastvideoplayer.library.FastVideoPlayer;

public class FastPlayer extends AppCompatActivity {
    private FastVideoPlayer videoPlayer;
    String LiveName;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fastplayer);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LiveName = getIntent().getStringExtra("Nome");
        url = getIntent().getStringExtra("path");

        videoPlayer = findViewById(R.id.fastvideo_player);

        videoPlayer.setLive(true);
        videoPlayer.setScaleType(FastVideoPlayer.SCALETYPE_FITXY);
        videoPlayer.setTitle(LiveName);
        videoPlayer.setUrl(url);

        //封面图加载
       // Glide.with(this).load("https://bot.tmall.com/guide/img/guide1-bg760.png").into(videoPlayer.getCoverImage());


        videoPlayer.play();


//        videoPlayer.setScreenListener(new FastVideoPlayerScreenListener() {
//            @Override
//            public void onFullScreen() {
//                Toast.makeText(MainActivity.this,"进入全屏",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSmallScreen() {
//                Toast.makeText(MainActivity.this,"进入小屏",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (videoPlayer != null) {
            videoPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoPlayer != null) {
            videoPlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null) {
            videoPlayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoPlayer != null) {
            videoPlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
       // if (videoPlayer != null && videoPlayer.onBackPressed()) {
         //   return;
        //}
        super.onBackPressed();
    }
}
