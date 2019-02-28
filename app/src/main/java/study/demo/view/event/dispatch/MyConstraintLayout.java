package study.demo.view.event.dispatch;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyConstraintLayout extends ConstraintLayout {
    public MyConstraintLayout(Context context) {
        this(context,null);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        MotionEventUtil.showEventLog(ev,"ViewGroup 的 dispatchTouchEvent 调用");

        return super.dispatchTouchEvent(ev);
//                return true;
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        MotionEventUtil.showEventLog(ev,"ViewGroup 的 onInterceptTouchEvent 调用");

        return super.onInterceptTouchEvent(ev);
//                return true;
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        MotionEventUtil.showEventLog(event,"ViewGroup 的 onTouchEvent 调用");

        return super.onTouchEvent(event);
//                return true;
//        return false;
    }
}
