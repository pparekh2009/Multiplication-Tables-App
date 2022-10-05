package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.priyanshparekh.multiplicationtables.R;

public class QuizLevelActivity extends AppCompatActivity {

    Button btnEasy, btnMedium, btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_level);

        intiViews();

        btnEasy.setOnClickListener(view -> nextActivity("easy"));
        btnMedium.setOnClickListener(view -> nextActivity("medium"));
        btnHard.setOnClickListener(view -> nextActivity("hard"));
    }

    void nextActivity(String level) {
        Intent intent = new Intent(QuizLevelActivity.this, QuizActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    private void intiViews() {
        btnEasy = findViewById(R.id.btn_easy);
        btnMedium = findViewById(R.id.btn_medium);
        btnHard = findViewById(R.id.btn_hard);
    }
}