package study.demo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PermissionActivity extends AppCompatActivity {

    private final static String TAG = "Permission_Activity";

    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    private final static int requestCode = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int permissionCheck = ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            boolean isShouldShow = ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,
                    Manifest.permission.CAMERA);
            if (isShouldShow) {
                //解释
                ActivityCompat.requestPermissions(PermissionActivity.this, permissions, requestCode);
            } else {
                ActivityCompat.requestPermissions(PermissionActivity.this, permissions, requestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG, "requestCode --> " + requestCode);
        Log.d(TAG, "String[] permissions --> " + permissions.length);
        Log.d(TAG, "int[] grantResults --> " + grantResults.length + " ,grantResults[0]: "
                + grantResults[0] + " ,grantResults[1]: " + grantResults[1]);

    }
}
