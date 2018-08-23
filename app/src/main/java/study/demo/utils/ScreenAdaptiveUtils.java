package study.demo.utils;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import study.demo.App;

/**
 * 根据 今日头条技术团队 分享的android适配方案封装
 *
 * @author jasonl 2018/8/23
 */
public class ScreenAdaptiveUtils {

    private static float sNoncompatDensity = 0.0F;
    private static float sNoncompatScaledDensity = 0.0F;

    /**
     * 根据设备的高适配
     */
    public static void screenAdaptiveHeight(@NonNull Context activity) {
        setCustomDensity(activity, true);
    }

    /**
     * 根据设备的宽适配
     */
    public static void screenAdaptiveWidth(@NonNull Context activity) {
        setCustomDensity(activity, false);
    }

    /**
     * 关闭，取消屏幕适配
     */
    private static void cancelAdaptive(@NonNull Context activity) {
        closeAdaptive(activity);
    }

    /**
     * 以设计图纸宽为360dp，高640dp 密度为3为基准
     * 修改系统的 Density（密度、比例）、dpi、scaledDensity
     */
    private static void setCustomDensity(@NonNull Context activity, boolean isOrientation) {
        DisplayMetrics applicationDisplayMetrics = App.getContext().getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0) {
            sNoncompatDensity = applicationDisplayMetrics.density;
            sNoncompatScaledDensity = applicationDisplayMetrics.scaledDensity;
            App.getContext().registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = App.getContext().getResources().getDisplayMetrics().scaledDensity;
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

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * 关闭适配，恢复之前的 dpi、Density、scaledDensity
     */
    private static void closeAdaptive(@NonNull Context activity) {
        DisplayMetrics ResourcesDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics applicationDisplayMetrics = App.getContext().getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        applicationDisplayMetrics.scaledDensity = ResourcesDisplayMetrics.scaledDensity;
        applicationDisplayMetrics.density = ResourcesDisplayMetrics.density;
        applicationDisplayMetrics.densityDpi = ResourcesDisplayMetrics.densityDpi;

        activityDisplayMetrics.scaledDensity = ResourcesDisplayMetrics.scaledDensity;
        activityDisplayMetrics.density = ResourcesDisplayMetrics.density;
        activityDisplayMetrics.densityDpi = ResourcesDisplayMetrics.densityDpi;
    }
}
