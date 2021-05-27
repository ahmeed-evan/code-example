package com.example.blog_post_android.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog_post_android.R;
import com.example.blog_post_android.service.model.Post;
import com.example.blog_post_android.view.adapter.PostDetailsRecyclerViewAdapter;
import com.example.blog_post_android.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostFragment extends Fragment {

    @BindView(R.id.postRecyclerView)
    RecyclerView postRecyclerView;

    private PostDetailsRecyclerViewAdapter adapter;
    private PostViewModel postViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);
        loadContent();
        return view;
    }

    private void loadContent() {
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postViewModel.getGetAllPosts().observe(getActivity(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> postList) {
                Log.d("Post Frag", "onChanged: " + postList);
                adapter = new PostDetailsRecyclerViewAdapter(postList);
                adapter.notifyDataSetChanged();
                postRecyclerView.setAdapter(adapter);
            }
        });

    }
}
