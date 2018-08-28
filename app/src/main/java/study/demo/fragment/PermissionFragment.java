package study.demo.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import study.demo.App;
import study.demo.annotation.PermissionDenied;
import study.demo.annotation.PermissionGranted;
import study.demo.annotation.ShowRequestPermissionRationale;
import study.demo.utils.PermissionUtils;


public class PermissionFragment extends Fragment {

    private final static String TAG = "Permission_Fragment";
    private String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS};
    private final int requestCode = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        PermissionUtils.requestPermissions(PermissionFragment.this, getActivity(), requestCode, permission);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /**
         * 第三步接收
         * */
        PermissionUtils.onRequestPermissionsResult(PermissionFragment.this, requestCode, permissions, grantResults);
    }


    /**
     * 请求权限成功
     */
    @PermissionGranted(100)
    public void permissionGranted(List<String> grantedResult) {
        Log.d(TAG, "permissionGranted...");
        for (String s : grantedResult) {
            Log.d(TAG, "permissionGranted..." + s);
        }
    }

    /**
     * 请求权限失败
     */
    @PermissionDenied(100)
    public void permissionDenied(List<String> deniedResult) {
        Log.d(TAG, "permissionDenied...");
        for (String s : deniedResult) {
            Log.d(TAG, "permissionDenied..." + s);
        }
    }

    /**
     * 需要解释的权限
     */
    @ShowRequestPermissionRationale(100)
    public void showRequestPermissionRationale(List<String> showRationaleResult) {
        Log.d(TAG, "ShowRequestPermissionRationale...");

        for (String s : showRationaleResult) {
            Log.d(TAG, "ShowRequestPermissionRationale..." + s);
        }
        Toast.makeText(App.getContext(), "showRequestPermissionRationale...", Toast.LENGTH_SHORT).show();
    }

}
