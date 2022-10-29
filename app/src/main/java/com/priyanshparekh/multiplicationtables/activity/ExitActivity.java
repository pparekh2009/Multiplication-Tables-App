package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.HelperResizer;

public class ExitActivity extends AppCompatActivity {

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        btnExit = findViewById(R.id.btn_exit);

        btnExit.setOnClickListener(v -> showExitDialog());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ExitActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showExitDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_exit_confirmation, null, false);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Button btnExit = view.findViewById(R.id.btn_quit);

        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(view, 849, 500);

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