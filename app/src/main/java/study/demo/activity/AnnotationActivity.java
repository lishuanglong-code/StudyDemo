package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import adaptive.screen.lsl.annotationlib.BindView;
import adaptive.screen.lsl.apilib.InjectHelper;
import study.demo.App;
import study.demo.R;
import study.demo.annotation.User;
import study.demo.annotation.UserFactory;

public class AnnotationActivity extends AppCompatActivity {

    public final static String TAG = "Annotation_Activity";

    @BindView(R.id.btn_toast)
    Button toast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        InjectHelper.inject(this);

//        User user = UserFactory.create();
//
//        Log.d(TAG,"user.getName() --> " + user.getName());
//        Log.d(TAG,"user.getAge() --> " + user.getAge());

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.getContext(),"onClick...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
