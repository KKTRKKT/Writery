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
    ArrayList<EpisodeItem> dataList = new ArrayList<>();
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

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        String title = extras.getString("title");
        String info = extras.getString("info");
        int image = extras.getInt("image");
        String[] contents = extras.getStringArray("contents");
        String[] episodeTitle = extras.getStringArray("episodeTitle");


        for(int i = 0; i < (contents != null ? contents.length : 0); i++) {
            dataList.add(new EpisodeItem(episodeTitle[i], contents[i]));
        }

        imageView.setImageResource(image);
        titleText.setText(title);
        infoText.setText(info);

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

    public void Setting(View view) {
        Intent intent = new Intent(this, WriteSetting.class);
        startActivity(intent);
    }
}
