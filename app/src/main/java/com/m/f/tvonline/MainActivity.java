package com.m.f.tvonline;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.m.f.tvonline.Categoriascanais.Abertos;
import com.m.f.tvonline.Categoriascanais.CatAdapter;
import com.m.f.tvonline.Categoriascanais.Categorias;
import com.m.f.tvonline.Categoriascanais.Desenhos;
import com.m.f.tvonline.Categoriascanais.Educativos;
import com.m.f.tvonline.Categoriascanais.Esportes;
import com.m.f.tvonline.Categoriascanais.Noticias;
import com.m.f.tvonline.Categoriascanais.Religiosos;
import com.m.f.tvonline.Categoriascanais.Variedades;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Categorias> catList;
    private CatAdapter adapter;
    private AdView adView;
    private InterstitialAd interstitialAd;
    RelativeLayout rela;
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpref = new SharedPref(this);

        if(sharedpref.loadNightModeState()==true) {

            setTheme(R.style.darktheme);

        }

        else  setTheme(R.style.AppTheme);



        setContentView(R.layout.catlayout);

        RateItDialogFragment.show(this, getFragmentManager());
        rela = findViewById(R.id.rela);


        if (!isConnected(MainActivity.this))
            buildDialog(MainActivity.this);
        else {
            Log.d("Conectado", "conectado");


        }

        initView();
        loadBannerAd();
        loadInterstitialAd();

    }

    private void initView() {

        listView = (ListView) findViewById(R.id.cat_list);
        catList = new ArrayList<>();

        Categorias cat;
        cat = new Categorias("Abertos");
        catList.add(cat);
        cat = new Categorias("Educativos");
        catList.add(cat);
        cat = new Categorias("Desenhos");
        catList.add(cat);
        cat = new Categorias("Esportes");
        catList.add(cat);
        cat = new Categorias("Notícias");
        catList.add(cat);
        cat = new Categorias("Religiosos");
        catList.add(cat);
        cat = new Categorias("Variedades");
        catList.add(cat);


        adapter = new CatAdapter(MainActivity.this, catList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0){
                    Intent i = new Intent(getApplicationContext(), Abertos.class);
                    i.putExtra("categoria","Abertos");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==1){
                    Intent i = new Intent(getApplicationContext(), Educativos.class);
                    i.putExtra("categoria","Educativos");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==2){
                    Intent i = new Intent(getApplicationContext(), Desenhos.class);
                    i.putExtra("categoria","Desenhos");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==3){
                    Intent i = new Intent(getApplicationContext(), Esportes.class);
                    i.putExtra("categoria","Esportes");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==4){
                    Intent i = new Intent(getApplicationContext(), Noticias.class);
                    i.putExtra("categoria","Noticias");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==5){
                    Intent i = new Intent(getApplicationContext(), Religiosos.class);
                    i.putExtra("categoria","Religiosos");
                    startActivity(i);
                    showInterstitialAd();
                }
                else if(position ==6){
                    Intent i = new Intent(getApplicationContext(), Variedades.class);
                    i.putExtra("categoria","Variedades");
                    startActivity(i);
                    showInterstitialAd();
                }



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

                // if (counter == Config.ADMOB_INTERSTITIAL_ADS_INTERVAL) {
                interstitialAd.show();
                //  counter = 1;
                //  } else {
                //   counter++;
                //  }

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
                    adView.loadAd(new AdRequest.Builder().build());

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
        sair();
    }

    private void sair() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmação");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Certeza que deseja sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.drawer_rate) {
            final String appName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
            }

            return true;


        } else if (id == R.id.drawer_more) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_more_apps))));

        } else if (id == R.id.drawer_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "Gostaria de compartilhar esse app de TV Online. " +
                    "https://play.google.com/store/apps/details?id=com.m.f.player";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TV Online BR");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Compartilhar"));


            return true;

        }

        return super.onOptionsItemSelected(item);
    }





    public boolean isConnected(Context c) {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

                buildDialog(MainActivity.this);
                return false;
            }

            return true;
        } catch (NullPointerException e) {

        }
        return true;
    }


    public void buildDialog(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_TRADITIONAL);

        alertDialog.setTitle("Aviso");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Sem conexão com a internet. " +
                "Gostaria de ativar?");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton("ATIVAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(" CANCELAR ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                finish();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();

    }
}
