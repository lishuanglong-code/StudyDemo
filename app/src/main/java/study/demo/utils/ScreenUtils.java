package study.demo.utils;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class ScreenUtils {

    private static float sNoncompatDensity = 0.0F;
    private static float sNoncompatScaledDensity = 0.0F;


    /**
     * 适配屏幕的高
     */
    public static void screenAdaptiveHeight(@NonNull Context activityContext, @NonNull final Context applicationContext) {
        setCustomDensity(activityContext, applicationContext, true);
    }

    /**
     * 适配屏幕的宽
     */
    public static void ScreenAdaptiveWidth(@NonNull Context activityContext, @NonNull final Context applicationContext) {
        setCustomDensity(activityContext, applicationContext, false);
    }

    /**
     * 以设计图纸宽为360dp，高640dp 密度为3为基准
     * <p>
     * 设置系统的 Density（密度、比例）、dpi、scaledDensity
     */
    private static void setCustomDensity(@NonNull Context activityContext, @NonNull final Context applicationContext
            , boolean isOrientation) {
        DisplayMetrics applicationDisplayMetrics = applicationContext.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = applicationDisplayMetrics.density;
            sNoncompatScaledDensity = applicationDisplayMetrics.scaledDensity;
            applicationContext.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = applicationContext.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity = 0.0F;
        if (isOrientation) {
            targetDensity = (float) applicationDisplayMetrics.heightPixels / 640;
        } else {
            targetDensity = (float) applicationDisplayMetrics.widthPixels / 360;
        }

        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (targetDensity * 160);

        applicationDisplayMetrics.scaledDensity = targetScaledDensity;
        applicationDisplayMetrics.density = targetDensity;
        applicationDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activityContext.getResources().getDisplayMetrics();
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
