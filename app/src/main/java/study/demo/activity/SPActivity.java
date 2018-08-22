package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Map;

import study.demo.R;
import study.demo.utils.SPUtils;

public class SPActivity extends AppCompatActivity {

    public static final String TAG = "SP_Activity";
    public static final String SP_USERNAME_KEY = "SP_USERNAME_KEY";
    public static final String SP_PASSWORD_KEY = "SP_PASSWORD_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        findViewById(R.id.btn_put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.putValue(SPActivity.this, SP_USERNAME_KEY, "哈哈");
                SPUtils.putValue(SPActivity.this, SP_PASSWORD_KEY, 123456);
            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.首次正常获取
                Map<String, ?> allSP = SPUtils.getAllSP(SPActivity.this);
                int size = allSP.size();
                Log.d(TAG, "1.getAllSP.size() --> " + size);

                String username = (String) SPUtils.getValue(SPActivity.this, SP_USERNAME_KEY, "");
                int password = (int) SPUtils.getValue(SPActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "1.username --> " + username + " ,1.password --> " + password);

                //2.删除 SP_USERNAME_KEY 后，打印sp数量，以及数据情况
                SPUtils.removeSP(SPActivity.this, SP_USERNAME_KEY);

                Map<String, ?> allSP2 = SPUtils.getAllSP(SPActivity.this);
                int size2 = allSP2.size();
                Log.d(TAG, "2.getAllSP.size() --> " + size2);

                String username2 = (String) SPUtils.getValue(SPActivity.this, SP_USERNAME_KEY, "");
                int password2 = (int) SPUtils.getValue(SPActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "2.username --> " + username2 + " ,2.password --> " + password2);

                //清空sp后，打印sp数量，以及数据情况
                SPUtils.clearSP(SPActivity.this);

                Map<String, ?> allSP3 = SPUtils.getAllSP(SPActivity.this);
                int size3 = allSP3.size();
                Log.d(TAG, "3.getAllSP.size() --> " + size3);

                String username3 = (String) SPUtils.getValue(SPActivity.this, SP_USERNAME_KEY, "");
                int password3 = (int) SPUtils.getValue(SPActivity.this, SP_PASSWORD_KEY, -1);
                Log.d(TAG, "3.username --> " + username3 + " ,3.password --> " + password3);

            }
        });
    }
}
