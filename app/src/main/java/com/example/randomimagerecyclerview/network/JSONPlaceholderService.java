package com.example.randomimagerecyclerview.network;

import com.example.randomimagerecyclerview.model.Post;
import com.example.randomimagerecyclerview.model.PostList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderService {
    @GET("/posts")
    Call<List<Post>> listPosts();
}
