package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class WriteActivity extends Activity {
    ArrayList<WriteItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);

        dataList.add(new WriteItem("wow", "very"));

        RecyclerView recyclerView = findViewById(R.id.write_recyclerview);
        RecyclerView.Adapter adapter = new writeRecyclerViewAdpater(dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    public void goToHome(View view) {
        onBackPressed();
    }

    public void gotoOnWrite(View view) {
        Intent intent = new Intent(this, WriteContent.class);
        startActivity(intent);
    }
}
