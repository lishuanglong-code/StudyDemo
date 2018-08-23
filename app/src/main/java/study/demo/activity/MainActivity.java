package study.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import study.demo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.spAvtivity).setOnClickListener(this);
        findViewById(R.id.screenAdaptiveAvtivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spAvtivity:
                startActivity(new Intent(MainActivity.this,SPActivity.class));
                break;
            case R.id.screenAdaptiveAvtivity:
                startActivity(new Intent(MainActivity.this,ScreenAdaptiveAvtivity.class));
                break;
        }
    }
}
