package study.demo.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


/**
 * 关于运行时权限的封装
 *
 * @author shuanglong
 * 2018/8/24
 */
public class PermissionUtils {

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(final @NonNull Activity activity, final @NonNull String permissions,
                                          final @IntRange(from = 0) int requestCode) {

        int permissionCheck = ContextCompat.checkSelfPermission(activity, permissions);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            boolean isShouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions);
            String[] permissionsArray = {permissions};
            if (isShouldShow) {

            } else {

                ActivityCompat.requestPermissions(activity, permissionsArray, requestCode);
            }

        } else {
            //调用注解的方法
            Class aClass = activity.getClass();
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods){
                if (method.isAnnotationPresent(PermissionGranted.class)){
                }
                if (method.isAnnotationPresent(PermissionDenied.class)){

                }

            }




        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


    }


    /**
     * 自定义权限申请通过的方法
     */
    @Target({ElementType.METHOD})
    public @interface PermissionGranted {


    }

    /**
     * 自定义权限申请拒绝的方法
     */
    @Target({ElementType.METHOD})
    public @interface PermissionDenied {

    }


}
