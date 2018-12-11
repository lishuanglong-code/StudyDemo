package study.demo.activity.jetpack.livedata;

import android.arch.lifecycle.MutableLiveData;

public class InfoModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> sex;


    public MutableLiveData<String> getName() {
        return name == null ? (name = new MutableLiveData<>()) : name;
    }

    public MutableLiveData<String> getSex() {
        return sex == null ? (sex = new MutableLiveData<>()) : sex;
    }
}
