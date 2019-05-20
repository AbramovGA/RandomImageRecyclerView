package com.example.randomimagerecyclerview.network;

import com.example.randomimagerecyclerview.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface JSONPlaceholderService {
    @GET("/posts")
    Call<List<Post>> listPosts();

    static JSONPlaceholderService init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(JSONPlaceholderService.class);
    }
}
