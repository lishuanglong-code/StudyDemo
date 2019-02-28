package study.demo.view.event.dispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import study.demo.R;
import study.demo.utils.L;

/**
 * android 事件分发流程练习
 */
public class EventDispatch extends AppCompatActivity {

    public static final String TAG = "Event_Dispatch";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_dispatch);

        findViewById(R.id.btn_test_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("我是测试按钮01");
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        MotionEventUtil.showEventLog(ev,"activity 的 dispatchTouchEvent 被调用！");

        return super.dispatchTouchEvent(ev);
//        return true;
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        MotionEventUtil.showEventLog(event,"activity 的 onTouchEvent 被调用！");

        return super.onTouchEvent(event);
//        return true;
//        return false;
    }
}
