package ru.teacher.retrofit_sqliyte.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceConstructor {
    public static <T> T CreateService(Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ru.teacher.retrofit_sqliyte.API.APIConfig.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
