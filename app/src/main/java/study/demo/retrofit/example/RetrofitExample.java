package study.demo.retrofit.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import study.demo.retrofit.example.bean.Verse;
import study.demo.retrofit.example.interfaces.GetDataService;
import study.demo.utils.L;

public class RetrofitExample extends AppCompatActivity {

    public static String TAG = "Retrofit_Example";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .build();
        GetDataService getDataService = retrofit.create(GetDataService.class);
        Call<ResponseBody> call = getDataService.getData();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                L.d(TAG, "response.message() --> " + response.message());
                try {

                    String jsonData = response.body().string();

                    L.d(TAG, "response.body().toString() --> " + jsonData);

                    Gson gson = new Gson();

                    Verse verse = gson.fromJson(jsonData, Verse.class);

                    StringBuilder sb = new StringBuilder();
                    sb.append("\r\n")
                            .append("code:")
                            .append("\r\n")
                            .append(verse.getCode())
                            .append("\r\n")
                            .append("message:")
                            .append("\r\n")
                            .append(verse.getMessage())
                            .append("\r\n")
                            .append("result:")
                            .append("\r\n\r\n")
                            .append("title:")
                            .append("\r\n")
                            .append(verse.getResult().getTitle())
                            .append("\r\n")
                            .append("content:")
                            .append("\r\n")
                            .append(verse.getResult().getContent())
                            .append("\r\n")
                            .append("authors:")
                            .append("\r\n")
                            .append(verse.getResult().getAuthors());

                    L.d(TAG, sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                L.d(TAG, "t.getMessage() --> " + t.getMessage());

            }
        });

    }
}
