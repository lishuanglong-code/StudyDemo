package study.demo.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DeviceActivity extends AppCompatActivity {

    public static final String TAG = "Device_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        Log.d(TAG,"ANDROID_ID01 --> " + ANDROID_ID);

        String string = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d(TAG,"ANDROID_ID02 --> " + ANDROID_ID);
    }



}
