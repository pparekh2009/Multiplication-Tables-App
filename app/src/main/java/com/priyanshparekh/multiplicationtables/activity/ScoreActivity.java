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
import android.widget.EditText;
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
}