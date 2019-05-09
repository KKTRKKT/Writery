package com.example.writery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BackCloseHandler backCloseHandler;

    public RecyclerView.Adapter gridAdapter;
    public ArrayList<NobelItem> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fad = findViewById(R.id.write_fad);

        backCloseHandler = new BackCloseHandler(this);

        fad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateWriteDialog createWriteDialog = new CreateWriteDialog((MainActivity)view.getContext());
                createWriteDialog.callFunction();
                showNobel();
                adapterList();
            }
        });

        showNobel();
        adapterList();
    }

    @Override
    public void onBackPressed() {
       backCloseHandler.onBackPressed();
//        super.onBackPressed();
    }

    //리스트 적용
    public void adapterList(){
        RecyclerView gridRecyclerView =  findViewById(R.id.recyclerview);
        gridAdapter = new NobelRecyclerViewAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        gridRecyclerView.setAdapter(gridAdapter);
    }

    //DB에서 목록을 불러와 리스트에 넣기
    public void showNobel() {
        NobelDBHandler dbHandler = new NobelDBHandler(this, null, null, 1);
        ArrayList<NobelItem> AllList = new ArrayList<>();
        AllList = dbHandler.showList();
        if(!(AllList == null)){
            list = AllList;
        }
    }

    //DB에서 삭제
    public void delNobel(int code){
        NobelDBHandler dbHandler = new NobelDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteProduct(code);
        if(result){
            EpisodeDBHandler episodeDBHandler = new EpisodeDBHandler(this, null, null,  1);
            episodeDBHandler.deleteProduct(code);
            Toast.makeText(this, "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
        }
        showNobel();
    }
}
