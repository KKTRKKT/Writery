package com.example.writery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageView> list = new ArrayList<ImageView>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fad = findViewById(R.id.write_fad);

        fad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWrite();
            }
        });

        RecyclerView gridRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.Adapter gridAdapter = new RecyclerViewAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        gridRecyclerView.setAdapter(gridAdapter);
    }

    private void goToWrite(){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
}
