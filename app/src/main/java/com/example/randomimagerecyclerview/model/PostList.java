package com.example.randomimagerecyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class PostList {
//    @SerializedName("posts")
    List<Post> postList;
}
