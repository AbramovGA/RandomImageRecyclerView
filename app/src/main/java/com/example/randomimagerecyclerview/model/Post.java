package com.example.randomimagerecyclerview.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Post {
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
}
