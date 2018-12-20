package study.demo.activity.fragments;

import android.support.v4.app.Fragment;

public class FragmentFactoryModel {
    private String title;
    private Fragment mBaseStepFragment;

    public FragmentFactoryModel(String title, Fragment baseStepFragment) {
        this.title = title;
        mBaseStepFragment = baseStepFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getBaseStepFragment() {
        return mBaseStepFragment;
    }

    public void setBaseStepFragment(Fragment baseStepFragment) {
        mBaseStepFragment = baseStepFragment;
    }
}