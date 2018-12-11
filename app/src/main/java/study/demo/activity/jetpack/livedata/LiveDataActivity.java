package study.demo.activity.jetpack.livedata;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import study.demo.R;

public class LiveDataActivity extends AppCompatActivity {

    private TextView mContext;
    private Button mChanged;
    private InfoModel mInfoModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        mContext = findViewById(R.id.tv_context);
        mChanged = findViewById(R.id.btn_changed);

        mInfoModel = new InfoModel();
        mInfoModel.getName().setValue("呵呵");
        mInfoModel.getSex().setValue("男");
        mContext.setText("name:  " + mInfoModel.getName().getValue() + " ,sex:  " + mInfoModel.getSex().getValue());

        Observer<String> infoNameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mContext.setText("name:  " + s + " ,sex:  " + mInfoModel.getSex().getValue());
            }
        };

        Observer<String> infoSexObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mContext.setText("name:  " + mInfoModel.getName().getValue() + " ,sex:  " + s);
            }
        };

        mInfoModel.getName().observe(this, infoNameObserver);
        mInfoModel.getSex().observe(this, infoSexObserver);


        mChanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInfoModel.getName().setValue("哈哈");
                mInfoModel.getSex().setValue("女");
            }
        });

    }
}
