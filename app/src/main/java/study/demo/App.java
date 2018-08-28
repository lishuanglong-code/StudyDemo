package study.demo;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context context = null;
    private static App app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        app = this;
    }

    public static Context getContext(){
        return context;
    }
}
