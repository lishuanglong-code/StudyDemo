package study.demo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import study.demo.App;
import study.demo.R;
import study.demo.utils.ScreenUtils;

public class ScreenAdaptiveAvtivity extends AppCompatActivity {

    public static final String TAG = "Screen_Adaptive_Avtivity";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //改变系统密度
        ScreenUtils.ScreenAdaptiveWidth(ScreenAdaptiveAvtivity.this, App.getContext());
        setContentView(R.layout.avtivity_screen_adaptive);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        Log.d(TAG, "密度density --> " + displayMetrics.density);
        Log.d(TAG, "每英寸像素密度densityDpi --> " + displayMetrics.densityDpi);
        Log.d(TAG, "字体的缩放因子，正常情况下和density相等，但是调节系统字体大小后会改变这个值 -->");
        Log.d(TAG, "字体的缩放因子scaledDensity --> " + displayMetrics.scaledDensity);
        Log.d(TAG, "设备分辨率高heightPixels --> " + displayMetrics.heightPixels);
        Log.d(TAG, "设备分辨率宽widthPixels --> " + displayMetrics.widthPixels);
        Log.d(TAG, "每英寸x轴像素密度xdpi --> " + displayMetrics.xdpi);
        Log.d(TAG, "每英寸y轴像素密度ydpi --> " + displayMetrics.ydpi);
    }





}
