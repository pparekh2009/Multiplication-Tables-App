package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.HelperResizer;

public class HomeActivity extends AppCompatActivity {

    Button learnBtn, quizBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        learnBtn = findViewById(R.id.learn_btn);
        quizBtn = findViewById(R.id.quiz_btn);
        settingsBtn = findViewById(R.id.settings_btn);

        resize();

        // Navigates to multiplication tables page
        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TableRangeActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to quiz page
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuizLevelActivity.class);
                startActivity(intent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void resize() {
        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(learnBtn, 500, 150);
        HelperResizer.setSize(quizBtn, 500, 150);
        HelperResizer.setSize(settingsBtn, 500, 150);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(HomeActivity.this, ExitActivity.class);
        startActivity(intent);
    }
}