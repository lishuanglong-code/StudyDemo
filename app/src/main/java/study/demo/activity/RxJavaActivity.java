package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import study.demo.utils.L;


public class RxJavaActivity extends AppCompatActivity {
    public static final String TAG = "RxJava_Activity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Observable.just("返回空值01")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        L.d(TAG,"s --> " + s);
                        return "返回空值02";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        L.d(TAG,"s --> " + s);
                    }
                });





        Single.just("返回空值03")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        L.d(TAG,"s --> " + s);
                    }
                });




    }
}
