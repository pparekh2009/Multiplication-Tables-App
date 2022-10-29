package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.adapter.TableAdapter;
import com.priyanshparekh.multiplicationtables.helper.AdManager;
import com.unity3d.services.banners.BannerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView header;
    TextView table_count;
    RecyclerView listView;
    SeekBar seekBar;
    TextView textView2;
//    ArrayAdapter<String> arrayAdapter;
    TableAdapter adapter;

    AdManager adManager;
    RelativeLayout bannerContainer;
    BannerView bannerView;

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

        bannerContainer = findViewById(R.id.ma_banner_container);
        adManager = new AdManager(this);
        bannerView = adManager.initBanner();
        bannerView.load();
        bannerContainer.addView(bannerView);

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

        adapter = new TableAdapter(this, mulTable);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listView.setHasFixedSize(true);
//        arrayAdapter = new ArrayAdapter<>(this, R.layout.item_list, mulTable);
        table_count.setText("Table of " + table);
        table_count.setBackground(getDrawable(R.drawable.table_bg));
    }
}