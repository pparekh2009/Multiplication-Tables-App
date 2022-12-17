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

//    String unityGameId = "4992527";
//    boolean testMode = false;
    boolean enableLoad = true;
//    String bannerPlacement = "Banner_Android";
//    String bannerPlacement = "banner";
    String interstitialPlacement = "Interstitial_Android";

    Activity activity;

    UnityBannerListener bannerListener;

    BannerView bottomBanner;

    public AdManager(Activity activity) {
        this.activity = activity;
    }

    public BannerView initBanner() {
        bannerListener = new UnityBannerListener(activity);
        UnityAds.initialize(activity, Constants.unityGameId, null, Constants.testMode, enableLoad);

        bottomBanner = new BannerView(activity, Constants.bannerPlacement, new UnityBannerSize(320, 50));
        bottomBanner.setListener(bannerListener);
        return bottomBanner;
    }

    private static class UnityBannerListener implements BannerView.IListener {

        Activity activity;

        UnityBannerListener(Activity activity) {
            this.activity = activity;
        }

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
            while (!UnityAds.isReady(Constants.bannerPlacement)) {
                UnityAds.load(Constants.bannerPlacement);
                Log.d("TAG", "onBannerFailedToLoad: loading banner ads");
            }
            UnityAds.show(activity, Constants.bannerPlacement);
            Log.d("TAG", "onBannerFailedToLoad: error: " + bannerErrorInfo.errorMessage);
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerView) {
            Log.d("TAG", "onBannerLeftApplication: ");
        }
    }
}
