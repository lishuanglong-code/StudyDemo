package study.demo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import study.demo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.spAvtivity).setOnClickListener(this);
        findViewById(R.id.screenAdaptiveAvtivity).setOnClickListener(this);
        findViewById(R.id.annotationActivity).setOnClickListener(this);
        findViewById(R.id.permissionActivity).setOnClickListener(this);
        findViewById(R.id.rxjavaActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spAvtivity:
                startActivity(new Intent(MainActivity.this, SPActivity.class));
                break;
            case R.id.screenAdaptiveAvtivity:
                startActivity(new Intent(MainActivity.this, ScreenAdaptiveAvtivity.class));
                break;
            case R.id.annotationActivity:
                startActivity(new Intent(MainActivity.this, AnnotationActivity.class));
                break;
            case R.id.permissionActivity:
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
                break;
            case R.id.rxjavaActivity:
                startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                break;
        }
    }
}
