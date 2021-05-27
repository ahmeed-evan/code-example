package com.example.blog_post_android.service.network;

import com.example.blog_post_android.service.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("posts")
    Call<List<Post>> getAllPost();
}
