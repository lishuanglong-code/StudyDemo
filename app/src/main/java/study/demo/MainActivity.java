package study.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;

import study.demo.sp.SPUtils;

public class MainActivity extends AppCompatActivity {

    public static final String SP_USERNAME_KEY = "SP_USERNAME_KEY";
    public static final String SP_PASSWORD_KEY = "SP_PASSWORD_KEY";
    public static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.putValue(MainActivity.this, SP_USERNAME_KEY, "哈哈");
                SPUtils.putValue(MainActivity.this, SP_PASSWORD_KEY, 123456);
            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.首次正常获取
                Map<String, ?> allSP = SPUtils.getAllSP(MainActivity.this);
                int size = allSP.size();
                Log.d(TAG, "1.getAllSP.size() --> " + size);

                String username = (String) SPUtils.getValue(MainActivity.this, SP_USERNAME_KEY, "");
                int password = (int) SPUtils.getValue(MainActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "1.username --> " + username + " ,1.password --> " + password);

                //2.删除 SP_USERNAME_KEY 后，打印sp数量，以及数据情况
                SPUtils.removeSP(MainActivity.this, SP_USERNAME_KEY);

                Map<String, ?> allSP2 = SPUtils.getAllSP(MainActivity.this);
                int size2 = allSP2.size();
                Log.d(TAG, "2.getAllSP.size() --> " + size2);

                String username2 = (String) SPUtils.getValue(MainActivity.this, SP_USERNAME_KEY, "");
                int password2 = (int) SPUtils.getValue(MainActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "2.username --> " + username2 + " ,2.password --> " + password2);

                //清空sp后，打印sp数量，以及数据情况
                SPUtils.clearSP(MainActivity.this);

                Map<String, ?> allSP3 = SPUtils.getAllSP(MainActivity.this);
                int size3 = allSP3.size();
                Log.d(TAG, "3.getAllSP.size() --> " + size3);

                String username3 = (String) SPUtils.getValue(MainActivity.this, SP_USERNAME_KEY, "");
                int password3 = (int) SPUtils.getValue(MainActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "3.username --> " + username3 + " ,3.password --> " + password3);

            }
        });
    }
}
