package com.priyanshparekh.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView header;
    TextView table_count;
    TextView instruction;
    ListView listView;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        table_count = findViewById(R.id.table_count);
        listView = findViewById(R.id.listView);
        seekBar = findViewById(R.id.seekBar);
        instruction = findViewById(R.id.instruction);

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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mulTable);
        listView.setAdapter(arrayAdapter);
        table_count.setText("Table of " + table);
        table_count.setBackground(getDrawable(R.drawable.table_bg));
    }
}