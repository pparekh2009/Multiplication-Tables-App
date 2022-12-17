package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.AdManager;
import com.priyanshparekh.multiplicationtables.helper.Constants;
import com.priyanshparekh.multiplicationtables.helper.HelperResizer;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;

public class TableRangeActivity extends AppCompatActivity implements IUnityAdsInitializationListener {

    Button btn1_10, btn11_20, btn21_30, btn31_40, btn41_50, btn51_60, btn61_70, btn71_80, btn81_90, btn91_100;

    AdManager adManager;

    BannerView bannerView;
    RelativeLayout bannerContainer;

    ConstraintLayout topBar;
    TextView tvHeader;
    ImageView ivBack;

    int range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_table_category);

        initViews();
        resize();

        tvHeader.setText("Select Table Range");
        ivBack.setOnClickListener(view -> onBackPressed());

        UnityAds.initialize(this, Constants.unityGameId, Constants.testMode, this);

        adManager = new AdManager(this);
        bannerView = adManager.initBanner();
        bannerView.load();

        bannerContainer = findViewById(R.id.tca_banner_container);
        bannerContainer.addView(bannerView);

        btn1_10.setOnClickListener(view -> nextActivity(0));
        btn11_20.setOnClickListener(view -> nextActivity(10));
        btn21_30.setOnClickListener(view -> nextActivity(20));
        btn31_40.setOnClickListener(view -> nextActivity(30));
        btn41_50.setOnClickListener(view -> nextActivity(40));
        btn51_60.setOnClickListener(view -> nextActivity(50));
        btn61_70.setOnClickListener(view -> nextActivity(60));
        btn71_80.setOnClickListener(view -> nextActivity(70));
        btn81_90.setOnClickListener(view -> nextActivity(80));
        btn91_100.setOnClickListener(view -> nextActivity(90));
    }

    private void resize() {
        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(topBar, 1080, 150);
        HelperResizer.setSize(ivBack, 100, 51);
    }

    void setCount(int count) {
        SharedPreferences preferences = getSharedPreferences("AdCount", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("table_count", count);
        editor.apply();
    }

    int getCount() {
        SharedPreferences preferences = getSharedPreferences("AdCount", MODE_PRIVATE);
        return preferences.getInt("table_count", 0);
    }

    int sdpToPixel(int sdp) {
        return getResources().getDimensionPixelSize(sdp);
    }

    private void initViews() {
        btn1_10 = findViewById(R.id.btn_1_10);
        btn11_20 = findViewById(R.id.btn_11_20);
        btn21_30 = findViewById(R.id.btn_21_30);
        btn31_40 = findViewById(R.id.btn_31_40);
        btn41_50 = findViewById(R.id.btn_41_50);
        btn51_60 = findViewById(R.id.btn_51_60);
        btn61_70 = findViewById(R.id.btn_61_70);
        btn71_80 = findViewById(R.id.btn_71_80);
        btn81_90 = findViewById(R.id.btn_81_90);
        btn91_100 = findViewById(R.id.btn_91_100);
        topBar = findViewById(R.id.tb_tca_top_bar);
        tvHeader = findViewById(R.id.tv_header);
        ivBack = findViewById(R.id.iv_back);
    }

    void nextActivity(int range) {
        Log.d("TAG", "nextActivity: " + getCount());
        setRange(range);
        if (getCount() % 3 == 0) {
            setCount(1);
            if (UnityAds.isInitialized()) {
                UnityAds.show(this, Constants.interstitialPlacement, showListener);
            } else {
                Intent intent = new Intent(TableRangeActivity.this, MainActivity.class);
                intent.putExtra("range", getRange());
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(TableRangeActivity.this, MainActivity.class);
            intent.putExtra("range", getRange());
            startActivity(intent);
            setCount(getCount() + 1);
        }
    }

    public IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
        @Override
        public void onUnityAdsAdLoaded(String s) {
            Log.d("TAG", "onUnityAdsAdLoaded: ");
        }

        @Override
        public void onUnityAdsFailedToLoad(String s, UnityAds.UnityAdsLoadError unityAdsLoadError, String s1) {
            Log.d("TAG", "onUnityAdsFailedToLoad: " + s);
            Log.d("TAG", "onUnityAdsFailedToLoad: " + unityAdsLoadError.toString());
            Log.d("TAG", "onUnityAdsFailedToLoad: " + s1);
        }
    };

    public IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
        @Override
        public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
            Log.d("TAG", "onUnityAdsShowFailure: " + s);
            Log.d("TAG", "onUnityAdsShowFailure: " + unityAdsShowError.toString());
            Log.d("TAG", "onUnityAdsShowFailure: " + s1);
        }

        @Override
        public void onUnityAdsShowStart(String s) {
            Log.d("TAG", "onUnityAdsShowStart: ");
        }

        @Override
        public void onUnityAdsShowClick(String s) {
            Log.d("TAG", "onUnityAdsShowClick: ");
        }

        @Override
        public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
            Log.d("TAG", "onUnityAdsShowComplete: ");
            Intent intent = new Intent(TableRangeActivity.this, MainActivity.class);
            intent.putExtra("range", getRange());
            startActivity(intent);
        }
    };

    void setRange(int range) {
        this.range = range;
    }

    int getRange() {
        return range;
    }

    @Override
    public void onInitializationComplete() {
        Log.d("TAG", "onInitializationComplete: ");
        DisplayInterstitialAd();
    }

    public static class UnityInterstitialListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(String s) {
            Log.d("TAG", "onUnityAdsReady: ");
        }

        @Override
        public void onUnityAdsStart(String s) {
            Log.d("TAG", "onUnityAdsStart: ");
        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            Log.d("TAG", "onUnityAdsFinish: ");
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            Log.d("TAG", "onUnityAdsError: " + unityAdsError.toString());
        }
    }

    private void DisplayInterstitialAd() {
        UnityAds.load(Constants.interstitialPlacement, loadListener);
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
        Log.d("TAG", "onInitializationFailed: " + unityAdsInitializationError.toString());
    }
}