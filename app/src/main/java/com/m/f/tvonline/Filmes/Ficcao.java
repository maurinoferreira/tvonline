package com.m.f.tvonline.Filmes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.m.f.tvonline.Config;
import com.m.f.tvonline.EscolhaPlayer;
import com.m.f.tvonline.R;

import java.util.ArrayList;
import java.util.List;


public class Ficcao extends AppCompatActivity {

    private ListView listView;
    private List<Filmes> movieList;
    private FilmesAdapter adapter;
    private AdView adView;
    private InterstitialAd interstitialAd;
    int counter = 1;
    RelativeLayout rela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_activity_layout);

        rela = findViewById(R.id.rela);



        initView();
        loadBannerAd();
        loadInterstitialAd();
    }




    private void initView() {
        listView = (ListView) findViewById(R.id.movie_list);
        movieList = new ArrayList<>();

        Filmes movie;
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);
        movie = new Filmes("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        movieList.add(movie);



        adapter = new FilmesAdapter(Ficcao.this, movieList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Ficcao.this, EscolhaPlayer.class);
                intent.putExtra("live_path", movieList.get(position).url_path);
                intent.putExtra("live_name", movieList.get(position).movie_name);
                startActivity(intent);
                overridePendingTransition(R.anim.translate_in, R.anim.translate_out);

                showInterstitialAd();

            }

        });
    }


    private void loadInterstitialAd() {
        if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {
            interstitialAd = new InterstitialAd(getApplicationContext());
            interstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial_unit_id));
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
        } else {
            Log.d("AdMob", "AdMob Interstitial is Disabled");
        }
    }


    private void showInterstitialAd() {
        if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {

            if (interstitialAd != null && interstitialAd.isLoaded()) {

                if (counter == Config.ADMOB_INTERSTITIAL_ADS_INTERVAL) {
                    interstitialAd.show();
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("AdMob", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("AdMob", "AdMob Interstitial is Disabled");
        }
    }


    public void loadBannerAd() {
        if (Config.ENABLE_ADMOB_BANNER_ADS) {
            MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.admob_banner_ad_unit_id));
            adView = (AdView) findViewById(R.id.adView);
            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {
                }

                @Override
                public void onAdFailedToLoad(int error) {

                    adView.setVisibility(View.GONE);
                    rela.setVisibility(View.GONE);
                }

                @Override
                public void onAdLeftApplication() {
                }

                @Override
                public void onAdOpened() {
                }

                @Override
                public void onAdLoaded() {

                    adView.setVisibility(View.VISIBLE);
                    rela.setVisibility(View.VISIBLE);
                }
            });

        } else {
            Log.d("AdMob", "AdMob Banner is Disabled");
        }

    }





    @Override
    public void onBackPressed() {

       super.onBackPressed();

    }



    }





