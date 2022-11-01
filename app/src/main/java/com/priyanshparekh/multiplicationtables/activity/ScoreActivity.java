package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.AdManager;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;

public class ScoreActivity extends AppCompatActivity implements IUnityAdsInitializationListener {

    TextView tvScore, tvHighScore;
    Button btnPlayAgain, btnExit;
    ImageView ivTrophy;
    String buttonPressed;

    AdManager adManager;
    BannerView bannerView;
    RelativeLayout bannerContainer;

    String unityGameId = "4992527";
    boolean testMode = false;
    String interstitialPlacement = "Interstitial_Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);

        initViews();

        bannerContainer = findViewById(R.id.sa_banner_container);
        adManager = new AdManager(this);
        bannerView = adManager.initBanner();
        bannerView.load();
        bannerContainer.addView(bannerView);

        int score = getIntent().getIntExtra("score", 0);
        Log.d("TAG", "onCreate: score: " + score);
        tvScore.setText(String.valueOf(score));

        SharedPreferences preferences = getSharedPreferences("Score", MODE_PRIVATE);
        int highScore = preferences.getInt("highScore", 0);
        Log.d("TAG", "onCreate: highScore: " + highScore);

        SharedPreferences.Editor editor = preferences.edit();
        if (score > highScore) {
            editor.putInt("highScore", score);
            editor.apply();
            ivTrophy.setVisibility(View.VISIBLE);
            scaleView(ivTrophy);
            tvHighScore.setVisibility(View.VISIBLE);
        }

        btnPlayAgain.setOnClickListener(view -> {
            buttonPressed = "playAgain";
            if (getCount("play_again_count") % 3 == 0) {
                setCount("play_again_count", 1);
                if (UnityAds.isInitialized()) {
                    UnityAds.show(this, interstitialPlacement, showListener);
                } else {
                    Intent intent = new Intent(ScoreActivity.this, QuizLevelActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Intent intent = new Intent(ScoreActivity.this, QuizLevelActivity.class);
                startActivity(intent);
                finish();
                setCount("play_again_count", getCount("play_again_count") + 1);
            }
        });

        btnExit.setOnClickListener(view -> {
            buttonPressed = "exit";
            if (getCount("exit_count") % 5 == 0) {
                setCount("exit_count", 1);
                if (UnityAds.isInitialized()) {
                    UnityAds.show(this, interstitialPlacement, showListener);
                } else {
                    Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                setCount("exit_count", getCount("exit_count") + 1);
            }
        });
    }

    private void initViews() {
        tvScore = findViewById(R.id.tv_sa_score);
        tvHighScore = findViewById(R.id.tv_sa_high_score);
        btnPlayAgain = findViewById(R.id.btn_sa_play_again);
        btnExit = findViewById(R.id.btn_sa_exit);
        ivTrophy = findViewById(R.id.iv_sa_trophy);
    }

    public void scaleView(View v) {
        Animation anim = new ScaleAnimation(
                15f, 1f, // Start and end values for the X axis scaling
                15f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        v.startAnimation(anim);
    }

    void setCount(String type, int count) {
        SharedPreferences preferences = getSharedPreferences("AdCount", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(type, count);
        editor.apply();
    }

    int getCount(String type) {
        SharedPreferences preferences = getSharedPreferences("AdCount", MODE_PRIVATE);
        return preferences.getInt(type, 0);
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
            if (buttonPressed.equals("playAgain")) {
                Intent intent = new Intent(ScoreActivity.this, QuizLevelActivity.class);
                startActivity(intent);
                finish();
            } else if (buttonPressed.equals("exit")) {
                Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
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
            if (buttonPressed.equals("playAgain")) {
                Intent intent = new Intent(ScoreActivity.this, QuizLevelActivity.class);
                startActivity(intent);
                finish();
            } else if (buttonPressed.equals("exit")) {
                Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    public void onInitializationComplete() {
        Log.d("TAG", "onInitializationComplete: ");
        DisplayInterstitialAd();
    }

    private void DisplayInterstitialAd() {
        UnityAds.load(interstitialPlacement, loadListener);
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
        Log.d("TAG", "onInitializationFailed: " + unityAdsInitializationError.toString());
    }
}