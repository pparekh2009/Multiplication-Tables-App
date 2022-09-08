package com.priyanshparekh.multiplicationtables;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class HelperResizer {
    public static int height, width;
    public static int SCALE_WIDTH = 1080; // scale width of ui
    public static int SCALE_HEIGHT = 1920; // scale height of ui

    public static void getheightandwidth(Context context) {

        getHeight(context);
        getwidth(context);
    }

    public static int getwidth(Context context) {
        width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static int getHeight(Context context) {
        height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }
    public static void setHeight(Context mContext, View view, int v_height) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int height = dm.heightPixels * v_height / SCALE_HEIGHT;
        view.getLayoutParams().height = height;
    }
    public static void setWidth(Context mContext, View view, int v_Width) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int width  = dm.widthPixels * v_Width / SCALE_WIDTH;
        view.getLayoutParams().width = width ;
    }
    public static int setHeight(int h) {

        return (height * h) / 1920;

    }

    public static int setWidth(int w) {
        return (width * w) / 1080;

    }

    public static void setSize(View view, int width, int height) {

        view.getLayoutParams().height = setHeight(height);
        view.getLayoutParams().width = setWidth(width);

    }
    public static void setHeightByWidth(Context mContext, View view, int v_height) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int height = dm.widthPixels * v_height / SCALE_WIDTH;
        view.getLayoutParams().height = height;
    }
    public static void setSize(View view, int width, int height, boolean sameheightandwidth) {

        if (sameheightandwidth) {
            view.getLayoutParams().height = setWidth(height);
            view.getLayoutParams().width = setWidth(width);
        } else {
            view.getLayoutParams().height = setHeight(height);
            view.getLayoutParams().width = setHeight(width);
        }

    }

    public static void setMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(setWidth(left), setHeight(top), setWidth(right), setHeight(bottom));
    }

    public static void setPadding(View view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
    }


}
