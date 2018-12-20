package study.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import study.demo.activity.AnnotationActivity;
import study.demo.activity.CameraActivity;
import study.demo.activity.ConstraintLayoutActivity;
import study.demo.activity.PermissionActivity;
import study.demo.activity.RegexActivity;
import study.demo.activity.RxJavaActivity;
import study.demo.activity.SPActivity;
import study.demo.activity.ScreenAdaptiveAvtivity;
import study.demo.activity.ServiceActivity;
import study.demo.activity.SimpleTestActivity;
import study.demo.activity.TestFragmentLifeActivity;
import study.demo.activity.fragments.Fragment01;
import study.demo.activity.fragments.Fragment02;
import study.demo.activity.fragments.Fragment03;
import study.demo.activity.fragments.Fragment04;
import study.demo.activity.fragments.FragmentFactoryModel;
import study.demo.activity.fragments.FragmentListActivity;
import study.demo.activity.jetpack.lifecycle.LifecycleActivity;
import study.demo.activity.jetpack.livedata.LiveDataActivity;

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
        findViewById(R.id.simpleTestActivity).setOnClickListener(this);
        findViewById(R.id.constraintLayoutActivity).setOnClickListener(this);
        findViewById(R.id.cameraActivity).setOnClickListener(this);
        findViewById(R.id.testFragmentLifeActivity).setOnClickListener(this);
        findViewById(R.id.serviceActivity).setOnClickListener(this);
        findViewById(R.id.regexActivity).setOnClickListener(this);

        /*android 建构组件*/
        findViewById(R.id.lifecyclesActivity).setOnClickListener(this);
        findViewById(R.id.liveDataActivity).setOnClickListener(this);

        /*fragment list activity*/
        findViewById(R.id.fragmentListActivity).setOnClickListener(this);
        sFragmentList = new ArrayList<>();
        sFragmentList.add(new FragmentFactoryModel("01", new Fragment01()));
        sFragmentList.add(new FragmentFactoryModel("02", new Fragment02()));
        sFragmentList.add(new FragmentFactoryModel("03", new Fragment03()));
        sFragmentList.add(new FragmentFactoryModel("04", new Fragment04()));
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
            case R.id.simpleTestActivity:
                startActivity(new Intent(MainActivity.this, SimpleTestActivity.class));
                break;
            case R.id.constraintLayoutActivity:
                startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
                break;
            case R.id.cameraActivity:
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
                break;
            case R.id.testFragmentLifeActivity:
                startActivity(new Intent(MainActivity.this, TestFragmentLifeActivity.class));
                break;
            case R.id.serviceActivity:
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            /*正则表达式*/
            case R.id.regexActivity:
                startActivity(new Intent(MainActivity.this, RegexActivity.class));
                break;
            /*android 建构组件*/
            case R.id.lifecyclesActivity:
                startActivity(new Intent(MainActivity.this, LifecycleActivity.class));
                break;
            case R.id.liveDataActivity:
                startActivity(new Intent(MainActivity.this, LiveDataActivity.class));
                break;
            /*fragment list activity*/
            case R.id.fragmentListActivity:
                startActivity(new Intent(MainActivity.this, FragmentListActivity.class));
                break;
        }
    }

    public static List<FragmentFactoryModel> sFragmentList = null;
}
