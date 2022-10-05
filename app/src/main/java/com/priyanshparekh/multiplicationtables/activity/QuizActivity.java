package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.priyanshparekh.multiplicationtables.R;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button btnNext, btnQuit;
    Button btnOption1, btnOption2, btnOption3, btnOption4;
    TextView tvNum1, tvNum2, tvScore;
    int noOfLives = 3;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        initViews();

        level = getIntent().getStringExtra("level");

        Button[] btn_array = new Button[]{btnOption1, btnOption2, btnOption3, btnOption4};

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int btn = random.nextInt(btn_array.length);

                // Set numbers in question from 1 to 20
                tvNum1.setText(String.valueOf((int)Math.floor(Math.random()*(20))+1));
                tvNum2.setText(String.valueOf((int)Math.floor(Math.random()*(10))+1));

                int num1 = Integer.parseInt(tvNum1.getText().toString());
                int num2 = Integer.parseInt(tvNum2.getText().toString());

                // Set options
                btn_array[btn].setText(String.valueOf(num1 * num2));
                btn_array[(btn+1)%4].setText(String.valueOf((num1 + 1) * num2));
                btn_array[(btn+2)%4].setText(String.valueOf((num1 + 1) * (num2 - 1)));
                btn_array[(btn+3)%4].setText(String.valueOf((num1 + 1) * (num2 + 2)));

                // Set background on options button
                btn_array[0].setBackgroundResource(R.drawable.btn_bg);
                btn_array[1].setBackgroundResource(R.drawable.btn_bg);
                btn_array[2].setBackgroundResource(R.drawable.btn_bg);
                btn_array[3].setBackgroundResource(R.drawable.btn_bg);

                enableButton();
            }
        });

        // Quit current activity and go to home activity
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(btnOption1);
                disableButton();
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(btnOption2);
                disableButton();
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(btnOption3);
                disableButton();
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(btnOption4);
                disableButton();
            }
        });
    }

    private void initViews() {
        btnNext = findViewById(R.id.next_btn);
        btnOption1 = findViewById(R.id.option_1);
        btnOption2 = findViewById(R.id.option_2);
        btnOption3 = findViewById(R.id.option_3);
        btnOption4 = findViewById(R.id.option_4);
        btnQuit = findViewById(R.id.quit_btn);
        tvNum1 = findViewById(R.id.tv_num_2);
        tvNum2 = findViewById(R.id.tv_num_1);
        tvScore = findViewById(R.id.tv_score);
    }

    public void checkAns(Button btn) {

        LayoutInflater inflater = getLayoutInflater();
        View layout1 = inflater.inflate(R.layout.correct_ans_toast, findViewById(R.id.correct_ans_toast));
        View layout2 = inflater.inflate(R.layout.wrong_ans_toast, findViewById(R.id.wrong_ans_toast));

        if (checkAnswer(btn)) {
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout1);
            toast.show();
            btn.setBackgroundResource(R.drawable.correct_btn_bg);
        }
        else {
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout2);
            toast.show();
            btn.setBackgroundResource(R.drawable.wrong_btn_bg);
        }
        int score = Integer.parseInt(tvScore.getText().toString());
        tvScore.setText(String.valueOf(setScore(btn, score)));
    }

    public boolean checkAnswer(Button btn) {
        int ans = Integer.parseInt(btn.getText().toString());
        int num1 = Integer.parseInt(tvNum1.getText().toString());
        int num2 = Integer.parseInt(tvNum2.getText().toString());

        return (ans == (num1 * num2));
    }

    public int setScore(Button btn, int score) {

        if (checkAnswer(btn)) {
            score = score + 2;
        }
        else {
//            score = score - 1;
            noOfLives = noOfLives - 1;
        }
        return score;
    }

    // Function for disabling buttons after clicking on buttons
    public void disableButton() {
        btnOption1.setEnabled(false);
        btnOption2.setEnabled(false);
        btnOption3.setEnabled(false);
        btnOption4.setEnabled(false);
    }

    // Function for enabling buttons on next question
    public void enableButton() {
        btnOption1.setEnabled(true);
        btnOption2.setEnabled(true);
        btnOption3.setEnabled(true);
        btnOption4.setEnabled(true);
    }
}