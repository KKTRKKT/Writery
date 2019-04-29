package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WriteActivity extends Activity {
    EpisodeDBHandler dbHandler = new EpisodeDBHandler(this, null, null, 1);

    ArrayList<EpisodeItem> dataList = new ArrayList<>();

    Bundle extras;

    RecyclerView.Adapter adapter;

    MaterialButton materialButton;
    TextView titleText;
    TextView infoText;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);

        materialButton = findViewById(R.id.addEpisode);
        titleText = findViewById(R.id.write_title);
        infoText = findViewById(R.id.write_Info);
        imageView = findViewById(R.id.write_img);

        extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        NobelDBHandler nobelDBHandler = new NobelDBHandler(this, null, null, 1);
        NobelItem nobelItem = nobelDBHandler.findNobel(extras.getInt("ID"));
//        Log.d("id", Integer.toString(extras.getInt("ID")));

        titleText.setText(nobelItem.getTitle());
        infoText.setText(nobelItem.getInfo());

        showEpisode();
        if(adapter == null)
            adapterList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToHome(View view) {
        onBackPressed();
    }

    public void gotoOnWrite(View view) {
        Intent intent = new Intent(this, WriteContent.class);
        intent.putExtra("ID", extras.getInt("ID"));
        startActivity(intent);
    }

    public void Setting(View view) {
        Intent intent = new Intent(this, WriteSetting.class);
        intent.putExtra("ID", extras.getInt("ID"));
        startActivity(intent);
    }

    public void showEpisode() {
        ArrayList<EpisodeItem> AllList = new ArrayList<>();
        AllList = dbHandler.showEpisode(extras.getInt("ID"));
        dataList = AllList;
    }

    public void adapterList(){
        RecyclerView recyclerView = findViewById(R.id.write_recyclerview);
        adapter = new writeRecyclerViewAdpater(dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
