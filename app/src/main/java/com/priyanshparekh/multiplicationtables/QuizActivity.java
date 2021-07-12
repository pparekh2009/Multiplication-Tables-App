package com.priyanshparekh.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.priyanshparekh.multiplicationtables.databinding.ActivityQuizBinding;

import java.util.Random;

public class QuizActivity extends AppCompatActivity{

    private ActivityQuizBinding activityQuizBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityQuizBinding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(activityQuizBinding.getRoot());

        Button[] btn_array = new Button[]{activityQuizBinding.option1, activityQuizBinding.option2, activityQuizBinding.option3, activityQuizBinding.option4};

        activityQuizBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int btn = random.nextInt(btn_array.length);

                activityQuizBinding.tvNum1.setText(String.valueOf((int)Math.floor(Math.random()*(20))+1));
                activityQuizBinding.tvNum2.setText(String.valueOf((int)Math.floor(Math.random()*(10))+1));

                int num1 = Integer.parseInt(activityQuizBinding.tvNum1.getText().toString());
                int num2 = Integer.parseInt(activityQuizBinding.tvNum2.getText().toString());

                btn_array[btn].setText(String.valueOf(num1 * num2));

                btn_array[(btn+1)%4].setText(String.valueOf((num1 + 1) * num2));
                btn_array[(btn+2)%4].setText(String.valueOf((num1 + 1) * (num2 - 1)));
                btn_array[(btn+3)%4].setText(String.valueOf((num1 + 1) * (num2 + 2)));

                btn_array[0].setBackgroundResource(R.drawable.btn_bg);
                btn_array[1].setBackgroundResource(R.drawable.btn_bg);
                btn_array[2].setBackgroundResource(R.drawable.btn_bg);
                btn_array[3].setBackgroundResource(R.drawable.btn_bg);

                enableButton();
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

        if (checkAnswer(btn)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            btn.setBackgroundResource(R.drawable.correct_btn_bg);
        }
        else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
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

    public void disableButton() {
        activityQuizBinding.option1.setEnabled(false);
        activityQuizBinding.option2.setEnabled(false);
        activityQuizBinding.option3.setEnabled(false);
        activityQuizBinding.option4.setEnabled(false);
    }

    public void enableButton() {
        activityQuizBinding.option1.setEnabled(true);
        activityQuizBinding.option2.setEnabled(true);
        activityQuizBinding.option3.setEnabled(true);
        activityQuizBinding.option4.setEnabled(true);
    }
}