package study.demo.utils;

import android.util.Log;

public class L {

    public static boolean flag = true;

    public static void d(String TAG, String context) {
        if (flag) {
            Log.d(TAG, context);
        }
    }

}
