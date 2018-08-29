package adaptive.screen.lsl.apilib;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Constructor;

public class InjectHelper {
    public static final String TAG = "Inject_Helper";

    public static void inject(Activity host) {
        Log.d(TAG,"inject ... ");

        String classFullName = host.getClass().getName() + "$$ViewInjector";
        try {
            Class proxy = Class.forName(classFullName);
            Constructor constructor = proxy.getConstructor(host.getClass());
            constructor.newInstance(host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}