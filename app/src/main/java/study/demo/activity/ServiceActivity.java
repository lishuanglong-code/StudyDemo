package study.demo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import study.demo.R;
import study.demo.service.MyService;

public class ServiceActivity extends AppCompatActivity {

    public static final String TAG = "Service_Activity";

    private MyService.IPersonalInif mInfoService;
    private MyServiceConnection mMyServiceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        ((Button) findViewById(R.id.btn_start_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                startService(intent);
            }
        });

        ((Button) findViewById(R.id.btn_end_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });

        ((Button) findViewById(R.id.btn_get_info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"IPersonalInif --> name --> " + mInfoService.getName());
            }
        });

        ((Button) findViewById(R.id.btn_bind_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                mMyServiceConnection = new MyServiceConnection();
                bindService(intent, mMyServiceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        ((Button) findViewById(R.id.btn_unbind_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mMyServiceConnection);
            }
        });
    }


    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mInfoService = (MyService.IPersonalInif) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }
}
