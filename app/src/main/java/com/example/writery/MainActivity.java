package com.example.writery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<NobelItem> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fad = findViewById(R.id.write_fad);

        fad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateWriteDialog createWriteDialog = new CreateWriteDialog((MainActivity)view.getContext());
                createWriteDialog.callFunction();
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

    @Override
    public void onBackPressed() {
        BackCloseHandler backCloseHandler = new BackCloseHandler(this);
        backCloseHandler.onBackPressed();
        //super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
