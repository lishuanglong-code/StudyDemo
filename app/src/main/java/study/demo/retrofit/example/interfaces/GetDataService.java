package study.demo.retrofit.example.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("recommendPoetry")
    Call<ResponseBody> getData();



}
