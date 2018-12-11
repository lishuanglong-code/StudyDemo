package study.demo.activity.jetpack.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.blankj.utilcode.util.LogUtils;


public class MyLifecycle implements LifecycleObserver {

    public static final String TAG = "MY_LIFECYCLE";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        LogUtils.d(TAG,"MyLifecycle --> onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        LogUtils.d(TAG,"MyLifecycle --> onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        LogUtils.d(TAG,"MyLifecycle --> onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        LogUtils.d(TAG,"MyLifecycle --> onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        LogUtils.d(TAG,"MyLifecycle --> onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        LogUtils.d(TAG,"MyLifecycle --> onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(){
        LogUtils.d(TAG,"MyLifecycle --> onAny");
    }

}
