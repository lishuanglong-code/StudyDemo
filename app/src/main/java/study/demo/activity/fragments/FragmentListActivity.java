package study.demo.activity.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import study.demo.MainActivity;
import study.demo.R;

public class FragmentListActivity extends AppCompatActivity {


    public int index = 0;
    private TextView mTitle;
    private FragmentManager mManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_list);

        mManager = getSupportFragmentManager();


        mTitle = findViewById(R.id.tv_title);


        Button last = findViewById(R.id.btn_last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index - 1;

                if (index >= 0) {
                    setLast(index);
                } else {
                    index = 0;
                }
            }
        });

        Button next = findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index + 1;
                if (index < MainActivity.sFragmentList.size()) {
                    setNext(index);
                } else {
                    index = MainActivity.sFragmentList.size() - 1;
                }

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setNext(index);
            }
        },1);


    }


    private void setNext(int index) {
        int copyIndex = index;

        FragmentTransaction transaction = mManager.beginTransaction();

        transaction.add(R.id.fl_context, MainActivity.sFragmentList.get(index).getBaseStepFragment());

        copyIndex = copyIndex - 1;
        if (copyIndex >= 0) {
            transaction.hide(MainActivity.sFragmentList.get(copyIndex).getBaseStepFragment());
        } else {
            transaction.show(MainActivity.sFragmentList.get(index).getBaseStepFragment());
        }

        transaction.addToBackStack(MainActivity.sFragmentList.get(index).getTitle());
        mTitle.setText(MainActivity.sFragmentList.get(index).getTitle());
        transaction.commit();
    }

    private void setLast(int index) {
        mManager.popBackStack();
        FragmentFactoryModel model = MainActivity.sFragmentList.get(index);
        mTitle.setText(model.getTitle());
    }

    public void popBackStack() {
        for (int i = 0; i < index + 1; i++) {
            mManager.popBackStack();
        }
        index = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setNext(index);
            }
        },1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
