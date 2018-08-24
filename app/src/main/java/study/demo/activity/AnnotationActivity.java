package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import study.demo.annotation.User;
import study.demo.annotation.UserFactory;

public class AnnotationActivity extends AppCompatActivity {

    public final static String TAG = "Annotation_Activity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = UserFactory.create();

        Log.d(TAG,"user.getName() --> " + user.getName());
        Log.d(TAG,"user.getAge() --> " + user.getAge());
    }
}
