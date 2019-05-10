package com.example.randomimagerecyclerview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RandomImageAdapter extends RecyclerView.Adapter<RandomImageAdapter.ViewHolder> {

    List<RandomImage> images;
    OnImgListener onImgListener;

    public RandomImageAdapter(List<RandomImage> images, OnImgListener onImgListener) {
        this.images = images;
        this.onImgListener = onImgListener;
    }

    public RandomImageAdapter(OnImgListener onImgListener) {
        images = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            images.add(new RandomImage());
        }
        this.onImgListener = onImgListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ImageView view = (ImageView) LayoutInflater.from(parent.getContext())
                                                    .inflate(R.layout.item_randomimg, parent, false);

        return new ViewHolder(view, onImgListener, images.get(i));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RandomImage image = images.get(position);
        Picasso.get().load(image.getPath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        RandomImage image;
        OnImgListener onImgListener;

        public ViewHolder(ImageView view, OnImgListener onImgListener, RandomImage image) {
            super(view);
            imageView = view;
            imageView.setOnClickListener(this);
            this.onImgListener = onImgListener;
            this.image = image;
        }

        @Override
        public void onClick(View v) {
            onImgListener.onImgClick(getAdapterPosition(), image.getPath());
        }
    }

    public interface OnImgListener {
        void onImgClick(int position, String imgPath);
    }
}