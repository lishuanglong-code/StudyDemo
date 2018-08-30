package study.demo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import study.demo.App;
import study.demo.R;
import study.demo.annotation.PermissionDenied;
import study.demo.annotation.PermissionGranted;
import study.demo.annotation.ShowRequestPermissionRationale;
import study.demo.utils.PermissionUtils;

/**
 * 第一步，添加类注解
 */
public class PermissionActivity extends AppCompatActivity {

    private final static String TAG = "Permission_Activity";

    private String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS};
    private final int requestCode = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        findViewById(R.id.btn_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PermissionActivity.this,PermissionChildActivity.class));
            }
        });
        /**
         * 第二步请求
         * */
//        PermissionUtils.requestPermissions(PermissionActivity.this, requestCode, permission);


        /*fragment 使用*/
//        PermissionFragment permissionFragment = new PermissionFragment();
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fl_context,permissionFragment);
//        fragmentTransaction.commit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG,"PermissionActivity --> onRequestPermissionsResult");

        /**
         * 第三步接收
         * */
//        PermissionUtils.onRequestPermissionsResult(PermissionActivity.this, requestCode, permissions, grantResults);
    }


    /**
     * 请求权限成功
     */
    @PermissionGranted(requestCode)
    public void permissionGranted(List<String> grantedResult) {
        Log.d(TAG, "permissionGranted...");
        for (String s : grantedResult) {
            Log.d(TAG, "permissionGranted..." + s);
        }
    }

    /**
     * 请求权限失败
     */
    @PermissionDenied(requestCode)
    public void permissionDenied(List<String> deniedResult) {
        Log.d(TAG, "permissionDenied...");
        for (String s : deniedResult) {
            Log.d(TAG, "permissionDenied..." + s);
        }
    }

    /**
     * 需要解释的权限
     */
    @ShowRequestPermissionRationale(requestCode)
    public void showRequestPermissionRationale(List<String> showRationaleResult) {
        Log.d(TAG, "ShowRequestPermissionRationale...");

        for (String s : showRationaleResult) {
            Log.d(TAG, "ShowRequestPermissionRationale..." + s);
        }
        Toast.makeText(App.getContext(), "showRequestPermissionRationale...", Toast.LENGTH_SHORT).show();
    }

}
