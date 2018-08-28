package study.demo.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import study.demo.annotation.PermissionDenied;
import study.demo.annotation.PermissionGranted;
import study.demo.annotation.ShowRequestPermissionRationale;


/**
 * 关于运行时权限的封装
 *
 * @author shuanglong
 * 2018/8/24
 */
public class PermissionUtils {

    private static final String TAG = "Permission_Utils";
    private static int sRequestCode = -1;
    private static final String PERMISSION_GRANTED = "PERMISSION_GRANTED";
    private static final String PERMISSION_DENIED = "PERMISSION_DENIED";
    private static final String SHOW_REQUEST_PERMISSION_RATIONALE = "SHOW_REQUEST_PERMISSION_RATIONALE";

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(final Activity activity, int requestCode, String[] permissions) {
        requestPermissions(activity, activity, requestCode, permissions);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(final Object obj, final Activity context, int requestCode, String[] permissions) {
        //获取请求码
        sRequestCode = requestCode;
        if (permissions != null) {
            List<String> deniedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    continue;
                } else {
                    deniedPermissions.add(permission);
                }
            }
            if (deniedPermissions.size() > 0) {
                List<String> showRequestPermissionRationale = new ArrayList<>();
                List<String> noShowRequestPermissionRationale = new ArrayList<>();
                for (String denied : deniedPermissions) {
                    boolean isShouldShow = ActivityCompat.shouldShowRequestPermissionRationale(context, denied);
                    if (isShouldShow) {
                        showRequestPermissionRationale.add(denied);
                    } else {
                        noShowRequestPermissionRationale.add(denied);
                    }
                }
                if (showRequestPermissionRationale.size() > 0) {
                    invokePermissionMethod(obj, SHOW_REQUEST_PERMISSION_RATIONALE, showRequestPermissionRationale);
                }
                if (noShowRequestPermissionRationale.size() > 0) {
                    ActivityCompat.requestPermissions(context,
                            noShowRequestPermissionRationale.toArray(new String[noShowRequestPermissionRationale.size()]),
                            sRequestCode);
                }
            } else {
                invokePermissionMethod(obj, PERMISSION_GRANTED, Arrays.asList(permissions));
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void onRequestPermissionsResult(Object obj, int requestCode, String[] permissions, int[] grantResults) {
        List<String> grantResult = new ArrayList<>();
        List<String> deniedResult = new ArrayList<>();
        if (sRequestCode == requestCode) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    grantResult.add(permissions[i]);
                } else {
                    deniedResult.add(permissions[i]);
                }
            }
        }
        if (grantResult.size() > 0) {
            invokePermissionMethod(obj, PERMISSION_GRANTED, grantResult);
        }
        if (deniedResult.size() > 0) {
            invokePermissionMethod(obj, PERMISSION_DENIED, deniedResult);
        }
    }

    /**
     * 通过反射调用带有注解类型的方法。
     * 如果带有注解类型方法的requestCode不等于 requestPermissions（Activity activity, final int requestCode）
     * 传入的requestCode，则无法成功调用方法。
     */
    private static void invokePermissionMethod(Object objs, String flag, List<String> result) {
        int requestCode = -1;
        boolean permissionGranted = false;
        Class<?> aClass = objs.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            switch (flag) {
                case PERMISSION_GRANTED:
                    permissionGranted = method.isAnnotationPresent(PermissionGranted.class);
                    break;
                case PERMISSION_DENIED:
                    permissionGranted = method.isAnnotationPresent(PermissionDenied.class);
                    break;
                case SHOW_REQUEST_PERMISSION_RATIONALE:
                    permissionGranted = method.isAnnotationPresent(ShowRequestPermissionRationale.class);
                    break;

            }
            if (permissionGranted) {
                switch (flag) {
                    case PERMISSION_GRANTED:
                        PermissionGranted annotation1 = method.getAnnotation(PermissionGranted.class);
                        requestCode = annotation1.value();
                        break;
                    case PERMISSION_DENIED:
                        PermissionDenied annotation2 = method.getAnnotation(PermissionDenied.class);
                        requestCode = annotation2.value();
                        break;
                    case SHOW_REQUEST_PERMISSION_RATIONALE:
                        ShowRequestPermissionRationale annotation3 = method.getAnnotation(ShowRequestPermissionRationale.class);
                        requestCode = annotation3.value();
                        break;

                }
                if (sRequestCode == requestCode) {
                    try {
                        Object obj = aClass.newInstance();
                        Method method1 = aClass.getMethod(method.getName(), List.class);
                        method1.invoke(obj, result);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        break;
                    }
                }
            }
        }
    }
}
