package com.example.randomimagerecyclerview;

public class RandomImage {

    String path;

    public RandomImage() {
        path = "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";
    }

    public RandomImage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }


}
