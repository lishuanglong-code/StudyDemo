package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import study.demo.R;
import study.demo.fragment.TestFragmentLife;

public class TestFragmentLifeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_life);

        //test test test
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_context,new TestFragmentLife(),"TestFragmentLife01");
        fragmentTransaction.commitAllowingStateLoss();


        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fl_context,new TestFragmentLife(),"TestFragmentLife02");
                fragmentTransaction.commitAllowingStateLoss();
            }
        });


    }
}
