package study.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String TAG = "My_Service";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService --> onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MyService --> onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService --> onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService --> onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService --> onDestroy");
    }

    public class MyBinder extends Binder implements IPersonalInif {

        @Override
        public String getName() {
            return "Test Name";
        }
    }

    public interface IPersonalInif {
        String getName();
    }
}
