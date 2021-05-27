package com.example.blog_post_android.service.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static String BASE_URL="https://jsonplaceholder.typicode.com/";
    public static Retrofit retrofit;
    public static Retrofit getRetrofitClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
