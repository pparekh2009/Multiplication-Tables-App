package com.priyanshparekh.multiplicationtables.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.priyanshparekh.multiplicationtables.activity.TableRangeActivity;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class AdManager {

    String unityGameId = "4992527";
    boolean testMode = true;
    boolean enableLoad = true;
//    String bannerPlacement = "Banner_Android";
    String bannerPlacement = "banner";
    String interstitialPlacement = "Interstitial_Android";

    Activity activity;

    UnityBannerListener bannerListener = new UnityBannerListener();

    BannerView bottomBanner;

    public AdManager(Activity activity) {
        this.activity = activity;
    }

    public BannerView initBanner() {
        UnityAds.initialize(activity, unityGameId, null, testMode, enableLoad);

        bottomBanner = new BannerView(activity, bannerPlacement, new UnityBannerSize(320, 50));
        bottomBanner.setListener(bannerListener);
        return bottomBanner;
    }

    private static class UnityBannerListener implements BannerView.IListener {

        @Override
        public void onBannerLoaded(BannerView bannerView) {
            Log.d("TAG", "onBannerLoaded: ");
        }

        @Override
        public void onBannerClick(BannerView bannerView) {
            Log.d("TAG", "onBannerClick: ");
        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
            Log.d("TAG", "onBannerFailedToLoad: error: " + bannerErrorInfo.errorMessage);
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerView) {
            Log.d("TAG", "onBannerLeftApplication: ");
        }
    }
}
