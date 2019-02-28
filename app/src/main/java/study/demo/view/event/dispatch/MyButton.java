package study.demo.view.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        MotionEventUtil.showEventLog(event,"View 的 dispatchTouchEvent 被调用！");

        return super.dispatchTouchEvent(event);
//                return true;
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        MotionEventUtil.showEventLog(event,"View 的 onTouchEvent 被调用！");

//        return super.onTouchEvent(event);
//                return true;
        return false;
    }

}
