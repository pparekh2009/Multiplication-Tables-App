package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.priyanshparekh.multiplicationtables.R;

public class TableRangeActivity extends AppCompatActivity {

    Button btn1_10, btn11_20, btn21_30, btn31_40, btn41_50, btn51_60, btn61_70, btn71_80, btn81_90, btn91_100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_table_category);

        initViews();

        btn1_10.setOnClickListener(view -> nextActivity(0));
        btn11_20.setOnClickListener(view -> nextActivity(10));
        btn21_30.setOnClickListener(view -> nextActivity(20));
        btn31_40.setOnClickListener(view -> nextActivity(30));
        btn41_50.setOnClickListener(view -> nextActivity(40));
        btn51_60.setOnClickListener(view -> nextActivity(50));
        btn61_70.setOnClickListener(view -> nextActivity(60));
        btn71_80.setOnClickListener(view -> nextActivity(70));
        btn81_90.setOnClickListener(view -> nextActivity(80));
        btn91_100.setOnClickListener(view -> nextActivity(90));
    }

    private void initViews() {
        btn1_10 = findViewById(R.id.btn_1_10);
        btn11_20 = findViewById(R.id.btn_11_20);
        btn21_30 = findViewById(R.id.btn_21_30);
        btn31_40 = findViewById(R.id.btn_31_40);
        btn41_50 = findViewById(R.id.btn_41_50);
        btn51_60 = findViewById(R.id.btn_51_60);
        btn61_70 = findViewById(R.id.btn_61_70);
        btn71_80 = findViewById(R.id.btn_71_80);
        btn81_90 = findViewById(R.id.btn_81_90);
        btn91_100 = findViewById(R.id.btn_91_100);
    }

    void nextActivity(int range) {
        Intent intent = new Intent(TableRangeActivity.this, MainActivity.class);
        intent.putExtra("range", range);
        startActivity(intent);
    }
}