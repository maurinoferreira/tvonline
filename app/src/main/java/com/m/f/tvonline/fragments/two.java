package com.m.f.tvonline.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.m.f.tvonline.Config;
import com.m.f.tvonline.Filmes.Acao;
import com.m.f.tvonline.Filmes.Animacao;
import com.m.f.tvonline.Filmes.Aventura;
import com.m.f.tvonline.Filmes.Comedia;
import com.m.f.tvonline.Filmes.Drama;
import com.m.f.tvonline.Filmes.Ficcao;
import com.m.f.tvonline.Filmes.Romance;
import com.m.f.tvonline.Filmes.Terror;
import com.m.f.tvonline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class two extends Fragment {

    private AdView adView;
    private InterstitialAd interstitialAd;
    int counter = 1;
    String[] values=new String[]{"Acao", "Animacao","Aventura", "Comédia","Drama","Ficção", "Romance","Terror"};
    ListView lv;
    RelativeLayout rela;

    public two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        perform(v);
        loadBannerAd(v);
        loadInterstitialAd();
        rela = v.findViewById(R.id.rela);

        return v;


    }

    private void perform(View v) {
        lv = (ListView)v.findViewById(R.id.filmes);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,values);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    Intent i = new Intent(getActivity(), Acao.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==1){
                    Intent i = new Intent(getActivity(), Animacao.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==2){
                    Intent i = new Intent(getActivity(), Aventura.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==3){
                    Intent i = new Intent(getActivity(), Comedia.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==4){
                    Intent i = new Intent(getActivity(), Drama.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==5){
                    Intent i = new Intent(getActivity(), Ficcao.class);
                    startActivity(i);
                    showInterstitialAd();
                }
               else if(position ==6){
                    Intent i = new Intent(getActivity(), Romance.class);
                    startActivity(i);
                    showInterstitialAd();
                }

                else if(position ==7){
                    Intent i = new Intent(getActivity(), Terror.class);
                    startActivity(i);
                    showInterstitialAd();
                }
            }
        });

    }
    private void loadInterstitialAd() {
        if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {
            interstitialAd = new InterstitialAd(getContext());
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


    public void loadBannerAd(View v) {
        if (Config.ENABLE_ADMOB_BANNER_ADS) {
            MobileAds.initialize(getContext(), getResources().getString(R.string.admob_banner_ad_unit_id));
            adView = (AdView) v.findViewById(R.id.adView);
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


}

