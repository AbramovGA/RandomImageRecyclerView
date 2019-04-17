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

    List<RandomImage> images = new ArrayList<>(100);

    {
        for (int i = 0; i < 100; i++) {
            images.add(new RandomImage());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_randomimg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RandomImage image = images.get(position);
//        holder.name.setText(image.getName());
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //        final TextView name;
        final ImageView image;

        public ViewHolder(View view) {
            super(view);
//            name = view.findViewById(R.id.lorem_name);
            image = view.findViewById(R.id.item_random_image);
        }
    }
}