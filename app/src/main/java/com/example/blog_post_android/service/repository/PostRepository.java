package com.example.blog_post_android.service.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.blog_post_android.service.dao.PostDao;
import com.example.blog_post_android.service.database.AppDatabase;
import com.example.blog_post_android.service.model.Post;

import java.util.List;

public class PostRepository {

    private PostDao postDao;
    private LiveData<List<Post>> posts;

    public PostRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabaseInstance(application);
        postDao = database.postDao();
        posts = postDao.getAllPost();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void insertPost(List<Post> postList) {
        new InsertPostAsyncTask(postDao).execute(postList);
    }

    private static class InsertPostAsyncTask extends AsyncTask<List<Post>, Void, Void> {
        private PostDao postDao;

        private InsertPostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(List<Post>... lists) {
            postDao.insertPosts(lists[0]);
            return null;
        }
    }
}
