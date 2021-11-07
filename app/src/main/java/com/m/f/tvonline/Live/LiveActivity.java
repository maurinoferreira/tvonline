package com.m.f.tvonline.Live;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.m.f.tvonline.Config;
import com.m.f.tvonline.EscolhaPlayer;;
import com.m.f.tvonline.R;
import com.m.f.tvonline.RateItDialogFragment;
import com.m.f.tvonline.SharedPref;


import java.util.ArrayList;
import java.util.List;

import io.presage.finder.model.App;


public class LiveActivity extends AppCompatActivity {

    private ListView listView;
    private List<Live> liveList;
    private LiveAdapter adapter;
    int counter = 1;
    RelativeLayout rela;
    SharedPref sharedpref;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Appodeal.destroy(Appodeal.BANNER_TOP);
        Log.d("Appodeal","BannerDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Appodeal.onResume(this,Appodeal.BANNER_TOP);
        Log.d("Appodeal","BannerOnResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = new SharedPref(this);


        if (sharedpref.loadNightModeState()== true) {

            setTheme(R.style.darktheme);

        } else setTheme(R.style.AppTheme);


        setContentView(R.layout.live_activity_layout);


        final String appKey = "a333e7d51b3a83c41e39347dcbdacbf6e1abfd3ae009ac64";
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER_TOP );
       // Appodeal.setTesting(true);
        Appodeal.setBannerViewId(R.id.appodealBannerView);
        Appodeal.show(this, Appodeal.BANNER_TOP);

        RateItDialogFragment.show(this, getFragmentManager());
        rela= findViewById(R.id.rela);

        if (!isConnected(LiveActivity.this))
            buildDialog(LiveActivity.this);
        else {
            Log.d("Conectado", "conectado");


        }

        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int i, boolean b) {
                Appodeal.show(LiveActivity.this, Appodeal.BANNER_TOP);
                 rela.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBannerFailedToLoad() {
              Appodeal.hide(LiveActivity.this, Appodeal.BANNER_TOP);
               rela.setVisibility(View.GONE);
            }

            @Override
            public void onBannerShown() {

            }

            @Override
            public void onBannerClicked() {

            }

            @Override
            public void onBannerExpired() {
              Appodeal.initialize(LiveActivity.this, appKey, Appodeal.BANNER_TOP);
            }
        });


        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {
                Log.d("Appodeal", "onInterLoaded");
            }

            @Override
            public void onInterstitialFailedToLoad() {
                Appodeal.hide(LiveActivity.this, Appodeal.INTERSTITIAL);
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


        initView();
       /* loadBannerAd();
        loadInterstitialAd();*/




    }

    private void initView() {
        listView = (ListView) findViewById(R.id.live_list);
        liveList = new ArrayList<>();

        Live live;
	/*	live = new Live("BBB", "http://apk26.futeboltvgratis.com/live/7/chunks.m3u8");
        liveList.add(live);
		live = new Live("Globo SD", "http://style.kastream.biz:8081/edge/ver69ca258bbf32/chunks.m3u8?nimblesessionid=470069&wmsAuthSign=c2VydmVyX3RpbWU9Mi8xNy8yMDE5IDQ6Mzg6NTEgUE0maGFzaF92YWx1ZT1jMVBseG0zNXIyaWxtS0V2V2hkWlZRPT0mdmFsaWRtaW51dGVzPTEyMA==");
        liveList.add(live);
		live = new Live("Globo HD", "https://www.skyiptv.com.br:25463/live/fwoiLSCNO39JIp/JLACYqow4753powei/17.m3u8");
        liveList.add(live); **/
        live = new Live("Record News", "http://stmv4.srvstm.com/recordnewses/recordnewses/playlist.m3u8?wowzasessionid=312646066");
        liveList.add(live);
        /**    live = new Live("ESPN Brasil", "http://apk26.futeboltvgratis.com/live/19/chunks.m3u8");
         liveList.add(live);
         live = new Live("ESPN", "http://apk26.futeboltvgratis.com/live/3/chunks.m3u8");
         liveList.add(live);
         live = new Live("Fox Sports", "http://apk26.futeboltvgratis.com/live/4/chunks.m3u8");
         liveList.add(live);
         live = new Live("Fox Sports 2", "http://apk26.futeboltvgratis.com/live/5/chunks.m3u8");
         liveList.add(live);
         live = new Live("Esporte TV", "http://apk26.futeboltvgratis.com/live/20/chunks.m3u8");
         liveList.add(live);
         live = new Live("Esporte TV 2", "http://apk26.futeboltvgratis.com/live/15/chunks.m3u8");
         liveList.add(live);**/
        live = new Live("Vem e vê Tv ", "https://srv-rp1.sisdera.com/hls/vvtv_480p/index.m3u8");
        liveList.add(live);
        live = new Live("SBT", "http://evpp.mm.uol.com.br/ne10/ne10.smil/playlist.m3u8");
        liveList.add(live);
        /** live = new Live("Cartoon Network HD", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/117.m3u8");
         liveList.add(live);
         live = new Live("Boomerang HD", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/127.m3u8");
         liveList.add(live);
         live = new Live("BAND News", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/98.m3u8");
         liveList.add(live);
         live = new Live("BAND Sports", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/34.m3u8");
         liveList.add(live);**/
        live = new Live("BAND", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/4.m3u8");
        liveList.add(live);
        live = new Live("TV NOVA PARAIBA", "http://stmv1.sejaguia.com.br/tv487/tv487/playlist.m3u8?wowzasessionid=1451503404");
        liveList.add(live);
        //	live = new Live("Discovery Turbo HD", "https://www.toquemp3.com:25463/live/WhrcLBZf20A/CdGiPPcQ19/109.m3u8?token=QxBcWRdYGgoRBQsCBlQIDVZTBl1SDwdTCQcNBAgADA8MUwQAUFZbBAdDHxcXREpcVghtUVcUXlIICxQRTUpQRWdbUBpeEgkSH0NHXAlVGgMHSRBIWlJGWRoEAQsOGxkVXUpAXRZeWVxsBVZDDVNdGw0LR1RfGkYMVm1cVlVYW1MaCBYJRhwaWUAREQ9Gc1RYRQoSax13SkEUEFpaTEtUQ10QDhpWCA8HEU0RVgtFVk1FHBACEXQ2QRQQXUtMXFtEUV1aGl4SVQNGWREZRllLZkUAQUxBUwUOXUAaCRoJFxsaX1VAO1NXXl0EUEENX1ZKFV8QCBEaRg5XXFFHV0tqR1FWFgJGBQ4FBUNO");
        //    liveList.add(live);
        //	live = new Live("Amazon Sat HD", "http://slrp.sambavideos.sambatech.com/liveevent/amazonsatabr1_3e9c859611a5e7fbedc785bd33c418b5/livestream3/chunklist.m3u8");
        //    liveList.add(live);
        live = new Live("HIT TV", "http://kissfm-cires21-video.secure.footprint.net/hittv/bitrate_3.m3u8");
        liveList.add(live);
        live = new Live("TV A Cara da Bahia", "http://hd4.com.br:1935/tvacaradabahia/tvacaradabahia/chunklist_w84716165.m3u8");
        liveList.add(live);
        live = new Live("Cine Sky", "http://stmv2.srvstm.com:1935/tvjsid/tvjsid/playlist.m3u8");
        liveList.add(live);
        live = new Live("Rede Família", "https://5a7bcfb834235.streamlock.net/zw901/smil:zw901.smil/chunklist_w1841066789_b300000.m3u8");
        liveList.add(live);
        live = new Live("TV Marajoara", "http://video01.tvdigitalhd.org/tv31966/tv31966/chunklist_w137192335.m3u8");
        liveList.add(live);
        live = new Live("TV Nova Nordeste", "https://cdn.jmvstream.com/live/hls/lv_7081/LV7081_1d9b6iVXj2.m3u8");
        liveList.add(live);
        live = new Live("ITV - Canal Itu Tv", "http://wse01.logicahost.com.br:1935/itutv/_definst_/itutv/chunklist_w1029188260.m3u8");
        liveList.add(live);
        live = new Live("TVE Brasil", "http://streaming.procergs.com.br:1935/tve/stve/playlist.m3u8");
        liveList.add(live);
        live = new Live("TV Record", "https://gbbrslbps-sambatech.akamaized.net/live/3170%2C7572%2Cc63dc860710b549f2a9f80342cd54418%3Bbase64np%3BDu4aOVgIM5Bl8fI%21/amlst%3ADu7iIEVDNpF6sX6G/chunklist_b832512.m3u8?sts=st=1537774647~exp=1537781247~acl=/*~hmac=b7d611bffa1e1e2cbffc9c0d4c24fd9d9a8fec255c56c839d5f2e332f9df6e56");
        liveList.add(live);
        live = new Live("Via Morena", "https://5a8d73edc0407.streamlock.net/omdvia1str/viawebtv1/chunklist_w559326523.m3u8");
        liveList.add(live);
        live = new Live("TV Tropical (Record RN)", "https://59f2354c05961.streamlock.net:1443/tvtropical/_definst_/tvtropical/chunklist_w51716316.m3u8");
        liveList.add(live);
        live = new Live("TV Jornal (SBT PE)", "http://evpp.mm.uol.com.br/ne10/ne10.smil/chunklist_w1597544782_b216000_sleng.m3u8");
        liveList.add(live);
        /**  live = new Live("Discovery HD", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/104.m3u8");
         liveList.add(live);
         live = new Live("Multishow HD", "https://www.skyiptv.com.br:25463/live/WhrcLBZf20A/CdGiPPcQ19/86.m3u8");
         liveList.add(live);
         live = new Live("Animal Planet HD", "https://www.toquemp3.com:25463/live/WhrcLBZf20A/CdGiPPcQ19/101.m3u8?token=QxBcWRdYGgoRUlUGAAMKWwRXBApWBl1TAFMACg4AAwINClYBVlEAUQtDHxcXREpcVghtUVcUXlIIAxQRTUpQRWdbUBpeEgkSH0NHXAlVGgMHSRBIWlJGWRoACgULARcbGldMTAFCVlFfPldQEllbXBVfXE1fWkhBV1xnV11UVFlcEA4aVRIUEloSQxdeEntVVhddGGAYJU0aHhpRUU1HVkxXFgJGAggHAkMfFwdfTVdDF0saCRQmMRoeGlZATVBZS1tbVkYKGl0AFAsXSBJRSmgXV0tHRAECVVdKEQIbBRUUEFlZHG9bX10PVlYQWVdXREcIGgMUSEFVXVZaTFZHaEhbUBpeEgkEAFAFFxk=");
         liveList.add(live); **/
        live = new Live("SBT Interior", "https://5a1c76baf08c0.streamlock.net/z343-live/stream/chunklist_w1093560975.m3u8");
        liveList.add(live);
        live = new Live("TV Gazeta (Record MT)", "http://api.new.livestream.com/accounts/5381476/events/2395418/live.m3u8");
        liveList.add(live);
        live = new Live("TV Pampa (Rede TV RS)", "https://v8.ciclano.io:1443/rafael926/_definst_/rafael926/chunklist_w2025268152.m3u8");
        liveList.add(live);
        live = new Live("TV Serra Dourada (SBT GO)", "http://tvsd2.zoeweb.tv:1935/tvsd2/smil:tvsd2.smil/chunklist_w29752069_b796000.m3u8");
        liveList.add(live);
        live = new Live("TV Gazeta", "http://api.new.livestream.com:80/accounts/5381476/events/2395418/live.m3u8");
        liveList.add(live);
        live = new Live("TV Poços", "https://rtmp.cdn.upx.net.br/00084/myStream.sdp/chunklist.m3u8?090ceb57687b01fd681cbcc27b715032");
        liveList.add(live);
        live = new Live("TV Metropole", "http://in.uaimacks.net.br:1935/macks/macks.sdp/chunklist_w1559311696.m3u8");
        liveList.add(live);
        live = new Live("Rede Minas", "https://slbps-sambatech.akamaized.net/live/3282%2C8114%2Cec4b5a296d97fa99bf990662f5b4f8e1%3Bbase64np%3BMc8VDxqNjXKCAf8%21/amlst%3AMc_tFgfGiHOdQXPB/chunklist_b954368.m3u8?sts=st=1544481305~exp=1544487905~acl=/*~hmac=919ddafdcfb14785090f52dd9a1972810c21e44f519285b321e80814fa8e7035");
        liveList.add(live);
        live = new Live("TV Difusora Sul", "http://difusorama.zoeweb.tv:1935/z437-live/stream/chunklist_w1429672111.m3u8");
        liveList.add(live);
        live = new Live("TV A Crítica (Record AM)", "https://slbps-sambatech.akamaized.net/live/3256%2C7930%2C679f501d2054fc8514ea1fc28fe5f167%3Bbase64np%3BS52I5Up2XHDMC-c%21/amlst%3AS51w_Fc9WXHTS2s2/chunklist_b2146304.m3u8?sts=st=1536423462~exp=1536430062~acl=/*~hmac=1a3b48abf92ea6c9ab702240de5d27ce27d535f18a79877c6e714912d2a90222");
        liveList.add(live);
        live = new Live("TV Santa Cecilia", "http://flash1.crossdigital.com.br:1935/2063/2063/chunklist_w1160689652.m3u8");
        liveList.add(live);
        live = new Live("TV Aparecida", "http://69.46.0.170:1935/tvaparecida/tvaparecida.stream/chunklist_w1858074958.m3u8");
        liveList.add(live);
        live = new Live("TV Pantanal", "http://177.36.192.78:1935/tvpantanalms/tvpantanalms/chunklist_w1748197954.m3u8");
        liveList.add(live);
        live = new Live("Rede Vida", "https://cvd1.cds.ebtcvd.net/live-redevida/smil:redevida.smil/chunklist_b1296000.m3u8");
        liveList.add(live);
        live = new Live("TVN Brasil", "http://wz6.dnip.com.br:1935/tvnbrasil/tvnbrasil.sdp/chunklist_w1849106650.m3u8");
        liveList.add(live);
        live = new Live("TV A Cara da Bahia", "http://hd4.com.br:1935/tvacaradabahia/tvacaradabahia/chunklist_w1405146077.m3u8");
        liveList.add(live);
        live = new Live("TV Revista", "http://wsecast.com.br:1935/tvprecabura/_definst_/tvprecabura/chunklist_w73230876.m3u8");
        liveList.add(live);
        live = new Live("TV Diário", "http://slrp.sambavideos.sambatech.com/liveevent/tvdiario_7a683b067e5eee5c8d45e1e1883f69b9/livestream/chunklist.m3u8");
        liveList.add(live);
        live = new Live("Canal Futura", "http://caikron.com.br:1935/unipar/tvup/chunklist_w83806858.m3u8");
        liveList.add(live);
        live = new Live("Tv Cultura HD", "http://177.74.1.38:80/funtelpa/tv_funtelpa/live.m3u8?CANALANDRONALTAS");
        liveList.add(live);
        live = new Live("Rede TV", "http://evpp.mm.uol.com.br/redetv1/redetv1/chunklist_w465994065.m3u8");
        liveList.add(live);
        live = new Live("TV CARIOCA.NET", "http://hd4.com.br:1935/tvcarioca/tvcarioca/chunklist_w1392734984.m3u8");
        liveList.add(live);
        live = new Live("Tv Uniao de Natal", "http://flash5.locamega.com.br:1935/locamega/_definst_/locamega/chunklist_w1450991588.m3u8");
        liveList.add(live);
        live = new Live("TV CATVE (Cultura PR)", "http://wowza4.catve.com.br:1935/live/livestream/chunklist_w980038133.m3u8");
        liveList.add(live);
        live = new Live("TV ESCOLA", "http://slrp.sambavideos.sambatech.com/liveevent/acerpTvEscolaABR_1f9a5d00db56b3c3020b6ac3dd693e12/livestream3/playlist.m3u8");
        liveList.add(live);
        // live = new Live("Red Bull TV", "https://rbmn-live.akamaized.net/hls/live/590964/BoRB-AT/master_3360.m3u8");
        // liveList.add(live);
        live = new Live("All Sports TV", "https://59f1cbe63db89.streamlock.net:1443/dgrau/_definst_/dgrau/chunklist_w2051307453.m3u8");
        liveList.add(live);
        live = new Live("Top Tv", "http://sateg.cdnseguro.com/toptv/toptv/playlist.m3u8?wowzasessionid=1482873169");
        liveList.add(live);
        live = new Live("NTV", "http://srv02.brasilstream.com.br:8080/live/ntv/ntv/mono.m3u8");
        liveList.add(live);
        live = new Live("TV Candidés", "https://cdn.jmvstream.com/live/hls/lv_6453_transcoder/LV6453_bXZTxrPxqo_1080p.m3u8");
        liveList.add(live);
        live = new Live("Tv o Vale", "http://stmv4.euroti.com.br/tvovale/tvovale/playlist.m3u8?wowzasessionid=1270799241");
        liveList.add(live);
        live = new Live("Radio Esportes Brasília", "http://173.236.10.10:1935/dgrau/dgrau/chunklist_w602805413.m3u8");
        liveList.add(live);
        live = new Live("Tv Zoom", "https://cdn.jmvstream.com/live/hls/lv_751/LV751_rdDjSLQZp6.m3u8");
        liveList.add(live);
        live = new Live("Rede Litoral News", "https://cdn.jmvstream.com/live/hls/lv_233/LV233_MXKNfeKoDo.m3u8");
        liveList.add(live);
        live = new Live("TCM- TV Cabo Mossoró", "http://cw.tcm10.com.br/low/canal10.m3u8");
        liveList.add(live);
        live = new Live("TV Maceió", "http://173.236.10.10:1935/toptv/toptv/chunklist_w80357166.m3u8");
        liveList.add(live);
        live = new Live("TV Mar", "https://v7.ciclano.io:1443/tvmar/tvmar/chunklist_w535163322.m3u8");
        liveList.add(live);
        live = new Live("Tv Rio Largo", "http://stmv4.srvstm.com/tvriolargo/tvriolargo/playlist.m3u8?wowzasessionid=1608980004");
        liveList.add(live);
        live = new Live("Rede TV Rondônia", "https://59f1cbe63db89.streamlock.net:1443/tvonline/_definst_/tvonline/chunklist_w837022678.m3u8");
        liveList.add(live);
        live = new Live("Todo mundo odeia o Chris", "http://apk26.futeboltvgratis.com/live/10/chunks.m3u8");
        liveList.add(live);


        adapter = new LiveAdapter(LiveActivity.this, liveList);
        listView.setAdapter(adapter);




 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**  if (position == 1) {

         try {
         Intent intent = new Intent(Intent.ACTION_VIEW);
         intent.setData(Uri.parse("https://www.skyiptv.com.br:25463/live/alksfaksdfpwoie/roipqsioercmkls/17.m3u8"));
         intent.setPackage("com.mxtech.videoplayer.ad");
         startActivity(intent);
         showInterstitialAd();

         } catch (android.content.ActivityNotFoundException anfe) {
         player1();
         }
         }

         if  (position == 2) {
         try {
         Intent intent = new Intent(Intent.ACTION_VIEW);
         intent.setData(Uri.parse("https://www.skyiptv.com.br:25463/live/alksfaksdfpwoie/roipqsioercmkls/100.m3u8"));
         intent.setPackage("org.videolan.vlc");
         startActivity(intent);
         showInterstitialAd();

         } catch (android.content.ActivityNotFoundException anfe) {
         player();
         }
         }  else if (position == 3) {
         Intent i = new Intent(LiveActivity.this, Youtube.class);
         i.putExtra("live_path", liveList.get(position).path);
         startActivity(i);
         showInterstitialAd();
         } else if (position == 4) {
         Intent i = new Intent(LiveActivity.this, Youtube.class);
         i.putExtra("live_path", liveList.get(position).path);
         startActivity(i);
         showInterstitialAd();
         } else {**/

         Intent intent = new Intent(LiveActivity.this, EscolhaPlayer.class);
         intent.putExtra("live_path", liveList.get(position).path);
         intent.putExtra("live_name", liveList.get(position).live_name);
         startActivity(intent);
         overridePendingTransition(R.anim.translate_in, R.anim.translate_out);

         //showInterstitialAd();
         showAd();

         }




         });


         }


        /**  private void player1() {
         AlertDialog.Builder builder = new AlertDialog.Builder(LiveActivity.this);
         builder.setTitle("Player externo");
         builder.setIcon(android.R.drawable.ic_dialog_alert);
         builder.setMessage("Deseja instalar o MX Player ?")
         .setCancelable(false)
         .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {

         try {
         startActivity(new Intent(Intent.ACTION_VIEW, parse("market://details?id=" + "com.mxtech.videoplayer.ad")));
         } catch (android.content.ActivityNotFoundException anfe) {
         startActivity(new Intent(Intent.ACTION_VIEW, parse("http://play.google.com/store/apps/details?id=" + "com.mxtech.videoplayer.ad")));
         }
         }
         })
         .setNegativeButton("Não", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
         dialog.cancel();
         }
         });
         AlertDialog alert = builder.create();
         alert.show();

         }**/



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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //noinspection SimplifiableIfStatement
            case R.id.drawer_rate:
                final String appName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.market) + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_store) + appName)));
                }

                return true;


            case R.id.drawer_more:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_more_apps))));


                return true;

            case R.id.url:
                abrirUrl();

                return true;

            case R.id.nightmode:
                if (sharedpref.loadNightModeState()==true) {

                    item.setChecked(true);

                }
                if (item.isChecked()) {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
                else{
                    item.setChecked(true);
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                return true;


            case R.id.drawer_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = getString(R.string.gostaria_compartilhar) +
                        getString(R.string.package_name);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.App_name));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.compartilhar)));


                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void abrirUrl() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.abrir_url, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        final EditText edit = deleteDialogView.findViewById(R.id.abrirurl);

        deleteDialogView.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edit.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(LiveActivity.this, R.string.URL_canal, Toast.LENGTH_SHORT).show();

                } else {
                    Intent i = new Intent(LiveActivity.this, EscolhaPlayer.class);
                    i.putExtra("live_path", url);
                    startActivity(i);
                    edit.setText("");
                    deleteDialog.dismiss();
                }

            }

        });

        deleteDialog.show();
    }


    private void restartApp() {
        Intent i = new Intent(getApplicationContext(),LiveActivity.class);
        startActivity(i);
        finish();
    }

    @Override
            public void onBackPressed() {
                sair();
            }

            private void sair() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LiveActivity.this);
                builder.setTitle(R.string.confirmar);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage(R.string.deseja_sair)
                        .setCancelable(false)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
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


         /*   private void loadInterstitialAd() {
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
                            Log.d("AdMob", "Adclosed");
                        }

                        @Override
                        public void onAdFailedToLoad(int error) {

                            adView.setVisibility(View.GONE);
                            rela.setVisibility(View.GONE);

                            Log.d("AdMob", "Banner failed " +
                                    "load");
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

            } */

            public boolean isConnected(Context c) {
                ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                try {
                    NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                    if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

                        buildDialog(LiveActivity.this);
                        return false;
                    }

                    return true;
                } catch (NullPointerException e) {
                             e.printStackTrace();
                }
                return true;
            }


            public void buildDialog(Context context) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_TRADITIONAL);

                alertDialog.setTitle(R.string.aviso);
                alertDialog.setCancelable(false);
                alertDialog.setMessage(getString(R.string.sem_internet) +
                        getString(R.string.ativar_msg));
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setPositiveButton(R.string.ativar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(dialogIntent);
                        dialog.cancel();
                    }
                });

                alertDialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.show();

            }



}


/**    private void player() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LiveActivity.this);
        builder.setTitle("Player externo");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Deseja instalar o VLC ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, parse("market://details?id=" + "org.videolan.vlc")));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, parse("http://play.google.com/store/apps/details?id=" + "org.videolan.vlc")));
                        }
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }**/


