package study.demo.view.event.dispatch;

import android.view.MotionEvent;

import study.demo.utils.L;

public class MotionEventUtil {
    public static final String TAG = "Motion_Event_Util";

    public static void showEventLog(MotionEvent motionEvent, String mag) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                L.d(TAG, mag);
                break;
        }
    }
}
