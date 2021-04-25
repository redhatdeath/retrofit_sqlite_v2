package ru.teacher.retrofit_sqliyte.API;

import retrofit2.Call;
import retrofit2.http.POST;
import ru.teacher.retrofit_sqliyte.API.Model.ResponseData;

public interface APIServise {
    @POST("getTen/")
    Call<ResponseData> getData();
}
