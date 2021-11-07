package com.m.f.tvonline;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.m.f.tvonline.Live.Live;
import com.m.f.tvonline.Live.LivePlayerActivity;

import java.util.List;

import io.presage.finder.model.App;

public class EscolhaPlayer extends AppCompatActivity  {

    private Button player1, player2, player3, player4;
    private TextView canal;
    String path;
    String Nome;
  //   private AdView adView;
  //  private InterstitialAd interstitialAd;
    int counter = 1;
    SharedPref sharedpref;


    @Override
    protected void onResume() {
        super.onResume();

        Appodeal.onResume(this,Appodeal.BANNER);
        Log.d("Appodeal","BannerOnResume");
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedpref = new SharedPref(this);

        if (sharedpref.loadNightModeState()== true ) {

            setTheme(R.style.darktheme);

        } else
            setTheme(R.style.AppTheme);

        setContentView(R.layout.escolha_player);


        canal = findViewById(R.id.canal);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        player3 = findViewById(R.id.player3);
        player4 = findViewById(R.id.playerext);


        if (sharedpref.loadNightModeState()==true) {

            player1.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));
            player2.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));
            player3.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));
            player4.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));

        } else {
            player1.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
            player2.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
            player3.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
            player4.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        }





        path = getIntent().getStringExtra("live_path");
        Nome = getIntent().getStringExtra("live_name");

        String appKey = "a333e7d51b3a83c41e39347dcbdacbf6e1abfd3ae009ac64";
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER);

        Appodeal.setBannerViewId(R.id.appodealBannerView);

        //  Appodeal.setTesting(true);

        Appodeal.show(this, Appodeal.BANNER);


        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                Appodeal.show(EscolhaPlayer.this, Appodeal.BANNER);
                Log.d("Appodeal", "onBannerLoaded");
            }

            @Override
            public void onBannerFailedToLoad() {
                Appodeal.hide(EscolhaPlayer.this, Appodeal.BANNER);
                Log.d("Appodeal", "onBannerFailedToLoad");
            }

            @Override
            public void onBannerShown() {
                Log.d("Appodeal", "onBannerShown");
            }

            @Override
            public void onBannerClicked() {
                Log.d("Appodeal", "onBannerClicked");
            }

            @Override
            public void onBannerExpired() {
                Log.d("Appodeal", "onBannerExpired");
            }
        });

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {
                Log.d("Appodeal", "onInterLoaded");
            }

            @Override
            public void onInterstitialFailedToLoad() {
                Appodeal.hide(EscolhaPlayer.this, Appodeal.INTERSTITIAL);
                Log.d("Appodeal", "onInterFailed");
            }

            @Override
            public void onInterstitialShown() {
                Log.d("Appodeal", "onInterShown");

            }

            @Override
            public void onInterstitialClicked() {
                Log.d("Appodeal", "onInterClicked");
            }

            @Override
            public void onInterstitialClosed() {
                Log.d("Appodeal", "onInterClosed");
            }

            @Override
            public void onInterstitialExpired() {
                Log.d("Appodeal", "onInterExpired");
            }
    });

        canal.setText(Nome);

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EscolhaPlayer.this, LivePlayerActivity.class);
                intent.putExtra("path", path);
                intent.putExtra("Nome", Nome);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                showAd();

            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EscolhaPlayer.this, FastPlayer.class);
                intent.putExtra("path", path);
                intent.putExtra("Nome", Nome);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);

                showAd1();

            }
        });


        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EscolhaPlayer.this, Player.class);
                intent.putExtra("path", path);
                intent.putExtra("Nome", Nome);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);

                showAd2();

            }
        });


        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                playerext();

            }
        });



    }










    //        loadBannerAd();
     //   loadInterstitialAd();



    private void showAd() {
        if (Config.ENABLE_APPODEAL_INTERSTITIAL_ADS) {

            if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {

                if (counter == Config.APPOEDAL_INTERSTITIAL_ADS_INTERVAL) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("Apodeal", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("Appodeal", "Appodeal Interstitial is Disabled");
        }

    }


    private void showAd1() {
        if (Config.ENABLE_APPODEAL_INTERSTITIAL_ADS) {

            if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {

                if (counter == Config.APPOEDAL_INTERSTITIAL_ADS_INTERVAL) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("Apodeal", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("Appodeal", "Appodeal Interstitial is Disabled");
        }

    }


    private void showAd2() {
        if (Config.ENABLE_APPODEAL_INTERSTITIAL_ADS) {

            if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {

                if (counter == Config.APPOEDAL_INTERSTITIAL_ADS_INTERVAL) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("Apodeal", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("Appodeal", "Appodeal Interstitial is Disabled");
        }

    }

    private void showAd3() {
        if (Config.ENABLE_APPODEAL_INTERSTITIAL_ADS) {

            if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {

                if (counter == Config.APPOEDAL_INTERSTITIAL_ADS_INTERVAL) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("Apodeal", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("Appodeal", "Appodeal Interstitial is Disabled");
        }

    }


    private void showAd4() {
        if (Config.ENABLE_APPODEAL_INTERSTITIAL_ADS) {

            if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {

                if (counter == Config.APPOEDAL_INTERSTITIAL_ADS_INTERVAL) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    counter = 1;
                } else {
                    counter++;
                }

            } else {
                Log.d("Apodeal", "Interstitial Ad is Disabled");
            }
        } else {
            Log.d("Appodeal", "Appodeal Interstitial is Disabled");
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Appodeal.destroy(Appodeal.BANNER);
        Log.d("Appodeal","BannerDestroy");
    }

    private void playerext() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.player_externo, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        Button mx = deleteDialogView.findViewById(R.id.Mxplayer);
        Button vlc = deleteDialogView.findViewById(R.id.Vlc);

        if(sharedpref.loadNightModeState()) {

            mx.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));
            vlc.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_dark));

        } else if(!sharedpref.loadNightModeState()) {
            mx.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
            vlc.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));

        }

                mx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(path));
                            intent.setPackage("com.mxtech.videoplayer.ad");
                            startActivity(intent);
                            showAd3();

                        } catch (ActivityNotFoundException anfe) {
                            mxplayer();
                        }
                    }

                });


        vlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(path));
                    intent.setPackage("org.videolan.vlc");
                    startActivity(intent);
                    showAd4();

                } catch (ActivityNotFoundException anfe) {
                    player();
                }
            }
        });


        deleteDialog.show();
    }

    private void mxplayer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EscolhaPlayer.this);
        builder.setTitle(R.string.player_externo);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(R.string.instalar_mxplayer)
                .setCancelable(false)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(R.string.market + "com.mxtech.videoplayer.ad")));
                        } catch (ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(R.string.play_store + "com.mxtech.videoplayer.ad")));
                        }
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




    private void player() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EscolhaPlayer.this);
        builder.setTitle(R.string.player_externo);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(R.string.instalar_vlc)
                .setCancelable(false)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(R.string.market + "org.videolan.vlc")));
                        } catch (ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(R.string.play_store + "org.videolan.vlc")));
                        }
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
/**
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
    } **/


   /** private void showInterstitialAd() {
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
    } **/


   /** public void loadBannerAd() {
        if (Config.ENABLE_ADMOB_BANNER_ADS) {
            MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.admob_banner_ad_unit_id));
            adView = (AdView) findViewById(R.id.adView);
            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {
                    adView.loadAd(new AdRequest.Builder().build());
                }

                @Override
                public void onAdFailedToLoad(int error) {

                    adView.setVisibility(View.GONE);

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

                }
            });

        } else {
            Log.d("AdMob", "AdMob Banner is Disabled");
        }

    } **/

    @Override
    public void onBackPressed() {
       super.onBackPressed();
       // showInterstitialAd1();
    }

  /**  private void showInterstitialAd1() {
            if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {

                if (interstitialAd != null && interstitialAd.isLoaded()) {

                    if (counter == Config.ADMOB_INTERSTITIAL_ADS_INTERVAL1) {
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
        } **/


}


