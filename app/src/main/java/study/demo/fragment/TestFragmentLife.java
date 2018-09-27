package study.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import study.demo.R;
import study.demo.variable.Variable;

public class TestFragmentLife extends Fragment {

    public static final String TAG = "Test_Fragment_Life";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_test_life, container, false);
        ++Variable.count;
        return inflate;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged -->" + hidden + " , " + (Variable.count));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart -->" + (Variable.count));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume -->" + (Variable.count));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause -->" + (Variable.count));
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop -->" + (Variable.count));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy -->" + (Variable.count));
    }

}
