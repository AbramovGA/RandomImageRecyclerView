package com.example.randomimagerecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

public class MainActivity extends Activity implements RandomImageAdapter.OnImgListener {

    RecyclerView recyclerView;
    LayoutManager layoutManager;
    RandomImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RandomImageAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImgClick(int position, String imgPath) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("image_url", imgPath);
        startActivity(intent);
    }
}
