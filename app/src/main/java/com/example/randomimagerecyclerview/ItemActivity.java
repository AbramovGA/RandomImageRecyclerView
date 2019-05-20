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

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class ItemActivity extends Activity {

    private static String IMG_NOT_FOUND = "https://webhostingmedia.net/wp-content/uploads/2018/01/http-error-404-not-found.png";
    private static String TEXT_NOT_FOUND = "TEXT NOT FOUND";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        loadImageFromSomwhere();
        loadTextFromSomwhere().observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    findViewById(R.id.item_screen).setVisibility(VISIBLE);
                    findViewById(R.id.progress).setVisibility(INVISIBLE);
                });
    }

    private void loadImageFromSomwhere() {
        String imageUrl = getImgFromIntent().orElse(IMG_NOT_FOUND);
        ImageView imageView = findViewById(R.id.item_image);
        Picasso.get().load(imageUrl).into(imageView);
    }

    private Observable loadTextFromSomwhere() {
        TextView textView = findViewById(R.id.item_description);
        return Single.fromCallable(this::loadText)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess((text) -> textView.setText(text))
                .toObservable();
    }


    private String loadText() {

        JSONPlaceholderService service = JSONPlaceholderService.init();

        Response<List<Post>> response = null;
        try {
            response = service.listPosts().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = response.body().get(0).getBody();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return text;

    }

    private Optional<String> getImgFromIntent() {
        String imageUrl = null;
        if (getIntent().hasExtra("image_url")) {
            imageUrl = getIntent().getStringExtra("image_url");
        }
        return Optional.ofNullable(imageUrl);
    }
}
