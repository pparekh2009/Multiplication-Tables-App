package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.AdManager;
import com.priyanshparekh.multiplicationtables.helper.Constants;
import com.priyanshparekh.multiplicationtables.helper.HelperResizer;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class QuizActivity extends AppCompatActivity implements IUnityAdsListener {

    Button btnNext, btnQuit;
    Button btnOption1, btnOption2, btnOption3, btnOption4;
    TextView tvNum1, tvNum2, tvScore;
    int noOfLives = 3;
    String level;
    ImageView ivLife1, ivLife2, ivLife3;
    Button[] btn_array;
    LinearLayout optionBtns;

    AdManager adManager;
    BannerView bannerView;
    RelativeLayout bannerContainer;
    boolean continuePressed = false;

    ConstraintLayout topBar;
    TextView tvHeader;
    ImageView ivBack;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        initViews();
        updateLives(noOfLives);

        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(topBar, 1080, 150);
        HelperResizer.setSize(ivBack, 100, 51);

        ivBack.setOnClickListener(view -> onBackPressed());
        tvHeader.setText("Quiz");

        UnityAds.initialize(this, Constants.unityGameId, this, Constants.testMode);

        bannerContainer = findViewById(R.id.qa_banner_container);
        adManager = new AdManager(this);
        bannerView = adManager.initBanner();
        bannerView.load();
        bannerContainer.addView(bannerView);

        level = getIntent().getStringExtra("level");

        btn_array = new Button[]{btnOption1, btnOption2, btnOption3, btnOption4};

        setQuestion(level);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crouton.cancelAllCroutons();
                setQuestion(level);
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

    void setQuestion(String level) {
        Random random = new Random();
        int btn = random.nextInt(btn_array.length);

        int min, max;

        switch (level) {
            case "training":
                min = 0;
                max = 20;
                break;
            case "easy":
                min = 21;
                max = 40;
                break;
            case "medium":
                min = 41;
                max = 60;
                break;
            case "hard":
                min = 61;
                max = 80;
                break;
            case "insane":
                min = 81;
                max = 100;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + level);
        }

        // Set numbers in question from 1 to 20
        tvNum1.setText(String.valueOf(getRandomNumber(min, max)));
        tvNum2.setText(String.valueOf(getRandomNumber(min, max)));

        int num1 = Integer.parseInt(tvNum1.getText().toString());
        int num2 = Integer.parseInt(tvNum2.getText().toString());

        // Set options
        btn_array[btn].setText(String.valueOf(num1 * num2));
        btn_array[(btn+1)%4].setText(String.valueOf((num1 + 1) * num2));
        btn_array[(btn+2)%4].setText(String.valueOf((num1 + 1) * (num2 - 1)));
        btn_array[(btn+3)%4].setText(String.valueOf((num1 + 1) * (num2 + 2)));

        // Set background on options button
        resetOptionBtns();

        enableButton();
    }

    void resetOptionBtns() {
        btn_array[0].setBackgroundResource(R.drawable.btn_bg);
        btn_array[1].setBackgroundResource(R.drawable.btn_bg);
        btn_array[2].setBackgroundResource(R.drawable.btn_bg);
        btn_array[3].setBackgroundResource(R.drawable.btn_bg);
    }

    int getRandomNumber(int min, int max) {
        return (new Random().nextInt((max - min) + 1)) + min;
    }

    private void initViews() {
        optionBtns = findViewById(R.id.option_btns);
        btnNext = findViewById(R.id.next_btn);
        btnOption1 = findViewById(R.id.option_1);
        btnOption2 = findViewById(R.id.option_2);
        btnOption3 = findViewById(R.id.option_3);
        btnOption4 = findViewById(R.id.option_4);
        btnQuit = findViewById(R.id.quit_btn);
        tvNum1 = findViewById(R.id.tv_num_2);
        tvNum2 = findViewById(R.id.tv_num_1);
        tvScore = findViewById(R.id.tv_score);
        ivLife1 = findViewById(R.id.iv_life_1);
        ivLife2 = findViewById(R.id.iv_life_2);
        ivLife3 = findViewById(R.id.iv_life_3);
        topBar = findViewById(R.id.tb_qa_top_bar);
        tvHeader = findViewById(R.id.tv_header);
        ivBack = findViewById(R.id.iv_back);
    }

    public void checkAns(Button btn) {

        LayoutInflater inflater = getLayoutInflater();
        View layout1 = inflater.inflate(R.layout.correct_ans_toast, findViewById(R.id.correct_ans_toast));
        View layout2 = inflater.inflate(R.layout.wrong_ans_toast, findViewById(R.id.wrong_ans_toast));

        if (checkAnswer(btn)) {
            Crouton.makeText(this, "Yay! Correct Answer", Style.CONFIRM, optionBtns).show();
//            Toast toast = new Toast(this);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setView(layout1);
//            toast.show();
            btn.setBackgroundResource(R.drawable.correct_btn_bg);
        }
        else {
            Crouton.makeText(this, "Oops! Wrong Answer", Style.ALERT, optionBtns).show();
//            Toast toast = new Toast(this);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setView(layout2);
//            toast.show();
            btn.setBackgroundResource(R.drawable.wrong_btn_bg);
        }
        score = Integer.parseInt(tvScore.getText().toString());
        tvScore.setText(String.valueOf(setScore(btn)));
    }

    public boolean checkAnswer(Button btn) {
        int ans = Integer.parseInt(btn.getText().toString());
        int num1 = Integer.parseInt(tvNum1.getText().toString());
        int num2 = Integer.parseInt(tvNum2.getText().toString());

        return (ans == (num1 * num2));
    }

    public int setScore(Button btn) {

        if (checkAnswer(btn)) {
            score = score + 2;
        }
        else {
            noOfLives = noOfLives - 1;
            Log.d("TAG", "setScore: noOfLives: " + noOfLives);
            if (noOfLives == 0) {
                resetLivesImg();
                updateLives(noOfLives);
                showContinueDialog(score);
            } else {
                resetLivesImg();
                updateLives(noOfLives);
            }
        }
        return score;
    }

    private void showContinueDialog(int score) {
        final int[] time = {5};

        View view = LayoutInflater.from(this).inflate(R.layout.layout_continue, null, false);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button btnContinue = view.findViewById(R.id.btn_cl_continue);
        Button btnQuit = view.findViewById(R.id.btn_cl_quit);

        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(view, 849, 500);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (time[0] > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnContinue.setText("Continue " + time[0] + "s");
                        }
                    });
                    time[0]--;
                } else if (time[0] == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnContinue.setVisibility(View.GONE);
                        }
                    });
                    cancel();
                }
            }
        }, 0, 1000);

        btnContinue.setOnClickListener(v -> {
            continuePressed = true;
            showRewardedAd();
            dialog.dismiss();
        });

        btnQuit.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        });
    }

    private void showRewardedAd() {
        if (UnityAds.isInitialized()) {
            Log.d("TAG", "showRewardedAd: unity ads initialized");
            if (UnityAds.isReady(Constants.interstitialPlacement)) {
                Log.d("TAG", "showRewardedAd: unity ads ready");
                UnityAds.show(this, Constants.interstitialPlacement);
            } else {
                Log.d("TAG", "showRewardedAd: unity ads not ready");
                ProgressDialog dialog = new ProgressDialog(QuizActivity.this);
                dialog.setTitle("Loading Ads");
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        UnityAds.load(Constants.interstitialPlacement);
                        if (UnityAds.isReady()) {
                            Log.d("TAG", "run: unity ads ready again");
                            UnityAds.show(QuizActivity.this, Constants.interstitialPlacement);
                        } else {
                            Log.d("TAG", "run: unity ads not ready again");
                            continuePressed = false;

                            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                            intent.putExtra("score", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 5000);
            }
        } else {
            Log.d("TAG", "showRewardedAd: unity ads not initialized");
        }
    }

    void showErrorDialog() {
        Log.d("TAG", "showErrorDialog: score: " + score);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_ad_error, null, false);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button btnQuit = view.findViewById(R.id.btn_ael_quit);

        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(view, 849, 500);

        btnQuit.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        });
    }

    public void resetLivesImg() {
        ivLife1.setImageResource(R.drawable.grey_heart);
        ivLife2.setImageResource(R.drawable.grey_heart);
        ivLife3.setImageResource(R.drawable.grey_heart);
    }

    public void updateLives(int noOfLives) {
        Log.d("TAG", "updateLives: noOfLives: " + noOfLives);
        int[] lives = {R.id.iv_life_3, R.id.iv_life_2, R.id.iv_life_1};

        for (int i = 0; i < noOfLives; i++) {
            ((ImageView) findViewById(lives[i])).setImageResource(R.drawable.red_heart);
        }
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
        if (finishState != UnityAds.FinishState.ERROR) {
            Log.d("TAG", "onUnityAdsFinish: COMPLETED");
            resetLivesImg();
            updateLives(3);
            noOfLives = 3;
//        } else if (finishState == UnityAds.FinishState.SKIPPED) {
//            showSkippedDialog();
        }
    }

    private void showSkippedDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_ad_skip_warning, null, false);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.show();
    }


    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
        Log.d("TAG", "onUnityAdsError: " + unityAdsError.toString());
        Log.d("TAG", "onUnityAdsError: continuePressed: " + continuePressed);
        if (continuePressed) {
            Toast.makeText(this, "Error Loading Ad", Toast.LENGTH_SHORT).show();
//            showErrorDialog(Integer.parseInt(tvScore.getText().toString()));
            continuePressed = false;

            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            Log.d("TAG", "onUnityAdsError: score: " + tvScore.getText());
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }
}