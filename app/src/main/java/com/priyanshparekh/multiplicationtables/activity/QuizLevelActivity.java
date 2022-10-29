package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.AdManager;
import com.unity3d.services.banners.BannerView;

public class QuizLevelActivity extends AppCompatActivity {

    Button btnTraining, btnEasy, btnMedium, btnHard, btnInsane;

    AdManager adManager;
    BannerView bannerView;
    RelativeLayout bannerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_level);

        intiViews();

        bannerContainer = findViewById(R.id.qla_banner_container);
        adManager = new AdManager(this);
        bannerView = adManager.initBanner();
        bannerView.load();
        bannerContainer.addView(bannerView);

        btnTraining.setOnClickListener(view -> nextActivity("training"));
        btnEasy.setOnClickListener(view -> nextActivity("easy"));
        btnMedium.setOnClickListener(view -> nextActivity("medium"));
        btnHard.setOnClickListener(view -> nextActivity("hard"));
        btnInsane.setOnClickListener(view -> nextActivity("insane"));
    }

    void nextActivity(String level) {
        Intent intent = new Intent(QuizLevelActivity.this, QuizActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    private void intiViews() {
        btnTraining = findViewById(R.id.btn_training);
        btnEasy = findViewById(R.id.btn_easy);
        btnMedium = findViewById(R.id.btn_medium);
        btnHard = findViewById(R.id.btn_hard);
        btnInsane = findViewById(R.id.btn_insane);
    }
}