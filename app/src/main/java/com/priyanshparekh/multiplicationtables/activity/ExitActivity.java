package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.priyanshparekh.multiplicationtables.R;

public class ExitActivity extends AppCompatActivity {

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        btnExit = findViewById(R.id.btn_exit);

        btnExit.setOnClickListener(v -> {
            showExitDialog();
        });
    }

    private void showExitDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_exit_confirmation, null, false);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.show();

        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Button btnExit = view.findViewById(R.id.btn_exit);

        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnExit.setOnClickListener(v -> {
            dialog.dismiss();
            finishAffinity();
            System.exit(0);
        });
    }
}