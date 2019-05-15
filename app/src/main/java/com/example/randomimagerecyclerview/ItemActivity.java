package com.example.randomimagerecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.randomimagerecyclerview.model.Post;
import com.example.randomimagerecyclerview.network.JSONPlaceholderService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

import lombok.SneakyThrows;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemActivity extends Activity {

    private static String IMG_NOT_FOUND = "https://webhostingmedia.net/wp-content/uploads/2018/01/http-error-404-not-found.png";
    private static String TEXT_NOT_FOUND = "TEXT NOT FOUND";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        String imageUrl = getImgFromIntent().orElse(IMG_NOT_FOUND);

        setItem(imageUrl);

    }

    @SneakyThrows
    private void loadTextFromSomewhere() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            Response<List<Post>> response = null;
            try {
                response = service.listPosts().execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }).thenAccept(listResponse -> {
            String text = listResponse.body().get(0).getBody();
            TextView textView = findViewById(R.id.item_description);
            textView.setText(text);
        });

        future.get();

    }

    private Optional<String> getImgFromIntent() {
        String imageUrl = null;
        if (getIntent().hasExtra("image_url")) {
            imageUrl = getIntent().getStringExtra("image_url");
        }
        return Optional.ofNullable(imageUrl);
    }

    private void setItem(String imageUrl) {
        ImageView imageView = findViewById(R.id.item_image);
        Picasso.get().load(imageUrl).into(imageView);

        loadTextFromSomewhere();
    }

}
