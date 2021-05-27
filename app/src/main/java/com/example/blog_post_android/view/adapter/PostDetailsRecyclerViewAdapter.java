package com.example.blog_post_android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog_post_android.R;
import com.example.blog_post_android.service.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailsRecyclerViewAdapter extends RecyclerView.Adapter<PostDetailsRecyclerViewAdapter.PostViewHolder> {

    private List<Post> allPost;

    public PostDetailsRecyclerViewAdapter(List<Post> postList) {
        this.allPost = postList;
    }

    @NonNull
    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_detail, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostDetailsRecyclerViewAdapter.PostViewHolder holder, int position) {
        Post post = allPost.get(position);
        holder.userIdTV.setText("UserId: "+post.getUserId());
        holder.titleTV.setText("Title: "+post.getTitle());
        holder.bodyTV.setText("Body: "+post.getBody());
    }

    @Override
    public int getItemCount() {
        return allPost.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userIdTV)
        TextView userIdTV;
        @BindView(R.id.titleTV)
        TextView titleTV;
        @BindView(R.id.bodyTV)
        TextView bodyTV;


        public PostViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
