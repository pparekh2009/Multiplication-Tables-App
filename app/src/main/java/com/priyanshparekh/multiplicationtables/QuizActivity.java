package com.priyanshparekh.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.priyanshparekh.multiplicationtables.databinding.ActivityQuizBinding;

import java.util.Random;

public class QuizActivity extends AppCompatActivity{

    private ActivityQuizBinding activityQuizBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityQuizBinding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(activityQuizBinding.getRoot());

        Button[] btn_array = new Button[]{activityQuizBinding.option1, activityQuizBinding.option2, activityQuizBinding.option3, activityQuizBinding.option4};

        activityQuizBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int btn = random.nextInt(btn_array.length);

                // Set numbers in question from 1 to 20
                activityQuizBinding.tvNum1.setText(String.valueOf((int)Math.floor(Math.random()*(20))+1));
                activityQuizBinding.tvNum2.setText(String.valueOf((int)Math.floor(Math.random()*(10))+1));


                int num1 = Integer.parseInt(activityQuizBinding.tvNum1.getText().toString());
                int num2 = Integer.parseInt(activityQuizBinding.tvNum2.getText().toString());

                btn_array[btn].setText(String.valueOf(num1 * num2));

                // Set options
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
        activityQuizBinding.quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityQuizBinding.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(activityQuizBinding.option1);
                disableButton();
            }
        });

        activityQuizBinding.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(activityQuizBinding.option2);
                disableButton();
            }
        });

        activityQuizBinding.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(activityQuizBinding.option3);
                disableButton();
            }
        });

        activityQuizBinding.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(activityQuizBinding.option4);
                disableButton();
            }
        });
    }

    public void checkAns(Button btn) {

        LayoutInflater inflater = getLayoutInflater();
        View layout1 = inflater.inflate(R.layout.correct_ans_toast, findViewById(R.id.correct_ans_toast));
        View layout2 = inflater.inflate(R.layout.wrong_ans_toast, findViewById(R.id.wrong_ans_toast));

        if (checkAnswer(btn)) {
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout1);
            toast.show();
            btn.setBackgroundResource(R.drawable.correct_btn_bg);
        }
        else {
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(  layout2);
            toast.show();
            btn.setBackgroundResource(R.drawable.wrong_btn_bg);
        }
        int score = Integer.parseInt(activityQuizBinding.tvScore.getText().toString());
        activityQuizBinding.tvScore.setText(String.valueOf(setScore(btn, score)));
    }

    public boolean checkAnswer(Button btn) {
        int ans = Integer.parseInt(btn.getText().toString());
        int num1 = Integer.parseInt(activityQuizBinding.tvNum1.getText().toString());
        int num2 = Integer.parseInt(activityQuizBinding.tvNum2.getText().toString());

        return (ans == (num1 * num2));
    }

    public int setScore(Button btn, int score) {

        if (checkAnswer(btn)) {
            score = score + 2;
        }
        else {
            score = score - 1;
        }
        return score;
    }

    // Function for disabling buttons after clicking on buttons
    public void disableButton() {
        activityQuizBinding.option1.setEnabled(false);
        activityQuizBinding.option2.setEnabled(false);
        activityQuizBinding.option3.setEnabled(false);
        activityQuizBinding.option4.setEnabled(false);
    }

    // Function for enabling buttons on next question
    public void enableButton() {
        activityQuizBinding.option1.setEnabled(true);
        activityQuizBinding.option2.setEnabled(true);
        activityQuizBinding.option3.setEnabled(true);
        activityQuizBinding.option4.setEnabled(true);
    }
}