package com.example.blog_post_android.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blog_post_android.service.model.Post;
import com.example.blog_post_android.service.network.APIService;
import com.example.blog_post_android.service.network.RetrofitInstance;
import com.example.blog_post_android.service.repository.PostRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends AndroidViewModel {

    private PostRepository postRepository;
    private LiveData<List<Post>> getAllPosts;

    public PostViewModel(@NonNull @NotNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        makeAPICall();
        getAllPosts = postRepository.getPosts();
    }


    public void insertPost(List<Post> postList) {
        postRepository.insertPost(postList);
    }

    public LiveData<List<Post>> getGetAllPosts() {
        return getAllPosts;
    }

    public void makeAPICall() {
        APIService apiService = RetrofitInstance.getRetrofitClient()
                .create(APIService.class);
        apiService.getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    Log.d("VM", "onResponse: "+response.body());
                    insertPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
