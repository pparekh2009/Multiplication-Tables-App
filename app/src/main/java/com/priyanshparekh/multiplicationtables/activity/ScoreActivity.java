package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;

public class ScoreActivity extends AppCompatActivity {

    TextView tvScore, tvHighScore;
    Button btnPlayAgain, btnExit;
    ImageView ivTrophy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);

        initViews();

        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(ScoreActivity.this, QuizLevelActivity.class);
            startActivity(intent);
            finish();
        });

        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initViews() {
        tvScore = findViewById(R.id.tv_sa_score);
        tvHighScore = findViewById(R.id.tv_sa_high_score);
        btnPlayAgain = findViewById(R.id.btn_sa_play_again);
        btnExit = findViewById(R.id.btn_sa_exit);
        ivTrophy = findViewById(R.id.iv_sa_trophy);
    }
}