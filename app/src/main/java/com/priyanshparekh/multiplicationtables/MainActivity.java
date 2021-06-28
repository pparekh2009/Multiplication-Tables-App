package com.priyanshparekh.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView header;
    TextView table_count;
    TextView instruction;
    ListView listView;
    SeekBar seekBar;

    TextView textView2;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        table_count = findViewById(R.id.table_count);
        listView = findViewById(R.id.listView);
        seekBar = findViewById(R.id.seekBar);
        instruction = findViewById(R.id.instruction);

        textView2 = findViewById(R.id.textView2);
        adView = findViewById(R.id.bannerAd);

        AdView adView1 = new AdView(this);
        adView1.setAdSize(AdSize.BANNER);
        adView1.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        seekBar.setMax(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                instruction.setVisibility(View.GONE);
                populate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void populate(int table) {
        ArrayList<String> mulTable = new ArrayList<>();

        for(int i=1;i<11;i++) {
            mulTable.add(table + " X " + i + " = " + table*i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_list, mulTable);
        listView.setAdapter(arrayAdapter);
        table_count.setText("Table of " + table);
        table_count.setBackground(getDrawable(R.drawable.table_bg));
    }
}