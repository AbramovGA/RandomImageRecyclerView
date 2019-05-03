package com.example.randomimagerecyclerview;

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

    public RandomImageAdapter(List<RandomImage> images) {
        this.images = images;
    }

    public RandomImageAdapter() {
        images = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            images.add(new RandomImage());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ImageView view = (ImageView) LayoutInflater.from(parent.getContext())
                                                    .inflate(R.layout.item_randomimg, parent, false);

        return new ViewHolder(view);
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




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(ImageView view) {
            super(view);
            imageView = view;
        }
    }
}