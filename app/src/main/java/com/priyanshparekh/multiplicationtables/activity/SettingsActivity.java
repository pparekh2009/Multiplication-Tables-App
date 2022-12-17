package com.priyanshparekh.multiplicationtables.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.priyanshparekh.multiplicationtables.R;
import com.priyanshparekh.multiplicationtables.helper.HelperResizer;

public class SettingsActivity extends AppCompatActivity {

    Button privacyPolicyBtn, rateAppBtn, shareAppBtn;
    WebView webView;
    ConstraintLayout topBar;
    TextView tvHeader;
    ImageView ivBack;

    String url = "https://sites.google.com/view/pparekh-privacy-policy-ad/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        privacyPolicyBtn = findViewById(R.id.privacy_policy_btn);
        rateAppBtn = findViewById(R.id.rate_app_btn);
        shareAppBtn = findViewById(R.id.share_app_btn);
        topBar = findViewById(R.id.tb_settings_top_bar);
        tvHeader = findViewById(R.id.tv_header);
        ivBack = findViewById(R.id.iv_back);

        HelperResizer.getheightandwidth(this);
        HelperResizer.setSize(topBar, 1080, 150);
        HelperResizer.setSize(ivBack, 100, 51);
        HelperResizer.setSize(privacyPolicyBtn, 500, 120);
        HelperResizer.setSize(rateAppBtn, 500, 120);
        HelperResizer.setSize(shareAppBtn, 500, 120);

        ivBack.setOnClickListener(view -> onBackPressed());
        tvHeader.setText("Settings");

//        webView = new WebView(this);
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
        webView.setZ(100);

        privacyPolicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.VISIBLE);
            }
        });

        rateAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateApp();
            }
        });

        shareAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.getVisibility() == View.VISIBLE) {
            webView.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    private void rateApp() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    private void shareApp() {
        String appLink = "https://play.google.com/store/apps/details?id=" + getPackageName();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, appLink);
        startActivity(Intent.createChooser(intent, "Share via..."));
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}