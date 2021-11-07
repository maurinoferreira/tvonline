package com.m.f.tvonline.Categoriascanais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.m.f.tvonline.Live.Live;
import com.m.f.tvonline.Live.LiveAdapter;
import com.m.f.tvonline.R;

import java.util.ArrayList;
import java.util.List;


public class Religiosos extends AppCompatActivity {

    private ListView listView;
    private List<Live> liveList;
    private LiveAdapter adapter;
    private AdView adView;
    private InterstitialAd interstitialAd;
    int counter = 1;
    RelativeLayout rela;
    String title;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_layout);

        rela = findViewById(R.id.rela);
        title = getIntent().getStringExtra("categoria");


        this.setTitle(title);



        initView();
        loadBannerAd();
        loadInterstitialAd();
    }




    private void initView() {
        listView = (ListView) findViewById(R.id.live_list);
        liveList = new ArrayList<>();

        Live live;
        live = new Live("Globo ","http://apk2.futeboltvgratis.com:8081/live/17/playlist.m3u8");
        liveList.add(live);
        live = new Live("SBT", "http://evpp.mm.uol.com.br/ne10/ne10.smil/playlist.m3u8");
        liveList.add(live);
        live = new Live("SBT GO SD", "http://tvsd2.zoeweb.tv:1935/tvsd2/smil:tvsd2.smil/playlist.m3u8 ");
        liveList.add(live);
        live = new Live("SBT GO HD", "http://tvsd2.zoeweb.tv:1935/tvsd2/smil:tvsd2.smil/chunklist_w1473487700_b796000.m3u8");
        liveList.add(live);
        live = new Live("BAND NEWS", "http://tv.factoryiptv.com/live/nathan123/nathan123/37126.m3u8");
        liveList.add(live);
        live = new Live("TV NOVA PARAIBA", "http://stmv1.sejaguia.com.br/tv487/tv487/playlist.m3u8?wowzasessionid=1451503404");
        liveList.add(live);
        live = new Live("SBT PI HD", "http://192.99.46.182:1935/pioneira-tv3/pioneira-tv3/playlist.m3u8");
        liveList.add(live);
        live = new Live("HEART TV", "http://ooyalahd2-f.akamaihd.net/i/globalradio02_delivery@156522/index_656_av-p.m3u8?sd=10&rebase=on");
        liveList.add(live);
        live = new Live("TVE Brasil", "http://streaming.procergs.com.br:1935/tve/stve/playlist.m3u8");
        liveList.add(live);
        live = new Live("TV Diário Do Sertão", "http://painelvj.com.br/pdsertaotv/pdsertaotv.sdp/chunklist_w753093291.m3u8");
        liveList.add(live);
        live = new Live("Yeeah! Teen", "http://stmv2.srvstm.com/yeeahteen/yeeahteen/playlist.m3u8?wowzasessionid=877531286");
        liveList.add(live);
        live = new Live("TV Record", "http://srv3.zoeweb.tv:1935/zw315/zw315tvs/chunklist_w319268296.m3u8");
        liveList.add(live);
        live = new Live("TV Band", "http://evpp.mm.uol.com.br:1935/geob_band/bandapp/chunklist_w1312610714.m3u8");
        liveList.add(live);
        live = new Live("Via Morena", "https://5a8d73edc0407.streamlock.net/omdvia1str/viawebtv1/chunklist_w353920766.m3u8");
        liveList.add(live);
        live = new Live("TV Jornal (SBT PE)", "http://evpp.mm.uol.com.br/ne10/ne10.smil/chunklist_w1597544782_b216000_sleng.m3u8");
        liveList.add(live);
        live = new Live("TV Record RJ", "http://wz3.dnip.com.br/tvgazetamt/tvgazetamt.sdp/chunklist_w3806020.m3u8");
        liveList.add(live);
        live = new Live("Rede Vida", "http://wpc.9280f.sigmacdn.net/249280F/default/default.m3u8");
        liveList.add(live);
        live = new Live("SBT Interior", "https://5a1c76baf08c0.streamlock.net/z343-live/stream/chunklist_w1093560975.m3u8");
        liveList.add(live);
        live = new Live("TV Gazeta (Record MT)", "http://wz3.dnip.com.br/tvgazetamt/tvgazetamt.sdp/chunklist_w1141052766.m3u8");
        liveList.add(live);
        live = new Live("TV Pampa (Rede TV RS)", "https://v8.ciclano.io:1443/rafael926/_definst_/rafael926/chunklist_w2025268152.m3u8");
        liveList.add(live);
        live = new Live("TV Terra Viva", "http://evpp.mm.uol.com.br:1935/band/terraviva/chunklist_w283850461.m3u8?LISTASDAGLAU");
        liveList.add(live);
        live = new Live("TV Master", "http://stmv2.paraibahost.com.br/tvmaster/tvmaster/playlist.m3u8?wowzasessionid=855906078");
        liveList.add(live);
        live = new Live("TV Serra Dourada (SBT GO)", "http://tvsd2.zoeweb.tv:1935/tvsd2/smil:tvsd2.smil/chunklist_w29752069_b796000.m3u8");
        liveList.add(live);
        live = new Live("TV Sucesso (Record GO)", "http://srv3.zoeweb.tv:1935/zw315/zw315tvs/chunklist_w685345813.m3u8");
        liveList.add(live);
        live = new Live("TV Cidade Verde (SBT PI)", "http://192.99.46.182:1935/pioneira-tv3/pioneira-tv3/playlist.m3u8");
        liveList.add(live);
        live = new Live("TV Gazeta", "http://api.new.livestream.com:80/accounts/5381476/events/2395418/live.m3u8");
        liveList.add(live);
        live = new Live("Rede Meio Norte", "https://playback.akamaized.net/streams/3332377_2958396_lsi1f8z0cge8oygg77c_1/media/3332377_2958396_lsi1f8z0cge8oygg77c_1@446000p.m3u8?dw=14400&ts=1536447600&hdnts=exp=1536621857~acl=/streams/3332377_2958396_lsi1f8z0cge8oygg77c_1/media*~hmac=ef2517215d8cf59de13e6f13604be68f91925d1bad1aebb231383398bdca5e84");
        liveList.add(live);
        live = new Live("TV Poços", "https://rtmp.cdn.upx.net.br/00084/myStream.sdp/chunklist_w1879811886.m3u8");
        liveList.add(live);
        live = new Live("TV Metropole", "http://in.uaimacks.net.br:1935/macks/macks.sdp/chunklist_w1559311696.m3u8");
        liveList.add(live);
        live = new Live("Rede Minas", "https://slbps-sambatech.akamaized.net/live/3282%2C8114%2Cec4b5a296d97fa99bf990662f5b4f8e1%3Bbase64np%3BMc8VCxqNjXKHAfw%21/amlst%3AMc_tFgfGiHOdQXPB/chunklist_b954368_DVR.m3u8?sts=st=1536448397~exp=1536454997~acl=/*~hmac=9f9206a43c8f991169237f28f7427fe2355ab8a87886ca5cf2939666f9ecf551");
        liveList.add(live);
        live = new Live("TV Difusora Sul", "http://difusorama.zoeweb.tv:1935/z437-live/stream/chunklist_w1429672111.m3u8");
        liveList.add(live);
        live = new Live("TV A Crítica (Record AM)", "https://slbps-sambatech.akamaized.net/live/3256%2C7930%2C679f501d2054fc8514ea1fc28fe5f167%3Bbase64np%3BS52I5Up2XHDMC-c%21/amlst%3AS51w_Fc9WXHTS2s2/chunklist_b2146304.m3u8?sts=st=1536423462~exp=1536430062~acl=/*~hmac=1a3b48abf92ea6c9ab702240de5d27ce27d535f18a79877c6e714912d2a90222");
        liveList.add(live);
        live = new Live("TV Santa Cecilia", "http://flash1.crossdigital.com.br:1935/2063/2063/chunklist_w1160689652.m3u8");
        liveList.add(live);
        live = new Live("TV Pantanal", "http://177.36.192.78:1935/tvpantanalms/tvpantanalms/chunklist_w1748197954.m3u8");
        liveList.add(live);
        live = new Live("TVN Brasil", "http://wz6.dnip.com.br:1935/tvnbrasil/tvnbrasil.sdp/chunklist_w1849106650.m3u8");
        liveList.add(live);
        live = new Live("TVBE - Tv Brasil Esperança", "https://cdn.jmvstream.com/live/hls/lv_483/LV483_94sbsyJ6cF.m3u8");
        liveList.add(live);
        live = new Live("TV Tambaú", "https://hls.portalt5.com.br/abr_tvtambau/aovivo/tvtambau/aovivo_1/chunks.m3u8?nimblesessionid=419343");
        liveList.add(live);
        live = new Live("Amazon Sat - Amazonas", "http://slrp.sambavideos.sambatech.com/liveevent/amazonsatabr1_3e9c859611a5e7fbedc785bd33c418b5/livestream3/chunklist.m3u8");
        liveList.add(live);
        live = new Live("TV SOL", "http://wse01.logicahost.com.br:1935/tvsol/_definst_/tvsol/chunklist_w562054553.m3u8");
        liveList.add(live);
        live = new Live("Cine+", "http://189.86.89.117:8080/hls/livecm/1_2/index.m3u8?sessId=4716");
        liveList.add(live);
        live = new Live("TV NBR", "http://ebctveventual01-live.hls.adaptive.level3.net/hls-live/ebcremuxlive-ebctveventual01/_definst_/live/stream1.m3u8");
        liveList.add(live);
        live = new Live("Record News", "http://sm.fabricahost.com.br:1935/tvguara/tvguara/chunklist_w1935869420.m3u8");
        liveList.add(live);
        live = new Live("Canal Futura", "http://caikron.com.br:1935/unipar/tvup/chunklist_w83806858.m3u8");
        liveList.add(live);
        live = new Live("Tv Cultura HD", "http://177.73.84.49/hls/live/live.m3u8");
        liveList.add(live);
        live = new Live("Rede TV", "http://evpp.mm.uol.com.br/redetv1/redetv1/chunklist_w465994065.m3u8");
        liveList.add(live);
        live = new Live("TV CARIOCA.NET", "http://hd4.com.br:1935/tvcarioca/tvcarioca/chunklist_w1392734984.m3u8");
        liveList.add(live);
        live = new Live("TV Guará (Record News)", "http://sm.fabricahost.com.br:1935/tvguara/tvguara/chunklist_w1753793996.m3u8");
        liveList.add(live);
        live = new Live("Tv Uniao de Natal", "http://flash5.locamega.com.br:1935/locamega/_definst_/locamega/chunklist_w1450991588.m3u8");
        liveList.add(live);
        live = new Live("TV CATVE (Cultura PR)", "http://wowza4.catve.com.br:1935/live/livestream/chunklist_w980038133.m3u8");
        liveList.add(live);
        live = new Live("Mundo TV", "http://ebctveventual01-live.hls.adaptive.level3.net/hls-live/ebcremuxlive-ebctveventual01/_definst_/live/stream1.m3u8");
        liveList.add(live);
        live = new Live("TV ESCOLA", "http://slrp.sambavideos.sambatech.com/liveevent/acerpTvEscolaABR_1f9a5d00db56b3c3020b6ac3dd693e12/livestream3/playlist.m3u8");
        liveList.add(live);
        live = new Live("Red Bull TV", "https://rbmn-live.akamaized.net/hls/live/590964/BoRB-AT/master_fb_3360.m3u8");
        liveList.add(live);
        live = new Live("All Sports TV", "https://59f1cbe63db89.streamlock.net:1443/dgrau/_definst_/dgrau/chunklist_w2051307453.m3u8");
        liveList.add(live);
        live = new Live("Top Tv", "http://sateg.cdnseguro.com/toptv/toptv/playlist.m3u8?wowzasessionid=1482873169");
        liveList.add(live);
        live = new Live("TV Aratu", "https://cdn.jmvstream.com/live/hls/lv_5347/LV5347_oKO9R0jr5Y.m3u8");
        liveList.add(live);
        live = new Live("NTV", "http://srv02.brasilstream.com.br:8080/live/ntv/ntv/mono.m3u8");
        liveList.add(live);
        live = new Live("TV Candidés", "https://cdn.jmvstream.com/live/hls/lv_6453_transcoder/LV6453_bXZTxrPxqo_1080p.m3u8");
        liveList.add(live);
        live = new Live("Rede Super", "https://secure-playlist.livestream.com/streams/10205943_3429501_lsinfhb6tvauz9831y0_1/media/10205943_3429501_lsinfhb6tvauz9831y0_1@678000p.m3u8?dw=14400&token=1539555021_29c6c8edf91f5843992b2761a22335ae86fc5680&ts=1539381600");
        liveList.add(live);
        live = new Live("TVBE - Tv Brasil ", "https://cdn.jmvstream.com/live/hls/lv_483/LV483_94sbsyJ6cF.m3u8");
        liveList.add(live);
        live = new Live("TV Portal Midia", "http://video02.kshost.com.br/tvportalmidia/tvportalmidia/chunklist_w1307286096.m3u8");
        liveList.add(live);
        live = new Live("Tv o Vale", "http://stmv4.euroti.com.br/tvovale/tvovale/playlist.m3u8?wowzasessionid=1270799241");
        liveList.add(live);
        live = new Live("Radio Esportes Brasília", "http://173.236.10.10:1935/dgrau/dgrau/chunklist_w602805413.m3u8");
        liveList.add(live);
        live = new Live("TVC 16", "http://stmv3.srvstm.com/tvc16/tvc16/playlist.m3u8?wowzasessionid=1146189154");
        liveList.add(live);
        live = new Live("Tv Zoom", "https://cdn.jmvstream.com/live/hls/lv_751/LV751_rdDjSLQZp6.m3u8");
        liveList.add(live);
        live = new Live("Rede NGT", "https://cdn.jmvstream.com/live/hls/lv_6099/LV6099_vKVncvJJBh.m3u8");
        liveList.add(live);
        live = new Live("Rede Litoral News", "https://cdn.jmvstream.com/live/hls/lv_233/LV233_MXKNfeKoDo.m3u8");
        liveList.add(live);
        live = new Live("Rede Caxias Tv", "http://stmv5.pagehost.com.br/luisclaudio8226/luisclaudio8226/playlist.m3u8?wowzasessionid=155685895");
        liveList.add(live);
        live = new Live("Buzios TV", "http://wsecast.com.br:1935/buziostv799/_definst_/buziostv799/chunklist_w783096838.m3u8");
        liveList.add(live);
        live = new Live("Tv Cidade", "https://5b7f3c45ab7c2.streamlock.net/8002/8002/chunklist_w249882669.m3u8");
        liveList.add(live);
        live = new Live("TCM- TV Cabo Mossoró", "http://cw.tcm10.com.br/low/canal10.m3u8");
        liveList.add(live);
        live = new Live("Livre TV", "http://wse01.logicahost.com.br:1935/livretv/livretv/chunklist_w1745122774.m3u8");
        liveList.add(live);
        live = new Live("GV NEWS", "http://173.236.10.10:1935/gvnews/gvnews/chunklist_w1932966333.m3u8");
        liveList.add(live);
        live = new Live("TV Maceió", "http://173.236.10.10:1935/toptv/toptv/chunklist_w80357166.m3u8");
        liveList.add(live);
        live = new Live("TV Mar", "https://v7.ciclano.io:1443/tvmar/tvmar/chunklist_w535163322.m3u8");
        liveList.add(live);
        live = new Live("Tv Rio Largo", "http://stmv4.srvstm.com/tvriolargo/tvriolargo/playlist.m3u8?wowzasessionid=1608980004");
        liveList.add(live);
        live = new Live("Rede TV Rondônia", "https://59f1cbe63db89.streamlock.net:1443/tvonline/_definst_/tvonline/chunklist_w837022678.m3u8");
        liveList.add(live);
        live = new Live("SICtv (Record RO)", "https://cdn.jmvstream.com/live/hls/lv_712/LV712_Rfjqak3w63.m3u8");
        liveList.add(live);


        adapter = new LiveAdapter(Religiosos.this, liveList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Religiosos.this, EscolhaPlayer.class);
                intent.putExtra("live_path", liveList.get(position).path);
                intent.putExtra("live_name", liveList.get(position).live_name);
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





