package com.example.blog_post_android.service.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.blog_post_android.service.model.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getAllPost();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(List<Post> postList);

    @Query("DELETE FROM post")
    void deleteAll();
}
