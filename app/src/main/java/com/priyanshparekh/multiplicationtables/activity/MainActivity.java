package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView header;
    TextView table_count;
    ListView listView;
    SeekBar seekBar;
    TextView textView2;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        table_count = findViewById(R.id.table_count);
        listView = findViewById(R.id.listView);
        seekBar = findViewById(R.id.seekBar);

        textView2 = findViewById(R.id.textView2);

        int range = getIntent().getIntExtra("range", 0);

        // Limit for number of tables
        seekBar.setMax(9);
        populate(range + 1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                populate(progress + range + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // Populating list of table upto 10
    public void populate(int table) {
        ArrayList<String> mulTable = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            mulTable.add(table + " X " + i + " = " + table*i);
        }

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item_list, mulTable);
        listView.setAdapter(arrayAdapter);
        table_count.setText("Table of " + table);
        table_count.setBackground(getDrawable(R.drawable.table_bg));
    }
}