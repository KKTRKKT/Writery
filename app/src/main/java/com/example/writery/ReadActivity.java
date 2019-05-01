package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadActivity extends Activity {
    EpisodeDBHandler dbHandler;

    ArrayList<ReadItem> list = new ArrayList<>();

    Bundle extras;

    TextView title;

    int ID;
    int code;

    String t;
    String c;

    final int LEN = 400;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);

        title = findViewById(R.id.read_title);

        extras = getIntent().getExtras();
        if(extras == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        try{
        code = extras.getInt("code");
        ID = extras.getInt("ID");
//        Log.d("ID", Integer.toString(ID));
//        Log.d("CODE", Integer.toString(code));
        showNobel();
        adapterList();

        title.setText(dbHandler.findEpisode(ID).getEpisodeTitle());
        }catch (Exception e){
            t = extras.getString("title");
            c = extras.getString("contents");

//        Log.d("ID", Integer.toString(ID));
//        Log.d("CODE", Integer.toString(code));

            preView();
            adapterList();

            title.setText(t);
        }
    }

    public void showNobel() {
        dbHandler = new EpisodeDBHandler(this, null, null, 1);
        EpisodeItem episodeItem;
        episodeItem = dbHandler.findEpisode(ID);
        if(episodeItem.getContents().length() > LEN){
            String page = episodeItem.getContents();
            for(int i = 0; i < page.length()/LEN+1; i++){
                String sub;
                if(page.length()/LEN <= i){
                    sub = page.substring((LEN*i));
                }else {
                    sub = page.substring((LEN * i), ((LEN+1) * (i + 1)) - (i));
                }
                list.add(new ReadItem(sub));

            }
        }else{
            list.add(new ReadItem(episodeItem.getContents()));
        }
    }

    public void preView() {
        if(c.length() > LEN){
            String page = c;
            for(int i = 0; i < page.length()/LEN+1; i++){
                String sub;
                if(page.length()/LEN <= i){
                    sub = page.substring((LEN*i));
                }else {
                    sub = page.substring((LEN * i), ((LEN+1) * (i + 1)) - (i));
                }
                list.add(new ReadItem(sub));
            }
        }else{
            list.add(new ReadItem(c));
        }
    }

    public void adapterList(){
        RecyclerView recyclerView =  findViewById(R.id.read_recyclerView);
        RecyclerView.Adapter adapter = new ReadRecyclerView(list);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }
}
